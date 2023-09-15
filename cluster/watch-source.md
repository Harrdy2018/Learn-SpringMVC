## 看集群源码学习知识
### ```kafka topics```
```shell
# /opt/module/kafka/bin/kafka-topics.sh

## 看主题操作脚本
#!/bin/bash
exec $(dirname $0)/kafka-run-class.sh kafka.admin.TopicCommand "$@"

### 收获
### 比如执行列举所有主题 /opt/module/kafka/bin/kafka-topics.sh --bootstrap-server hadoop102:9092 --list
### $0 当前执行脚本全路径 /opt/module/kafka/bin/kafka-topics.sh
### dirname $0 用于取指定路径所在的目录 如果你在控制台执行 dirname /opt/module/kafka/bin/kafka-topics.sh 返回 /opt/module/kafka/bin
### $(dirname $0) 返回该命令的结果 也可以写成 `dirname $0`
### $@ 所有可选参数 --bootstrap-server hadoop102:9092 --list
### exec exec是用新的进程去代替原先的进程,原先的进程就消失了,但是进程号不变
```
```shell
# /opt/module/kafka/bin/kafka-run-class.sh

## 看主题核心操作流程
if [ $# -lt 1 ];
then
  echo "USAGE: $0 [-daemon] [-name servicename] [-loggc] classname [opts]"
  exit 1
fi

### 收获
### $# 可选参数个数
### -lt 关系运算符 检测左边的数是否小于右边的，如果是，则返回 true
if [ $a -lt $b ]
then
   echo "$a -lt $b: a 小于 b"
else
   echo "$a -lt $b: a 不小于 b"
fi

### =~ 前面能否通过"CYGWIN"正则匹配上
# CYGWIN == 1 if Cygwin is detected, else 0.
if [[ $(uname -a) =~ "CYGWIN" ]]; then
  CYGWIN=1
else
  CYGWIN=0
fi

### -z 检测字符串长度是否为0，为0返回 true
if [ -z "$INCLUDE_TEST_JARS" ]; then
  INCLUDE_TEST_JARS=false
fi
```
