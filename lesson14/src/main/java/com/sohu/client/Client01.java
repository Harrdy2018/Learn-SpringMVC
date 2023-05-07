package com.sohu.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client01 {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("client= "+client.getClass());

        OutputStream outputStream=client.getOutputStream();
        System.out.println("outputStream= "+outputStream.getClass());
        outputStream.write("Hello Server01.".getBytes());
        // 设置结束标志
        client.shutdownOutput();

        InputStream inputStream = client.getInputStream();
        byte buffer[] = new byte[1024];
        int len=0;
        while ((len=inputStream.read(buffer))!=-1){
            System.out.println(new String(buffer, 0, len));
        }

        outputStream.close();
        client.close();
        System.out.println("client exit...");
    }
}
