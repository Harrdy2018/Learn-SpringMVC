package com.sohu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server01 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("server start listening port at 9999 ....");
        Socket client = server.accept();
        System.out.println("client= " + client.getClass());

        // 将客户端发送的消息从socket里面读到内存
        InputStream inputStream = client.getInputStream();
        System.out.println("inputStream= "+inputStream.getClass());
        byte buffer[] = new byte[1024];
        int len=0;
        while ((len=inputStream.read(buffer))!=-1){
            System.out.println(new String(buffer, 0, len));
        }

        OutputStream outputStream=client.getOutputStream();
        outputStream.write("Hello Client01.".getBytes());
        // 设置结束标志
        client.shutdownOutput();

        inputStream.close();
        client.close();
        server.close();
    }
}
