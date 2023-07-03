## Linux中的重定向
### 输出重定向
```txt
# 将命令的正常输出结果保存到指定的文件中，而不是直接显示在显示器的屏幕上
# > 覆盖       >> 追加

cat /proc/cpuinfo > cpu.txt
uname -a >> cpu.txt
# 清空文件
> cpu.txt
```
### 输入重定向
```shell
# 将命令中接收输入的途径由默认的键盘改为其他文件，而不是等待从键盘输入
[root@hadoop101 home]# grep root < /etc/passwd
root:x:0:0:root:/root:/bin/bash
operator:x:11:0:operator:/root:/sbin/nologin

mysql -uroot -proot < xuegod.sql

mysql -uroot -proot << EOF
> show databases;
> EOF

[root@hadoop101 home]# cat > a.txt <<EOF
> A
> B
> C
> EOF
[root@hadoop101 home]#
[root@hadoop101 home]# cat a.txt
A
B
C
```
### 标准错误重定向```2>```
```shell
ls xxx 2> a.txt
```
