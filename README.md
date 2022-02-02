# BotDefender-Native
BotDefender 特权应用程序

## 🚧 正在施工中

当前项目正在施工，部分功能可能不可用，请勿在生产环境中使用。

## 客制化工具

此项目为设计为在 KarNetwork 下使用的客制化工具，可能无法满足所有人的需求，请谨慎使用。

## 特点
* 使用 ipset 和 iptables 对 IP 进行屏蔽，性能优秀
* 使用 gRPC 和插件进行通信，避免 Minecraft 服务端运行在特权模式下造成安全风险
* 支持控制台手动操作

## 环境

* **不支持 Windows 操作系统，节哀顺变**
* 需要配合 [BotDefender](https://github.com/KarStudio/BotDefender) 使用。
* **需要使用 root 权限运行** （或者需要拥有 iptables 和 ipset 的完整权限）
* 系统已正确安装 iptables 和 ipset 工具

## 启动参数

按照常规方式启动即可：

```shell
sudo java -jar BotDefender-Native.jar
```

或者如果想要修改监听端口：
```shell
sudo java -Dport=10086 -jar BotDefender-Native.jar
```
