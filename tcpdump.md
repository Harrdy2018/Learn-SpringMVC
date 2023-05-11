### tcpdump的安装
![img_4.png](picture/img_4.png)
```txt
Linux 安装tcpdump以CentOS为例
https://www.jianshu.com/p/e5f67833ef2b
```
### tcpdump的使用
```shell
# -i 表示监听接口
tcpdump -i ens33

# 打印经过此主机或者离开此主机的包
tcpdump -i ens33 host 主机IP
# -n 不转换端口和地址(都用数字表示)
tcpdump -i ens33 -n -v host 192.168.182.128

# 打印ping包
tcpdump -i ens33 'icmp[icmptype] == icmp-echo or icmp[icmptype] == icmp-echoreply'
# -v 详情
tcpdump -i ens33 -v 'icmp[icmptype] == icmp-echo or icmp[icmptype] == icmp-echoreply'

# 捕获主机192.168.182.128与其他主机之间（不包括www.baidu.com）通信的ip数据包
tcpdump -i ens33 -n -v ip host 192.168.182.128 and not www.baidu.com

# 捕获主机 192.168.182.128 和主机 192.168.182.1或192.168.182.211的所有通信数据包
tcpdump -i ens33 -n -v host 192.168.182.128 and 192.168.182.1 or 192.168.182.211

# 捕获主机192.168.182.128接收和发出的tcp协议的ssh的数据包
tcpdump -i ens33 -n -v tcp port 22 and host 192.168.182.128

# 抓取ens33经过的icmp协议的数据包
tcpdump -i ens33 -n -v icmp

# 抓取ens33网卡icmp协议，源地址是 192.168.182.128 数据报文
tcpdump -i ens33 -n -v icmp and src 192.168.182.128

# 写文件  拉取远程服务器文件->"scp(secure copy) root@192.168.182.128/home/harrdy/icmp.pcap 本地路径"
tcpdump -i ens33 -n -v icmp and src 192.168.182.128 -w icmp.pcap
# 读文件
tcpdump icmp -r icmp.pcap
```
