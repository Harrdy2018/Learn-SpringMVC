## 部署k8s
### 部署Kubernetes Master主节点
```shell
# 关掉swapoff
swapoff -a

## 永久关闭swapoff
swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab

## 永久关闭Selinux
setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config

## 设置 net.bridge.bridge-nf-call-iptables 值为1
cat <<EOF > /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
net.ipv6.conf.all.disable_ipv6=1
EOF
sysctl --system

# 重置kubeadm
kubeadm reset

#
kubeadm init  --apiserver-advertise-address=192.168.10.132 --image-repository registry.aliyuncs.com/google_containers --kubernetes-version v1.19.4  --service-cidr=10.96.0.0/12 --pod-network-cidr=10.244.0.0/16

# 创建的时候可以看到按照提示
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

[root@k8smaster ~]# kubectl get nodes
NAME        STATUS   ROLES    AGE     VERSION
k8smaster   Ready    master   3m44s   v1.19.4

# 在工作节点执行，加入到集群 在init的时候可以看到
kubeadm join 192.168.10.132:6443 --token 5oex8w.zql0bpylwtijsoj7 \
    --discovery-token-ca-cert-hash sha256:a10de17748c8c2ab49000e8b19d7456e5889b5021476781fa890dd264a0e3827
    
# 部署网络插件
wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yaml
kubectl apply -f kube-flannel.yaml
# 删除yaml描述的资源
kubectl delete -f kube-flannel.yaml

# 查看所有运行时的容器
[root@k8smaster ~]# kubectl get nodes
NAME        STATUS   ROLES    AGE   VERSION
k8smaster   Ready    master   28m   v1.19.4
k8snode     Ready    <none>   28m   v1.19.4
[root@k8smaster ~]# kubectl get pods --all-namespaces
NAMESPACE     NAME                                READY   STATUS              RESTARTS   AGE
kube-system   coredns-6d56c8448f-2l6zc            0/1     ContainerCreating   0          28m
kube-system   coredns-6d56c8448f-mfr74            0/1     ContainerCreating   0          28m
kube-system   etcd-k8smaster                      0/1     Running             1          28m
kube-system   kube-apiserver-k8smaster            1/1     Running             1          28m
kube-system   kube-controller-manager-k8smaster   0/1     Running             1          28m
kube-system   kube-proxy-6nm4l                    1/1     Running             1          28m
kube-system   kube-proxy-vb7hk                    1/1     Running             0          28m
kube-system   kube-scheduler-k8smaster            0/1     Running             1          28m
[root@k8smaster ~]# kubectl get pods -n kube-system
NAME                                READY   STATUS              RESTARTS   AGE
coredns-6d56c8448f-2l6zc            0/1     ContainerCreating   0          28m
coredns-6d56c8448f-mfr74            0/1     ContainerCreating   0          28m
etcd-k8smaster                      0/1     Running             1          29m
kube-apiserver-k8smaster            1/1     Running             1          29m
kube-controller-manager-k8smaster   0/1     Running             1          29m
kube-proxy-6nm4l                    1/1     Running             1          28m
kube-proxy-vb7hk                    1/1     Running             0          28m
kube-scheduler-k8smaster            1/1     Running             1          29m
```
### ```cat kube-flannel.yaml```
```yaml
---
kind: Namespace
apiVersion: v1
metadata:
  name: kube-flannel
  labels:
    k8s-app: flannel
    pod-security.kubernetes.io/enforce: privileged
---
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  labels:
    k8s-app: flannel
  name: flannel
rules:
- apiGroups:
  - ""
  resources:
  - pods
  verbs:
  - get
- apiGroups:
  - ""
  resources:
  - nodes
  verbs:
  - get
  - list
  - watch
- apiGroups:
  - ""
  resources:
  - nodes/status
  verbs:
  - patch
- apiGroups:
  - networking.k8s.io
  resources:
  - clustercidrs
  verbs:
  - list
  - watch
---
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  labels:
    k8s-app: flannel
  name: flannel
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: flannel
subjects:
- kind: ServiceAccount
  name: flannel
  namespace: kube-flannel
---
apiVersion: v1
kind: ServiceAccount
metadata:
  labels:
    k8s-app: flannel
  name: flannel
  namespace: kube-flannel
---
kind: ConfigMap
apiVersion: v1
metadata:
  name: kube-flannel-cfg
  namespace: kube-flannel
  labels:
    tier: node
    k8s-app: flannel
    app: flannel
data:
  cni-conf.json: |
    {
      "name": "cbr0",
      "cniVersion": "0.3.1",
      "plugins": [
        {
          "type": "flannel",
          "delegate": {
            "hairpinMode": true,
            "isDefaultGateway": true
          }
        },
        {
          "type": "portmap",
          "capabilities": {
            "portMappings": true
          }
        }
      ]
    }
  net-conf.json: |
    {
      "Network": "10.244.0.0/16",
      "Backend": {
        "Type": "vxlan"
      }
    }
---
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: kube-flannel-ds
  namespace: kube-flannel
  labels:
    tier: node
    app: flannel
    k8s-app: flannel
spec:
  selector:
    matchLabels:
      app: flannel
  template:
    metadata:
      labels:
        tier: node
        app: flannel
    spec:
      affinity:
        nodeAffinity:
          requiredDuringSchedulingIgnoredDuringExecution:
            nodeSelectorTerms:
            - matchExpressions:
              - key: kubernetes.io/os
                operator: In
                values:
                - linux
      hostNetwork: true
      priorityClassName: system-node-critical
      tolerations:
      - operator: Exists
        effect: NoSchedule
      serviceAccountName: flannel
      initContainers:
      - name: install-cni-plugin
        image: docker.io/flannel/flannel-cni-plugin:v1.1.2
       #image: docker.io/rancher/mirrored-flannelcni-flannel-cni-plugin:v1.1.2
        command:
        - cp
        args:
        - -f
        - /flannel
        - /opt/cni/bin/flannel
        volumeMounts:
        - name: cni-plugin
          mountPath: /opt/cni/bin
      - name: install-cni
        image: docker.io/flannel/flannel:v0.22.0
       #image: docker.io/rancher/mirrored-flannelcni-flannel:v0.22.0
        command:
        - cp
        args:
        - -f
        - /etc/kube-flannel/cni-conf.json
        - /etc/cni/net.d/10-flannel.conflist
        volumeMounts:
        - name: cni
          mountPath: /etc/cni/net.d
        - name: flannel-cfg
          mountPath: /etc/kube-flannel/
      containers:
      - name: kube-flannel
        image: docker.io/flannel/flannel:v0.22.0
       #image: docker.io/rancher/mirrored-flannelcni-flannel:v0.22.0
        command:
        - /opt/bin/flanneld
        args:
        - --ip-masq
        - --kube-subnet-mgr
        resources:
          requests:
            cpu: "100m"
            memory: "50Mi"
        securityContext:
          privileged: false
          capabilities:
            add: ["NET_ADMIN", "NET_RAW"]
        env:
        - name: POD_NAME
          valueFrom:
            fieldRef:
              fieldPath: metadata.name
        - name: POD_NAMESPACE
          valueFrom:
            fieldRef:
              fieldPath: metadata.namespace
        - name: EVENT_QUEUE_DEPTH
          value: "5000"
        volumeMounts:
        - name: run
          mountPath: /run/flannel
        - name: flannel-cfg
          mountPath: /etc/kube-flannel/
        - name: xtables-lock
          mountPath: /run/xtables.lock
      volumes:
      - name: run
        hostPath:
          path: /run/flannel
      - name: cni-plugin
        hostPath:
          path: /opt/cni/bin
      - name: cni
        hostPath:
          path: /etc/cni/net.d
      - name: flannel-cfg
        configMap:
          name: kube-flannel-cfg
      - name: xtables-lock
        hostPath:
          path: /run/xtables.lock
          type: FileOrCreate
```
### 错误集锦
![img.png](picture/k8s1.png)
```shell
# 没执行
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```
* 展示一个资源组的详细信息
```shell
kubectl describe pods/kube-scheduler-k8smaster -n kube-system
kubectl describe nodes k8snode
```
* 部署一个pod
```shell
kubectl expose deployment httpd22 --port=80 --type=NodePort
# 暴露端口
kubectl expose deployment httpd22 --port=80 --type=NodePort
kubectl get pod,svc
[root@k8smaster home]# kubectl get services
NAME         TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
httpd22      NodePort    10.96.167.243    <none>        80:31402/TCP     2m55s
kubernetes   ClusterIP   10.96.0.1        <none>        443/TCP          42m

## 外网访问
http://192.168.10.132:31402/   # master 结点
http://192.168.10.133:31402/   # ndoe 结点
```
* 正确删除一个pod
```shell
# 我们想要正常且彻底的删除一个pod，必须要先破坏掉他的容灾机制，即删除deployment机制。
# 查看deployment信息
kubectl get deployment -o wide --all-namespaces
# 删除deployment配置
kubectl delete deployment httpd -n default
# 删除deployment，pod会随之删除

# 直接删除pod等于重启
# 可是这里你会发现，在进行删除delete pod后，并不会直接删除。该pod会自动重新构建（可以理解为重启、重构），原因是k8s误认为我们要删除的pod异常挂了，会启用容灾机制，导致重新再拉起一个新的pod
[root@k8smaster home]# kubectl get pods -o wide
NAME                      READY   STATUS    RESTARTS   AGE   IP           NODE      NOMINATED NODE   READINESS GATES
httpd22-94cb4cddd-h5jnt   1/1     Running   0          13m   10.244.1.3   k8snode   <none>           <none>
[root@k8smaster home]# kubectl delete pod httpd22-94cb4cddd-h5jnt -n default
pod "httpd22-94cb4cddd-h5jnt" deleted
[root@k8smaster home]# kubectl get pods -o wide
NAME                      READY   STATUS    RESTARTS   AGE   IP           NODE      NOMINATED NODE   READINESS GATES
httpd22-94cb4cddd-sdk8n   1/1     Running   0          25s   10.244.1.4   k8snode   <none>           <none>
```
