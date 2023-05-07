package com.sohu;

import java.io.*;
import java.net.Socket;

public class Response {
    private String absolutePath = "J:/development/Learn-SpringMVC/lesson14/src/source";

    private Socket client;
    private PrintStream ps;
    private String path = null;

    public Response() {
    }

    public Response(Socket client) {
        super();
        this.client = client;
        try {
            ps = new PrintStream(client.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void forward() throws FileNotFoundException {
        // 响应(把服务器上有的资源用流传给客户端)
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(path));
            ps.println("HTTP/1.1 200 OK");
            ps.println();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                ps.write(buf, 0, len);
                ps.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void forward(String url) {
        /*
         * url处理分3种情况 1.为空：返回默认资源 2.不空存在，返回该资源 3.不空不存在，返回错误信息
         */
        if (url.equals("/")) {
            path = this.absolutePath+"/2.jpg";
        } else {
            path = this.absolutePath + url;
            File file = new File(path);
            if (!file.exists()) {
                path = this.absolutePath+"/error.html";
            }
        }
        try {
            forward();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
