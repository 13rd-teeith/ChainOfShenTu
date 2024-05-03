package dataUtil.utilTest;
import dataUtil.systemInfo.UserInformation;
import dataUtil.systemInfo.UserMain;

/*
用于用户文件新建、文件覆写的测试
*/
public class Test_0 {
    public static void main(String[] args){
        //启动新建用户示例前应删除user目录下的数据，在测试环节并未加入文件路径存在性检测

        //新建用户
        UserMain tool = new UserMain();
        UserInformation info = new UserInformation("黎静安","19183534777","12345", false,
                "511303199705036327","四川大学江安校区","2511011112@qq.com");


        info = new UserInformation("白听荷","14522847426","12345",false,
                "510184199201128347","四川大学江安校区","1281018032@qq.com");
    }
}
