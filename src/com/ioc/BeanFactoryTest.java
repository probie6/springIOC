package com.ioc;

import com.ioc.controller.Controller;
import com.ioc.factory.BeanFactory;
import com.ioc.service.QueryService;

public class BeanFactoryTest {

    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory("com.ioc");

        Controller controller = (Controller) beanFactory.getBean("controller");

        System.out.println(controller.print());

    }
}
