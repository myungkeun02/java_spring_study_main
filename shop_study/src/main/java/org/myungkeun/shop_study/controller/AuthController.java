package org.myungkeun.shop_study.controller;

import org.myungkeun.shop_study.payload.LoginUserDto;
import org.myungkeun.shop_study.payload.RegisterUserDto;
import org.myungkeun.shop_study.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto loginUserDto) {
        return null;
    }
}
