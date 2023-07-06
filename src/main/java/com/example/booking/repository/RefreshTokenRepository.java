package com.example.booking.repository;

import com.example.booking.model.JwtBlacklist;
import com.example.booking.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query(value = "DELETE from RefreshToken u where u.user.id =:id")
    void deleteAccessTokens(@Param("id")long id);
//
//    @Modifying
//    int deleteByUser(Users users);
}
