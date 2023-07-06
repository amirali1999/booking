package com.example.booking.service;

import com.example.booking.config.security.FeignClientInterceptor;
import com.example.booking.config.security.jwt.JwtUtils;
import com.example.booking.config.security.services.UserDetailsImpl;
import com.example.booking.exception.Response;
import com.example.booking.model.RefreshToken;
import com.example.booking.model.Role;
import com.example.booking.model.User;
import com.example.booking.repository.RefreshTokenRepository;
import com.example.booking.repository.RoleRepository;
import com.example.booking.repository.UserRepository;
import com.example.booking.request.LoginRequest;
import com.example.booking.request.SignUpRequest;
import com.example.booking.response.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository usersRepository;
    private final RoleRepository rolesRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserService usersService;
    private final JwtBlacklistService jwtBlacklistService;
    private final FeignClientInterceptor feignClientInterceptor;
    private final RefreshTokenRepository refreshTokenRepository;

    private final RefreshTokenService refreshTokenService;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository usersRepository,
                       RoleRepository rolesRepository,
                       PasswordEncoder encoder,
                       JwtUtils jwtUtils, UserService usersService,
                       JwtBlacklistService jwtBlacklistService, FeignClientInterceptor feignClientInterceptor,
                       RefreshTokenRepository refreshTokenRepository, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.usersService = usersService;
        this.jwtBlacklistService = jwtBlacklistService;
        this.feignClientInterceptor = feignClientInterceptor;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenService = refreshTokenService;
    }

    public Response authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        //Authentication
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //create jwt access token
        String jwt = jwtUtils.generateJwtToken(authentication);
        //get authenticate user's details like email and username to userDetails
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return new Response(
                HttpStatus.OK,
                "user.signin.successfully",
                new JwtResponse(jwt, refreshToken.getToken()),
                1);
    }

    public Response registerUser(SignUpRequest signUpRequest) {
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .password(encoder.encode(signUpRequest.getPassword())).build();
        Set<Role> roles = new HashSet<>();
        roles.add(rolesRepository.findById((long) 2).get());
        user.setRoles(roles);
        usersRepository.save(user);
        return new Response(HttpStatus.OK, "user.registered.successfully", signUpRequest, 1);
    }
    @Transactional
    public Response logoutUser(){
        String accessToken = feignClientInterceptor.getBearerTokenHeader().replace("Bearer ","");
        String email = jwtUtils.getUserNameFromJwtToken(accessToken);
        User user = usersRepository.findByEmail(email).orElse(null);
        refreshTokenRepository.deleteAccessTokens(user.getId());
        jwtBlacklistService.saveTokenToBlackList(accessToken,jwtUtils.getExpireDateFromJwtToken(accessToken));
        return new Response(HttpStatus.OK, "user.logout.successfully",null, 1);
    }
}
