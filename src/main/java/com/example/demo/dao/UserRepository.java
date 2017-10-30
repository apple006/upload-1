package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 18274 on 2017/8/9.
 */
public interface UserRepository extends JpaRepository<User,Long>{
}
