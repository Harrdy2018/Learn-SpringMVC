* windows上netstat的使用
---
![img.png](picture/img.png)
* 程序里面的东西和报文一一对应
---
![img_1.png](picture/img_1.png)
### 客户端与服务器交互的流程
* 三次握手时机
```txt
server 开始以9999端口进行监听
client 发起连接之后 3次握手完成
```
![img_2.png](picture/img_2.png)
* 客户端发送消息
![img_3.png](picture/img_3.png)
* 客户端断开连接&服务端将在缓冲队列中获取的客户端连接也断掉
![img_4.png](picture/img_4.png)