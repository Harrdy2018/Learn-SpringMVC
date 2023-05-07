package com.sohu;

import com.sohu.util.GetParm;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Request {
    private Socket client;
    private BufferedReader br;
    private String url;

    private String method;

    private String protocol;

    private Map<String, String> map;

    private GetParm getParm;

    public Request() {
    }

    public Request(Socket client) {
        this.client = client;
        map = new HashMap<>();
        getParm = new GetParm();

        try {
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String firstLine = br.readLine();
            String split[] = firstLine.split(" ");
            method = split[0];
            url = split[1];
            protocol = split[2];
            // 解析url，分析参数
            if (method.equalsIgnoreCase("get")) {
                if (url.contains("?")) {
                    String[] split2 = url.split("[?]");
                    url = split2[0];
                    // 参数行
                    String property = split2[1];
                    map = getParm.getParm(property);
                }
            } else {
                int length = 0;
                while(br.ready()){
                    String line = br.readLine();
                    if (line.contains("Content-Length")) {
                        String[] split2 = line.split(" ");
                        length = Integer.parseInt(split2[1]);
                    }
                    if(line.equals("")){
                        break;
                    }
                }
                String info = null;
                char[] ch = new char[length];
                br.read(ch, 0, length);
                info = new String(ch, 0, length);
                map = getParm.getParm(info);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
