#### 准备工作
```txt
# win10安装snmp服务
https://jingyan.baidu.com/article/4d58d5410969ebdcd4e9c0d8.html

# snmp服务设置
https://www.cnblogs.com/xdp-gacl/p/4011464.html

# 下载snmputil工具
https://soft.3dmgame.com/down/213865.html

# java操作发送snmp报文 maven仓库
https://mvnrepository.com/artifact/org.snmp4j/snmp4j/2.8.2
```
#### 命令
```shell
# 查看本地计算机(IP地址为192.168.0.3)的系统信息
$ ./Snmputil.exe get localhost public .1.3.6.1.2.1.1.1.0
Variable = system.sysDescr.0
Value    = String Hardware: Intel64 Family 6 Model 158 Stepping 10 AT/AT COMPATIBLE - Software: Windows Version 6.3 (Build 19045 Multiprocessor Free)

# 查询计算机连续开机多长时间
$ ./Snmputil.exe get localhost public .1.3.6.1.2.1.1.3.0
Variable = system.sysUpTime.0
Value    = TimeTicks 815316

# 如果我们在对象ID后面不加0，使用getnext参数能得到同样的效果
$ ./Snmputil.exe getnext localhost public .1.3.6.1.2.1.1.3
Variable = system.sysUpTime.0
Value    = TimeTicks 820277

# 查询计算机的联系人
$ ./Snmputil.exe get localhost public .1.3.6.1.2.1.1.4.0
Variable = system.sysContact.0
Value    = String

# 使用walk查询设备上所有正在运行的进程
$ ./Snmputil.exe walk localhost public .1.3.6.1.2.1.25.4.2.1.2

# 查询计算机上面的用户列表
$ ./Snmputil.exe walk localhost public .1.3.6.1.4.1.77.1.2.25.1.1

# Snmputil还有一个trap的参数，主要用来陷阱捕捉，它可以接受代理进程上主动发来的信息。如果我们在命令行下面输入snmputil trap后回车，然后用错误的团体名来访问代理进程，这时候就能收到代理进程主动发回的报告。
```