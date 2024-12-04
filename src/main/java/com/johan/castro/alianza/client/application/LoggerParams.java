package com.johan.castro.alianza.client.application;

import org.apache.log4j.MDC;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerParams {

    private static LoggerParams loggerParams;
    private final SimpleDateFormat sdf;
    private String ip;
    private String email;
    private String timeZone;

    private LoggerParams() {
        sdf = new SimpleDateFormat("yyy-MM-dd'T'hh:mm:ss");
        ip = "-";
        email = "-";
    }

    public static LoggerParams getInstance() {
        if (loggerParams == null)
            loggerParams = new LoggerParams();
        return loggerParams;
    }

    public void build(String ip, String email, String timeZone) {
        this.ip = ip;
        this.email = email;
        this.timeZone = timeZone;
    }

    public void setEvent(String event) {
        MDC.put("ip", (this.ip == null ? "-" : this.ip));
        MDC.put("event", (event == null ? "-" : event));
        MDC.put("email", (this.email == null ? "-" : this.email));
        MDC.put("timeZone", (this.timeZone == null ? "-" : this.timeZone));
        MDC.put("ISO8601", sdf.format(new Date(System.currentTimeMillis())));
    }

}