package thrift.server;

import org.apache.thrift.TException;
import thrift.gencode.DataException;
import thrift.gencode.Son;
import thrift.gencode.SonService;

/**
 * @author: Yang
 * @date: 2019/5/19 17:49
 * @description:
 */
public class SonServiceImpl implements SonService.Iface {

    @Override
    public Son getSonByUserName(String name) throws DataException, TException {
        System.out.println("=======" + name);
        Son son = new Son();
        son.setAge(1);
        son.setName("yang");
        son.setMarried(true);
        return son;
    }

    @Override
    public void saveSon(Son son) throws DataException, TException {
        System.out.println("=======save");
        System.out.println(son.getAge());
        System.out.println(son.getName());
        System.out.println(son.isMarried());
    }
}
