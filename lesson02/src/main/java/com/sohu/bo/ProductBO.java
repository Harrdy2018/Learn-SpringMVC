package com.sohu.bo;

/**
 * 用于测试配置文件的
 * <bean id="productBO" class="com.sohu.bo.ProductBO" init-method="myInit" destroy-method="myDestroy"/>
 */
public class ProductBO {
    private String productName;

    public ProductBO() {
        System.out.println("执行了构造器函数");
    }

    public void myInit(){
        System.out.println("execute myInit");
    }

    public void myDestroy(){
        System.out.println("execute myDestroy");
    }

    public void ppPrint(){
        System.out.println("HelloWorld");
    }
}
