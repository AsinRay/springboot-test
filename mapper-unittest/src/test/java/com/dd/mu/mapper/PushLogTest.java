package com.dd.mu.mapper;

import com.dd.mu.domain.PushLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @Rollback(value = false)
// @ActiveProfiles("test")

@MybatisTest
public class PushLogTest {

    @Resource
    private PushLogMapper pushLogMapper;

    @Test
    void findByMsgTest() {
        List<PushLog> pushLogList = pushLogMapper.findByMsg("help");

        Assertions.assertEquals(1, pushLogList.size());

        PushLog pl = pushLogList.get(0);

        Assertions.assertEquals("Sys",pl.getSrc());
        Assertions.assertEquals("2002",pl.getDest());
        Assertions.assertEquals("help",pl.getMsg());
        Assertions.assertEquals(LocalDateTime.parse("2022-06-06T12:12:12.00"),pl.getDt());
    }
}
