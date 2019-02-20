package com.ioc.componentScann;

import com.ioc.annortation.Componet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentScann {

    public static HashMap<String,String> getComponentClassName(String packagName) {
        List<String> classes = getClassName(packagName);

        HashMap<String,String> components = new HashMap<>();

        try {
            for(String c1 : classes) {
                Componet component = Class.forName(c1).getAnnotation(Componet.class);
                if(component != null) {
                    components.put(component.id(),c1);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();

        }
        return components;
    }

    private static List<String> getClassName(String packagName) {
        String filePath = ClassLoader.getSystemResource("").getPath()+packagName.replace(".",File.separator);
        List<String> fileNames = getClassName(filePath, packagName.replace(".",File.separator));
        return fileNames;

    }

    private static List<String> getClassName(String filePath,String packagName) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();

        for(File childFile : childFiles) {
            if(childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(),packagName));
            } else {
                String childFilePath = childFile.getPath();
                String classForName = childFilePath.substring(childFilePath.indexOf(packagName),childFilePath.lastIndexOf(".")).replace("\\",".");
                myClassName.add(classForName);
            }
    }
        return myClassName;

    }

    public static void main(String[] args) throws Exception {

        getComponentClassName("com.ioc");
    }
}
