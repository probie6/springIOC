package com.ioc.service;

import com.ioc.annortation.Autowire;
import com.ioc.annortation.Componet;
import com.ioc.dao.DataAccessInterface;

@Componet(id = "queryService")
public class QueryService {

    @Autowire(id = "dataAccessInterface")
    private DataAccessInterface dao;


    public String query() {
        return dao.queryData();
    }
}
