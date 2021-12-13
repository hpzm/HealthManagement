package com.example.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

//https://www.pianshen.com/article/2513137392/

public class TencentCosUtils {

    private static final String ACCESSKEY = "AKIDpTryoOghRu61f29zXooY1EBrGNDmIXVC";
    private static final String SECRETKEY = "RbT4ORCgtpUszpOggQs3wZuykGUR7VcS";
    private static final String BUCKETNAME = "health-1257135628";
    private static final String REGIONID = "ap-nanjing";

    public static COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(ACCESSKEY, SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        //String bucketName = BUCKETNAME;
        return cosClient;
    }

    /**
     * 上传文件
     *
     * @return //绝对路径和相对路径都OK
     */
    public static String uploadFile(MultipartFile multipartFile, String fileName) throws Exception{
        InputStream inputStream = multipartFile.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, fileName,inputStream,objectMetadata);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
        putObjectRequest.setStorageClass(StorageClass.Standard);
        objectMetadata.setContentType("image/jpeg");//文件的类型
        COSClient cc = getCosClient();
        try {
            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
            String etag = putObjectResult.getETag();
            //System.out.println(etag);
            System.out.println("图片上传成功");
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        //cc.shutdown();
        return "上传成功";
    }

    /**
     * 下载文件
     *
     * @param bucketName
     * @param key
     * @return
     */
    public static String downLoadFile(String bucketName, String key) {
        File downFile = new File("G:\\development\\code\\HealthManagement\\health_backend\\src\\test\\java\\com\\example\\test\\1.jpg");
        COSClient cc = getCosClient();
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);

        ObjectMetadata downObjectMeta = cc.getObject(getObjectRequest, downFile);
        cc.shutdown();
        String etag = downObjectMeta.getETag();
        return etag;
    }

    /**
     * 删除文件
     * @param key
     */
    public static void deleteFile(String key) {
        COSClient cc = getCosClient();
        try {
            cc.deleteObject(BUCKETNAME, key);
            System.out.println("图片删除成功");
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cc.shutdown();
        }

    }

    /**
     * 查看桶文件
     *
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static ObjectListing listObjects(String bucketName) throws CosClientException, CosServiceException {
        COSClient cc = getCosClient();
        // 获取 bucket 下成员（设置 delimiter）
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        // 设置 list 的 prefix, 表示 list 出来的文件 key 都是以这个 prefix 开始
        listObjectsRequest.setPrefix("");
        // 设置 delimiter 为/, 即获取的是直接成员，不包含目录下的递归子成员
        listObjectsRequest.setDelimiter("/");
        // 设置 marker, (marker 由上一次 list 获取到, 或者第一次 list marker 为空)
        listObjectsRequest.setMarker("");
        // 设置最多 list 100 个成员,（如果不设置, 默认为 1000 个，最大允许一次 list 1000 个 key）
        listObjectsRequest.setMaxKeys(100);

        ObjectListing objectListing = cc.listObjects(listObjectsRequest);
        // 获取下次 list 的 marker
        String nextMarker = objectListing.getNextMarker();
        // 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        boolean isTruncated = objectListing.isTruncated();
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        for (COSObjectSummary cosObjectSummary : objectSummaries) {
            // get file path
            String key = cosObjectSummary.getKey();
            // get file length
            long fileSize = cosObjectSummary.getSize();
            // get file etag
            String eTag = cosObjectSummary.getETag();
            // get last modify time
            Date lastModified = cosObjectSummary.getLastModified();
            // get file save type
            String StorageClassStr = cosObjectSummary.getStorageClass();
        }
        return objectListing;
    }

    /**
     * 查询一个 Bucket 所在的 Region。
     *
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static String getBucketLocation(String bucketName) throws CosClientException, CosServiceException {
        COSClient cosClient = getCosClient();
        // bucket 的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String location = cosClient.getBucketLocation(bucketName);
        return location;
    }

    public static void main(String[] args) {
        //uploadFile();
        //downLoadFile(BUCKETNAME, KEY);
        //deleteFile(BUCKETNAME , KEY);
        //System.out.println(listObjects(BUCKETNAME));
        //System.out.println("BUCKETNAME的位置：" + getBucketLocation(BUCKETNAME));
    }
}
