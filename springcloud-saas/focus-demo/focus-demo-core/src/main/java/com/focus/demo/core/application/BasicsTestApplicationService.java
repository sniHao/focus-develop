package com.focus.demo.core.application;

import com.focus.demo.core.domain.vo.BasicsTestVo;
import com.focus.demo.core.domainservice.BasicsTestDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zi-wei
 * @create 2025/6/6 11:11
 */
@Service
@RequiredArgsConstructor
public class BasicsTestApplicationService {
    private final BasicsTestDomainService basicsTestDomainService;

    public List<BasicsTestVo> queryList() {
        return basicsTestDomainService.queryList();
    }
}
