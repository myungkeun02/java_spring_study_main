package org.myungkeun.shop_study.service.impl;

import org.myungkeun.shop_study.payload.LoginUserDto;
import org.myungkeun.shop_study.payload.RegisterUserDto;
import org.myungkeun.shop_study.repository.MemberRepository;
import org.myungkeun.shop_study.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private MemberRepository memberRepository;

    public AuthServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {
        return null;
    }

    @Override
    public String registerUser(RegisterUserDto registerUserDto) {
        return null;
    }
}
