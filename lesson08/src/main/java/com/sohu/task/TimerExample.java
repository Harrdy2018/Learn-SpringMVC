package com.sohu.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {
    public static void timer1(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第一种方法：实现 TimerTask 任务，指定在两秒后执行");
            }
        }, 2000);
    }

    public static void timer2(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第二种方法：实现 TimerTask 任务，指定在两秒后循环执行，每次执行完后间隔五秒");
            }
        }, 2000, 5000);
    }

    public static void timer3(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第三种方法：实现 TimerTask 任务，指定在两秒后循环执行，每次执行完后间隔五秒");
            }
        }, 2000, 5000);
    }

    public static void timer4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);     // 控制时
        calendar.set(Calendar.MINUTE, 0);           // 控制分
        calendar.set(Calendar.SECOND, 0);           // 控制秒
        Date time = calendar.getTime();             // 得出执行任务的时间,此处为今天的12：00：00
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("第四种方法：实现 TimerTask 任务，指定在某个时间点循环执行，每次执行完间隔二十四小时");
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }

    public static void main(String[] args) {
        // timer1();
        // timer2();
        // timer3();
        timer4();
    }
}
