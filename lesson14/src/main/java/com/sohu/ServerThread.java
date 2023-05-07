package com.sohu;

import java.net.Socket;

public class ServerThread extends Thread{
    private Socket client;
    public ServerThread() {}
    public ServerThread(Socket client){
        this.client = client;
    }
    @Override
    public void run() {
        System.out.println("server thread="+Thread.currentThread().getName()+Thread.currentThread().getId());
        Request request = new Request(client);
        Response response = new Response(client);
        response.forward(request.getUrl());
    }
}
