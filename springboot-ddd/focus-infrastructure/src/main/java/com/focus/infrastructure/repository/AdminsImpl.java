package com.focus.infrastructure.repository;

import com.focus.domain.admin.repository.Admins;
import org.springframework.stereotype.Repository;


@Repository
public class AdminsImpl implements Admins {
    @Override
    public String sayHelloWorld() {
        return "hello world";
    }
}
