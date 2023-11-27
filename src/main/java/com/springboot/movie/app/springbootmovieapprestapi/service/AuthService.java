package com.springboot.movie.app.springbootmovieapprestapi.service;

import com.springboot.movie.app.springbootmovieapprestapi.dto.LoginDto;
import com.springboot.movie.app.springbootmovieapprestapi.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
