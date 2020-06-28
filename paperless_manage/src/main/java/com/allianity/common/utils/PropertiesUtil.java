package com.allianity.common.utils;

import com.allianity.framework.aspectj.lang.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @description:
 * @author: tangh
 * @create: 2020-06-28
 **/
@Component
public class PropertiesUtil {

    public static PropertiesUtil propertiesUtil;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init(){
        propertiesUtil = this;
        propertiesUtil.environment = this.environment;
    }

    public static Environment getEnvironment(){
        return propertiesUtil.environment;
    }

    public static String getProperty(String key){
        String property = getEnvironment().getProperty(key);
        return property;
    }

}