package com.example.booking.service;

import com.example.booking.exception.BookingException;
import com.example.booking.model.JwtBlacklist;
import com.example.booking.repository.JwtBlacklistRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtBlacklistService {
    private final JwtBlacklistRepository jwtBlacklistRepository;

    public JwtBlacklistService(JwtBlacklistRepository jwtBlacklistRepository) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
    }

    public void checkAccessTokenExpire(String accessToken) throws BookingException.TokenExpireException {
        if(jwtBlacklistRepository.findByAccessToken(accessToken).isPresent()){
            throw new BookingException.TokenExpireException("");
        }
    }

    public void saveTokenToBlackList(String accessToken, Date date){
        jwtBlacklistRepository.save(new JwtBlacklist(accessToken, date));
    }
}
