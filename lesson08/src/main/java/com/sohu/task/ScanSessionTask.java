package com.sohu.task;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TimerTask;

/**
 * 在任务中应该扫描容器，容器在监听器上，只能传递进来了。
 * 要想得到在监听器上的锁，也只能是传递进来
 */
public class ScanSessionTask extends TimerTask {
    private List<HttpSession> sessions;
    private Object lock;

    public ScanSessionTask(List<HttpSession> sessions, Object lock) {
        this.sessions = sessions;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            //遍历容器
            for (HttpSession session : sessions) {
                //只要15秒没人使用，我就移除它啦
                if (System.currentTimeMillis() - session.getLastAccessedTime() > (1000 * 15)) {
                    session.invalidate();
                    sessions.remove(session);
                }
            }
        }
    }
}
