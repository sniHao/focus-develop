package com.focus.domain.admin.service.impl;

import com.focus.domain.admin.repository.Admins;
import com.focus.domain.admin.service.ManageAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageAdminServiceImpl implements ManageAdminService {
    private final Admins admins;

    @Override
    public String sayHello() {
        return "say " + admins.sayHelloWorld();
    }
}
