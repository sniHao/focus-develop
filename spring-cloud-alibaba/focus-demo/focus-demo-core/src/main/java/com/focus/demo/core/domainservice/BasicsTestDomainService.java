package com.focus.demo.core.domainservice;

import cn.hutool.core.bean.BeanUtil;
import com.focus.demo.core.domain.entity.BasicsTest;
import com.focus.demo.core.domain.vo.BasicsTestVo;
import com.focus.demo.core.repository.BasicsTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zi-wei
 * @create 2025/6/6 11:11
 */
@Service
@RequiredArgsConstructor
public class BasicsTestDomainService {
    private final BasicsTestRepository basicsTestRepository;

    public List<BasicsTestVo> queryList() {
        List<BasicsTest> list = basicsTestRepository.list();
        return BeanUtil.copyToList(list, BasicsTestVo.class);
    }
}
