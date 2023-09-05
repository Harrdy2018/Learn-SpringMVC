package com.sohu.utils.myjsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class Util {
    public static void listFolderStructure(String username, String password, String host, int port, String command) throws Exception {

        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            /**
             * StrictHostKeyChecking –指示应用程序是否将检查是否可以在已知主机之间找到主机公钥。
             * 另外，可用的参数值是ask，yes和no，其中ask是默认值。如果我们将此属性设置为yes，
             * 那么JSch将永远不会将主机**自动添加到known_hosts文件，
             * 并且它将拒绝连接主机**已更改的主机。这将强制用户手动添加所有新主机。
             * 如果将其设置为 no，那么JSch将自动将新的主机**添加到已知主机列表中
             */
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
