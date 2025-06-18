package com.focus.tasks;

import com.focus.tasks.service.FocusTasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:定时任务
 * @Author: ni_hao
 * @Date: 2025-06-18 14:58
 */
@Component
@RestController
@RequiredArgsConstructor
public class FocusTasks {

    private final FocusTasksService focusTasksService;

    /**
     * 每1小时执行一次
     */
//    @Scheduled(cron = "0 0 * * * ?")
//    public void text() {
//        focusTasksService.text();
//    }
}
