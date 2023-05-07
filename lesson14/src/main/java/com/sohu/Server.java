package com.sohu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("server running at port 8888");
        System.out.println("server main thread="+Thread.currentThread().getName()+Thread.currentThread().getId());
        while(true){
            Socket client = server.accept();
            ServerThread thread = new ServerThread(client);
            thread.start();
            // 简单来说,就是线程没有执行完之前,会一直阻塞在join方法处.也就是说新创建的线程加入到进程中,并马上执行
            thread.join();
            System.out.println("end");
        }
    }
}