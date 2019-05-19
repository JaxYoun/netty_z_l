package thrift.server;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.gencode.SonService;

/**
 * @author: Yang
 * @date: 2019/5/19 19:33
 * @description:
 */
public class Server {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8888);

        //类似netty中的eventLoopGroup，half-sync-half-async的服务器
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).minWorkerThreads(2).maxWorkerThreads(4);
        SonService.Processor<SonServiceImpl> processor = new SonService.Processor<>(new SonServiceImpl());

        //网络协议层IP（此处用的是二进制压缩）
        arg.protocolFactory(new TCompactProtocol.Factory());
        //数据传输层MAC
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Started");
        server.serve();
    }

}
