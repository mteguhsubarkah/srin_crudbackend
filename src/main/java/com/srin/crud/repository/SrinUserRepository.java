package com.srin.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.srin.crud.domain.SrinUser;

import javax.transaction.Transactional;
import java.util.Map;

public interface SrinUserRepository extends JpaRepository<SrinUser, Integer> {
    @Query(value = "Select * from ais.user where username = ?1", nativeQuery = true)
    Map<String,Object> findByUsername(String username);

    @Query(value = "SELECT password FROM ais.user where username = ?1", nativeQuery = true)
    String findPasswordByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ais.`user`\n" +
        "(username, password, `type`, token, reset_token, reset_timeout, permission)\n" +
        "VALUES(?1, ?2, 0, '0', '0', now(), 0)", nativeQuery = true)
    void addUser(String username, String password);

}
