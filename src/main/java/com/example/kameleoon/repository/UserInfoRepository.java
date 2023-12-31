package com.example.kameleoon.repository;

import com.example.kameleoon.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
}
