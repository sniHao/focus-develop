package com.focus.application.service.impl;

import com.focus.application.service.AdminService;
import com.focus.domain.admin.service.ManageAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ManageAdminService manageAdminService;

    @Override
    public String sayHello() {
        return manageAdminService.sayHello() ;
    }
}
