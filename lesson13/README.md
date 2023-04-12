## lesson13
* spring容器启动
### 观察者模式
```txt
1、概述
观察者模式又称为发布/订阅(Publish/Subscribe)模式
观察者设计模式涉及到两种角色：主题（Subject）和观察者（Observer）

2、Subject模块
Subjec模块有3个主要操作
addObserver()：注册添加观察者（申请订阅）
deleteObserver()：删除观察者（取消订阅）
notifyObserver()：主题状态发生变化时通知所有的观察者对象
3、Oserver模块
Oserver模块有1个核心操作update()，当主题Subject状态改变时,将调用每个观察者的update()方法，更新通知。
```