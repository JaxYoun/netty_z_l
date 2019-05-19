package thrift.client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import thrift.gencode.Son;
import thrift.gencode.SonService;

/**
 * @author: Yang
 * @date: 2019/5/19 19:43
 * @description:
 */
public class Client {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8888), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        SonService.Client client = new SonService.Client(protocol);

        try {
            transport.open();
            Son son = client.getSonByUserName("yang");
            System.out.println(son.getName());
            System.out.println(son.getAge());
            System.out.println(son.isMarried());
            System.out.println("-----------");

            Son song = new Son();
            song.setMarried(false);
            song.setName("luo");
            song.setAge(28);
            client.saveSon(song);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }

}
