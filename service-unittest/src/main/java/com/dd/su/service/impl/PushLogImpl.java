package com.dd.su.service.impl;

import com.dd.su.domain.PushLog;
import com.dd.su.mapper.PushLogMapper;
import com.dd.su.service.PushLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushLogImpl implements PushLogService {

    @Autowired
    PushLogMapper pushLogMapper;

    @Override
    public PushLog getById(int id) {
        return pushLogMapper.getById(id);
    }

    @Override
    public PushLog save(PushLog pushLog) {
        return pushLogMapper.save(pushLog);
    }
}
