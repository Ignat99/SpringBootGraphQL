package com.ochiengolanga.tuts.bootgraphql.utils;

import org.springframework.stereotype.Component;

@Component
public abstract class LogService extends ComponentLogs {

    public static final String SERVICE_LABEL = "Service";

    public LogService() {
        this.objectType = SERVICE_LABEL;
        this.objectName = this.getClass().getSimpleName();
    }

}
