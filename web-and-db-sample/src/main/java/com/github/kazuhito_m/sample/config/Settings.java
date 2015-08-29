package com.github.kazuhito_m.sample.config;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Properties;

public class Settings {

    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String flywaySqlLocation;

    public static Settings get() {
        Settings obj = new Settings();
        try {
            Properties prop = new Properties();
            String propFileName = obj.getClass().getSimpleName().toLowerCase()
                    + ".properties";
            prop.load(Settings.class.getResourceAsStream(propFileName));
            setValueBeanByProperties(obj, prop);
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    private static void setValueBeanByProperties(Object bean,
                                                 Properties prop) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        for (Object name : Collections.list(prop.propertyNames())) {
            PropertyDescriptor desc = new PropertyDescriptor(name.toString(),
                    bean.getClass());
            desc.getWriteMethod().invoke(bean,
                    new Object[]{prop.get(name)});
        }
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getFlywaySqlLocation() {
        return flywaySqlLocation;
    }

    public void setFlywaySqlLocation(String flywaySqlLocation) {
        this.flywaySqlLocation = flywaySqlLocation;
    }

}
