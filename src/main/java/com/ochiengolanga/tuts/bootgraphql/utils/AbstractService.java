package com.ochiengolanga.tuts.bootgraphql.utils;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractService extends ComponentLogs {

    public static final String SERVICE_LABEL = "Service";

    public AbstractService() {
        this.objectType = SERVICE_LABEL;
        this.objectName = this.getClass().getSimpleName();
    }

}
