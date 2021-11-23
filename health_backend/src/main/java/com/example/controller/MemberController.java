package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.constant.MessageConstant;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.entity.Result;
import com.example.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理会员相关操作
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    @Reference
    private MemberService memberService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return memberService.pageQuery(queryPageBean);
    }

    //注销
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            memberService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.DELETE_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);
    }
}
