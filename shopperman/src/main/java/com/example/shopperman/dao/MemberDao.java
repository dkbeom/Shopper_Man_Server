package com.example.shopperman.dao;

import java.util.Map;

import com.example.shopperman.entity.LoginForm;
import com.example.shopperman.entity.Member;

public interface MemberDao {

    Member getMemberByLoginForm(LoginForm loginForm);
    
    Member getMemberById(String id);

    boolean insertMember(Member member);

    String getIdById(String id);

    String getIdByNickname(String nickname);
    
    Integer getSumScore(String id);
    
    Integer getNumScore(String id);
    
    boolean setScore(Map<String, Object> newScore);
}