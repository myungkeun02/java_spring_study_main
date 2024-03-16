package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.payload.LoginUserDto;
import org.myungkeun.shop_study.payload.RegisterUserDto;

public interface AuthService {
    String loginUser(LoginUserDto loginUserDto);
    String registerUser(RegisterUserDto registerUserDto);

}
