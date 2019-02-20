package com.ioc;

import com.ioc.annortation.Autowire;

import java.lang.reflect.Field;

public class Person {
    @Autowire(id="person")
    private String name = "张三";
    private int age = 20;

    public static void main(String[] args) throws Exception {
        Person person = getBean();
        System.out.println(person.name);


    }
    private static Person getBean() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object obj = Class.forName("com.ioc.Person").newInstance();
        Class c1 = obj.getClass();

        Field[] fields = c1.getDeclaredFields();

        for(Field field : fields) {
            Autowire at = field.getAnnotation(Autowire.class);
            if(at != null) {
                field.set(obj,"王五");
            }
        }
        return (Person) obj;
    }



}
