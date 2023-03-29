package com.sohu;

import org.junit.Test;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 自定义网络类加载器
 */
class MyURLClassLoader extends ClassLoader {
    private String url;

    public MyURLClassLoader(String url) {
        this.url = url;
    }

    public MyURLClassLoader(ClassLoader parent, String url) {
        super(parent);
        this.url = url;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String path = this.url+"/"+name.replace(",", "/")+".class";
        URL url2 = null;
        try {
            url2 = new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try(InputStream in = url2.openStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte buf[] = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            byte data[] = bos.toByteArray();
            return super.defineClass(name, data,0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyFileClassLoader extends ClassLoader {
    /**
     * 被加载的类所在的目录
     */
    private String directory;

    public MyFileClassLoader(String directory) {
        this.directory = directory;
    }

    public MyFileClassLoader(ClassLoader parent, String directory) {
        super(parent);
        this.directory = directory;
    }

    /**
     *
     * @param name com.sohu.HelloWorld
     *
     * @return r
     * @throws ClassNotFoundException c
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 将类名转换为目录
        String file = this.directory+ File.separator+name.replace(".", File.separator)+".class";

        try(InputStream in = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte buf[] = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            byte data[] = bos.toByteArray();
            return super.defineClass(name, data,0, data.length);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


public class TestClassLoader {
    @Test
    public void display1(){
        // 根类加载器
        ClassLoader c1 = Object.class.getClassLoader();
        System.out.println(c1);
    }

    @Test
    public void display2(){
        // 扩展类加载器
        ClassLoader c1 = DNSNameService.class.getClassLoader();
        System.out.println(c1);
    }

    @Test
    public void display3(){
        // 自定义类加载器
        ClassLoader c1 = TestClassLoader.class.getClassLoader();
        System.out.println(c1);
        System.out.println("---------------------------");
        while (c1 != null) {
            System.out.println(c1);
            c1 = c1.getParent();
        }
    }

    /**
     * 测试自定义类加载器
     * 将编好的com.sohu.HelloWorld.class文件放到J盘根目录 J:\com\sohu\HelloWorld.class
     */
    @Test
    public void display4() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyFileClassLoader classLoader = new MyFileClassLoader("J:/");
        Class<?> clazz = classLoader.loadClass("com.sohu.HelloWorld");
        clazz.newInstance();
    }

    /**
     * 双亲委派机制限制自定义文件类加载器重复加载
     */
    @Test
    public void display5() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyFileClassLoader classLoader1 = new MyFileClassLoader("J:/");
        Class<?> clazz1 = classLoader1.loadClass("com.sohu.HelloWorld");
        System.out.println(classLoader1+">>>>"+clazz1.hashCode());

        MyFileClassLoader classLoader2 = new MyFileClassLoader(classLoader1,"J:/");
        Class<?> clazz2 = classLoader2.loadClass("com.sohu.HelloWorld");
        System.out.println(classLoader2+">>>>"+clazz2.hashCode());
    }

    /**
     * 避开双亲委派机制类重复加载
     */
    @Test
    public void display6() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MyFileClassLoader classLoader1 = new MyFileClassLoader("J:/");
        Class<?> clazz1 = classLoader1.findClass("com.sohu.HelloWorld");
        System.out.println(classLoader1+">>>>"+clazz1.hashCode());

        MyFileClassLoader classLoader2 = new MyFileClassLoader(classLoader1,"J:/");
        Class<?> clazz2 = classLoader2.findClass("com.sohu.HelloWorld");
        System.out.println(classLoader2+">>>>"+clazz2.hashCode());
    }
}
