# RMI（Java内置标准，只支持Java）
## 用户端调用的方法在另一个远程进程中。
## 远程方法调用不止序列化目标参数，同时也要序列化模型对象及函数，反序列化会做类似目标代码生成的工作，然后执行生成的代码。
### client -> stub .....net...... skeleton -> server
### 1.客户端将目标过程及参数发给stub。
### 2.stub将目标过程和参数序列化为字节码，经过网络传输给skeleton，
### 3.skeleton将字节码反序列化为目标代码。
### 4.生成的目标代码在服务端执行。
### 5.执行结果经过逆向序列化与反序列化回到客户端。

# RPC（支持跨语言）
#### a.WebService调用：可以认为是RPC，的一种但是其效率不及RPC，主要原因是在编解码和网络传输协议上，WebService通常用HTTP，而RPC则直接用TCP.
#### b.REST接口调用：不能算RPC，因为其传递的只是参数。
## 1.定义接口说明文件，它描述了对象、字段、接口方法等一系列信息（是一个文本文件，独立于语言）。
## 2.通过RPC框架根据接口说明文件编译生成对应语言的程序代码。
## 3.客户端和服务器端分别引入RPC框架生成的代码，两端都可想调用本地程序一样使用远程代码了。
# 安装
## 1.到github下载Win64的编译器
## 2.引入java核心依赖库compile ('com.google.protobuf:protobuf-java:3.6.1')
## 3.一如java工具库compile ('com.google.protobuf:protobuf-java-util:3.7.1')
# 简单测试
## 1.使用protoc工具根据IDL生成Java代码：/f/netty_z_l/src/main/java/protobuf$ protoc --java_out=.. Student.proto
## 2.使用生成的类构造对象并设置属性。
## 3.将生成的对象使用protobuf序列化为二进制的byte[]。
## 4.将二进制的byte[]通过protobuf反序列化为对象。
## 5.调用反序列化得到的对象的方法。