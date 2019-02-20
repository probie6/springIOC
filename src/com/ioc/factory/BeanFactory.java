package com.ioc.factory;

import com.ioc.annortation.Autowire;
import com.ioc.componentScann.ComponentScann;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BeanFactory {

    /**
     * bean容器
     */
    private HashMap<String,Object> beanPool;
    /**
     * 某一包下所有文件名
     */
    private HashMap<String,String> componets;

    public BeanFactory(String packageName) {
        beanPool = new HashMap<>();

        componets = ComponentScann.getComponentClassName(packageName);

    }

    /**
     * 根据id获取bean对象
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Object getBean(String id) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(beanPool.containsKey(id)) {
            return beanPool.get(id);
        }
        if(componets.containsKey(id)) {
            Object bean = Class.forName(componets.get(id)).newInstance();

            //属性注入
            bean = assemblMember(bean);

            beanPool.put(id,bean);

            return getBean(id);

        }
        throw new ClassNotFoundException();
    }

    /**
     * 扫描bean 如有Autowire注解 则注入对应bean
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object assemblMember(Object obj) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class c1 = obj.getClass();

        for(Field field : c1.getDeclaredFields()) {
            Autowire at = field.getAnnotation(Autowire.class);

            if(at != null) {
                field.setAccessible(true);
                field.set(obj,getBean(at.id()));
            }
        }
        return obj;
    }

    /**
     * 首字母大写
     * @param name
     * @return
     */
    private static String capteruName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(capteruName("zhangsan"));

    }
}
