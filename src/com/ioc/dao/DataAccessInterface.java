package com.ioc.dao;

import com.ioc.annortation.Componet;

@Componet(id = "dataAccessInterface")
public class DataAccessInterface {

    public String queryData() {
        return "query result";
    }
}
