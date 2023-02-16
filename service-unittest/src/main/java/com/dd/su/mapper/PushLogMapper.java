package com.dd.su.mapper;

import com.dd.su.domain.PushLog;

public interface PushLogMapper {

    PushLog getById(int id);
    PushLog save(PushLog pushLog);
}
