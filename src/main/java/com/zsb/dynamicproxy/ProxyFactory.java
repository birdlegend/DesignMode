package com.zsb.dynamicproxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object invoke(){
        com.zsb.dynamicproxy.Proxy temp = new com.zsb.dynamicproxy.Proxy();
        return Proxy.newProxyInstance(MyInterface.class.getClassLoader(),new Class[]{MyInterface.class},temp);
    }
}
