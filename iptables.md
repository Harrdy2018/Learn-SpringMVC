### iptables
#### 使用规则
![img.png](picture/img10.png)
#### redis如果远程登录需要放行端口
```txt
# 列举filter表
iptables -t filter -L

# 不指定表默认代表filter
# -I 默认插入在最前面
# -p 协议
# --dport 目的端口
# -j 动作
iptables -I INPUT -p tcp --dport 6379 -j ACCEPT

[root@left ~]# iptables -t filter -L
Chain INPUT (policy ACCEPT)
target     prot opt source               destination
ACCEPT     tcp  --  anywhere             anywhere             tcp dpt:6379
```
#### linux上自由切换用户
```sh
# harrdy切换到root用户并执行
[harrdy@left root]$ echo "centos" | su - root -c /home/harrdy/development/hello.sh
Password: Hello World!

# 希望屏蔽 标准输出 /dev/null 是一个特殊的文件，写入到它的内容都会被丢弃；如果尝试从该文件读取内容，那么什么也读不到
[harrdy@left root]$ echo "centos" | su - root -c /home/harrdy/development/hello.sh > /dev/null
Password:

# 屏蔽标准输出和标准错误输出
echo "centos" | su - root -c /home/harrdy/development/hello.sh > /dev/null 2>&1
```
### crontab定时任务
![crontab.png](picture/crontab.png)
```
yum install crontabs 下载安装
service crond status 查看crontab服务状态
service crond start 手动启动crontab服务
crontab -l 查看定时任务列表
crontab –e 编辑/添加定时任务

# 日志查看
[root@left ~]# tail -f /var/log/cron
May 30 05:24:01 left CROND[7791]: (root) CMD (echo "Hello Wolrd!!!!")
May 30 05:24:01 left CROND[7792]: (harrdy) CMD (echo "centos" | su - root -c /home/harrdy/development/hello.sh)
May 30 05:25:01 left CROND[7846]: (root) CMD (echo "Hello Wolrd!!!!")
May 30 05:25:01 left CROND[7847]: (harrdy) CMD (echo "centos" | su - root -c /home/harrdy/development/hello.sh)
```
