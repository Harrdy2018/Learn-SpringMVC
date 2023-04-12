package com.sohu.design;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    // 添加观察者
    void addObserver(Observer obj);

    // 移除观察者
    void deleteObserver(Observer obj);

    // 当主题方法改变时，这个方法会被调用，通知所有的观察者
    void notifyObserver();
}

interface Observer {
    // 主题状态改变时，更新通知
    void update(int version);
}

class MagazineSubject implements Subject {
    // 存放订阅者
    private List<Observer> observers = new ArrayList<>();

    private int version;

    @Override
    public void addObserver(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void deleteObserver(Observer obj) {
        int i = observers.indexOf(obj);
        if (i>0) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : this.observers) {
            observer.update(this.version);
        }
    }

    public void publish(){
        this.version++;
        this.notifyObserver();
    }
}

class CustomerObserver implements Observer {
    private String name;

    private int version;

    public CustomerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(int version) {
        this.version = version;
        System.out.println("该杂志出新版本了");
        this.buy();
    }

    public void buy(){
        System.out.println(this.name+" 购买了第 "+this.version+" 期杂志");
    }
}

public class Example1 {
    public static void main(String[] args) {
        // 创建主题(被观察者)
        MagazineSubject magazineSubject = new MagazineSubject();

        // 创建三个不同的观察者
        Observer A = new CustomerObserver("A");
        Observer B = new CustomerObserver("B");
        Observer C = new CustomerObserver("C");

        // 将观察者注册到主题中
        magazineSubject.addObserver(A);
        magazineSubject.addObserver(B);
        magazineSubject.addObserver(C);

        magazineSubject.publish();

        magazineSubject.deleteObserver(B);
        magazineSubject.publish();
    }
}
