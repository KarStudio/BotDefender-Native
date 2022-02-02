# BotDefender-Native
BotDefender 特权应用程序

## Usage

BotDefender-Native 需要配合 BotDefender 使用。

**BotDefender-Native 需要使用 root 权限运行**（或者需要拥有 iptables 和 ipset 的完整权限）

**BotDefender-Native 需要 Java 17+ 才能运行**

## 启动参数

按照常规方式启动即可：

```shell
sudo java -jar BotDefender-Native.jar
```

或者如果想要修改监听端口：
```shell
sudo java -Dport=10086 -jar BotDefender-Native.jar
```
