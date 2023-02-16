package com.dd.mu.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class PushLog implements Serializable {

    // content
    private String msg;

    // source
    private String src;
    // dest
    private String dest;

    // data time of the msg
    private LocalDateTime dt;
}
