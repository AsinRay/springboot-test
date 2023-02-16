package com.dd.su.service;

import com.dd.su.domain.PushLog;
import com.dd.su.mapper.PushLogMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

class PushLogServiceTest {

    @InjectMocks
    PushLogService pushLogService;

    @MockBean
    PushLogMapper pushLogMapper;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getById() {

        int id = 1;

        PushLog pl = PushLog.builder()
                .id(id)
                .src("PushLogServiceTest")
                .dest("getById")
                .msg("0xfffff")
                .build();

        // when 带条件， thenReturn 里面的的返回结果
        Mockito.when(pushLogMapper.getById(id)).thenReturn(pl);
        // 后面是断言语句
        String expMsg = pushLogService.getById(id).getMsg();
        Assertions.assertEquals(expMsg,pl.getMsg());
    }

    @Test
    void save() {

    }
}