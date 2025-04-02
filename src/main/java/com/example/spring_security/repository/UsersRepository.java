package com.example.spring_security.repository;

import com.example.spring_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users getByUsername(String username);

    Users findByUsername(String username);
}
