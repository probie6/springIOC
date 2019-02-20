package com.ioc.controller;

import com.ioc.annortation.Autowire;
import com.ioc.annortation.Componet;
import com.ioc.service.QueryService;

@Componet(id="controller")
public class Controller {

    @Autowire(id="queryService")
    private QueryService queryService;


    public String print() {
        return queryService.query();
    }
}
