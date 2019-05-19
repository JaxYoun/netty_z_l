# Thrift框架同时完成了对象的序列化和socket通信两大RPC基础功能。
# 在Windows系统下（由于只使用编译功能，其他功能可以通过依赖jar包的方式引入），可以跳过繁琐的安装步骤，只下载编译器：thrift-0.12.0.exe，然后将其改名为thrift.exe配置好系统环境变量就可使用。
## thrift传输格式-protocol
- TBinaryProtocol 二进制格式，传输效率高
- TCompactProtocol 压缩格式，数据比二进制更紧凑，传输效率更高【推荐】
- TJSONProtocol JSON格式
- TSimpleJSONProtocol 提供JSON只写协议，生成的文件很容易通过脚本语言解析，缺少描述信息程序不可解析，项目中极少使用
- TDebugProtocol 使用易懂的可读的文本格式，在传输过程中截获后易于分析，以便debug
## thrift数据传输方式-transport
- TSocket 阻塞式的，类似于bio的socket，比较低效
- TFramedTranspot 以frame为单位进行传输，同时也是非阻塞式通信【推荐】
- TFileTransport 以文件形式进行传输
- TMemoryTransport 将内存用于IO，在java实现中底层使用了简单的ByteArrayOutputStream
- TZlibTransport 使用zilib进行压缩，于其他传输方式配合使用（目前无java实现）
## thrift支持的服务模型-server
- TSimpleServer 简单的单线程服务模型
- TThreadPoolServer 多线程服务模型，使用标准的阻塞式IO
- TNonblockingServer 多线程服务模型，使用非阻塞式IO（需要配合TFramedTransport进行数据传输）
- THsHaServer THsHa引入了线程池去处理，其模型把读写任务放到线程池去处理【推荐】
* Hs用于处理IO事件（accept、read、write）
* Ha用于handler对RPC的同步处理