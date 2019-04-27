package protobuf;

import protobuf.generated.DataInfo;

/**
 * @author: Yang
 * @date: 2019/4/27 21:59
 * @description: 在同一个jvm进程中：先构造一个对象，将对象序列化，然后再反序列化的到对象
 */
public class ProtobufTest {

    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("yang").setAge(30).setAddress("xdh").build();

        //关键就在此字节数据组，它可以在跨进程、跨主机、跨语言传输，然后通过ProtoBuf反序列化得到原对象，再调用从而实现RPC
        byte[] toByteArr = student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(toByteArr);
        System.out.println(student.getName());
        System.out.println(student1.getName());
    }

}
