package com.example.service;

import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Member;

import java.util.List;

public interface MemberService {

    //根据手机号查询会员
    public Member findByTelephone(String telephone);

    public void add(Member member);

    public List<Integer> findMemberCountByMonths(List<String> months);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void edit(Member member);

    public void deleteById(Integer id);
}
