## docker学习
### 概念
```txt
镜像(image)

容器(container)
通过镜像创建容器
启动、停止、删除

仓库(repository)
存放镜像的地方
仓库分为公有仓库和私有仓库，默认是国外的，要配置镜像加速
```
### 安装docker
```txt
# 帮助文档
https://docs.docker.com/engine/install/centos/

# 卸载老的版本
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine

# 安装工具包和设置仓库地址
sudo yum install -y yum-utils
# /etc/yum.repos.d/目录下加了repo仓库 不要走国外的镜像，走阿里云镜像
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 更新yum软件包索引
yum makecache fast

# 安装docker相关
sudo yum install docker-ce docker-ce-cli containerd.io

# 启动docker
sudo systemctl start docker
sudo systemctl status docker

# 测试 刚开始没有镜像，他会去拉镜像
sudo docker run hello-world
```
### 配置阿里云镜像加速器
![img.png](picture/docker1.png)
```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://pbrojcu0.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

