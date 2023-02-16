package com.dd.su.service;

import com.dd.su.domain.PushLog;

public interface PushLogService {
    PushLog getById(int id);
    PushLog save(PushLog pushLog);
}
