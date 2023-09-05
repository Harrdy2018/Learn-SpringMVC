package com.sohu;

import com.sohu.utils.myjsch.Util;
import com.sohu.utils.sshd.Util2;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.Test;

public class Example {
    @Test
    public void display1(){
        System.out.println("myssh");
    }

    /**
     * http://www.jcraft.com/jsch/
     * JSch is a pure Java implementation of SSH2.
     * JSch allows you to connect to an sshd server and use port forwarding, X11 forwarding, file transfer, etc.,
     * and you can integrate its functionality into your own Java programs. JSch is licensed under BSD style license.
     */
    @Test
    public void test1() throws Exception {
        String username = "root";
        String password = "centos";
        String host = "192.168.10.102";
        int port = 22;
        String command = "ls";
        Util.listFolderStructure(username, password, host, port, command);
    }

    @Test
    public void test2() throws Exception {
        String username = "root";
        String password = "centos";
        String host = "192.168.10.102";
        int port = 22;
        long defaultTimeoutSeconds = 10;
        String command = "ls"+"\r\n";
        Util2.listFolderStructure(username, password, host, port, defaultTimeoutSeconds, command);
    }

    @Test
    public void test3() throws Exception {
        String username = "root";
        String password = "centos";
        String host = "192.168.10.102";
        int port = 22;
        long defaultTimeoutSeconds = 10;
        String command = "touch test.txt";
        Util2.execlistFolderStructure(username, password, host, port, defaultTimeoutSeconds, command);
    }
}
