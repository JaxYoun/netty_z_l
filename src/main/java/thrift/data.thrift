//0.定义包名，注意：此处的包名一定要与最终代码在项目中的位置相匹配
namespace java thrift.gencode

//1.定义数据类型别名
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

//2.定义实体类
struct Son {
    1: optional String name,
    2: optional int age,
    3: optional boolean married
}

//3.定义异常
exception DataException {
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

//4.定义服务接口
service SonService {
    #定义接口方法
    Son getSonByUserName(1: required String name) throws (1: DataException dataException),

    #定义接口方法
    void saveSon(1: required Son son) throws (1: DataException dataException)
}

//5.执行命令，生成源码：D:\ProgramFiles\thrift\thrift-0.12.0.exe --gen java data.thrift #由于没有安装，所以直接通过路径调用了编译器。
//6.protoBuff为所有类生成一个总的源文件，各个类以内部类的形式来组织，而thrift则为每个一类都生成一个源文件。