package com.dd.mu;

import com.dd.mu.mapper.PushLogMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MuMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MuMain.class, args);
    }


    @Resource
    PushLogMapper pushLogMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.pushLogMapper.findByMsg("help"));
    }
}
