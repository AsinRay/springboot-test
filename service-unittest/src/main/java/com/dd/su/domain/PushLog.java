package com.dd.su.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
public class PushLog implements Serializable {

    private int id;

    // content
    private String msg;

    // source
    private String src;
    // dest
    private String dest;

    // data time of the msg
    private LocalDateTime dt;
}
