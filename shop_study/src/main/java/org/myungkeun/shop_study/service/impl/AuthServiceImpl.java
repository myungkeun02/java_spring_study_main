package org.myungkeun.shop_study.service.impl;

//import org.myungkeun.shop_study.entity.Member;
//import org.myungkeun.shop_study.exception.ProductApiException;
//import org.myungkeun.shop_study.payload.LoginUserDto;
//import org.myungkeun.shop_study.payload.RegisterUserDto;
//import org.myungkeun.shop_study.repository.MemberRepository;
//import org.myungkeun.shop_study.security.JwtTokenProvider;
//import org.myungkeun.shop_study.service.AuthService;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;

import org.myungkeun.shop_study.entity.Member;
import org.myungkeun.shop_study.exception.ProductApiException;
import org.myungkeun.shop_study.payload.LoginUserDto;
import org.myungkeun.shop_study.payload.RegisterUserDto;
import org.myungkeun.shop_study.repository.MemberRepository;
import org.myungkeun.shop_study.security.JwtTokenProvider;
import org.myungkeun.shop_study.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDto.getName(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String registerUser(RegisterUserDto registerUserDto) {
        if (memberRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new ProductApiException(HttpStatus.BAD_REQUEST, "email is already exist");
        }
        Member member = new Member();
        member.setName(registerUserDto.getName());
        member.setEmail(registerUserDto.getEmail());
        member.setPhone(registerUserDto.getPhone());
        member.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        memberRepository.save(member);
        return "user registered successfully";
    }
}
