## 1.对于只传递多种类型的对象时直接在发送端和接收端声明范型就可以了
## 2.对于需要传递多种类型的对象时有两种方法可以解决
- 1.通过netty的自定义协议机制来实现，即在协议头信息中标注数据的类型（依赖netty，不能跨语言）。
- 2.通过protoBuff提供的包装类机制，将类型信息和数据对象放在包装类中来传递，读写都针对包装对象来进行（依赖protoBuff，可以跨语言）。

## 对于多种数据类型，也可以通过类型映射等方式，比如构造一个mapping，做路由映射。

## 假如VCS为git，如何共享代码
### 1.git的submodule机制：git仓库中的嵌套仓库，内部仓库具有很好的独立性。通过proto生成的java代码放到内部仓库中，在服务端和客户端都保存内部仓库，并将目录嵌入到各自主项目的目录中。
### 生成代码的更新只需要到对应的目录执行git命令就可以了。同时，idl文件应该独立成一个仓库（用户端、客户端、idl、生成的代码四个仓库）。
- 容易引发内外层分支切换的混乱，导致人员误操作。
- 对生成源码仓库的意外性修改。
- git-dubModule本身比较笨重。
### 2.git-subTree，内层和外层仍然只有一个，但是目录是嵌套的。