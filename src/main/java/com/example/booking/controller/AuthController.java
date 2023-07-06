package com.example.booking.controller;

import com.example.booking.config.security.jwt.JwtUtils;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.model.RefreshToken;
import com.example.booking.request.LoginRequest;
import com.example.booking.request.SignUpRequest;
import com.example.booking.request.TokenRefreshRequest;
import com.example.booking.response.TokenRefreshResponse;
import com.example.booking.service.AuthService;
import com.example.booking.service.JwtBlacklistService;
import com.example.booking.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
@Slf4j
@CrossOrigin("*")
@SecurityRequirement(name = "token")
public class AuthController {
    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final MessageSource messageSource;

    private final JwtBlacklistService jwtBlacklistService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtUtils jwtUtils, MessageSource messageSource, JwtBlacklistService jwtBlacklistService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtils = jwtUtils;
        this.messageSource = messageSource;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest).createResponseEntity();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authService.registerUser(signUpRequest).createResponseEntity();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request)
            throws BookingException.TokenRefreshException{
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return new Response(HttpStatus.OK,
                            messageSource.getMessage("get.token.successfully",
                                    null,
                                    LocaleContextHolder.getLocale()
                            ),
                            new TokenRefreshResponse(token, requestRefreshToken),
                            1);
                })
                .orElseThrow(() -> new BookingException.TokenRefreshException("")).createResponseEntity();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return authService.logoutUser().createResponseEntity();
    }
}
