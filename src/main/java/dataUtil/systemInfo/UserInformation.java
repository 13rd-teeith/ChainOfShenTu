package dataUtil.systemInfo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInformation extends UserDigest{
    //外部可通过RiskDataStatistics的方法访问指定数据或完成局部功能
    public transient RiskDataStatistics userRiskData;//风险数据统计

    public transient PcapManage userPcapManage;//风险数据统计

    private String registeredAddress;//户籍地址
    private String emailAddress;//邮箱
    private String ID_Number;//身份证号
    private boolean gender;//性别
    private int age;//年龄
    public UserInformation(String _name, String _phoneNumber, String _password, Boolean _gender,
                           String _ID_Number,String _registeredAddress, String _emailAddress) {
        super(_name, _phoneNumber, _password);

        gender = _gender;
        ID_Number = _ID_Number;
        age = initAgeByID();
        registeredAddress = _registeredAddress;
        emailAddress = _emailAddress;
        userRiskData = new RiskDataStatistics();
        userPcapManage = new PcapManage();
        newUserFile();
    }

    public UserInformation(String phoneNumber){
        super(null,null,null);
        setPhoneNumber(phoneNumber);
        loadFromFile();
    }

    private int initAgeByID(){
        String birthDateStr = ID_Number.substring(6, 14); // 从身份证号中截取出生日期部分
        LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate currentDate = LocalDate.now();
        int _age = currentDate.getYear() - birthDate.getYear();
        // 判断是否已经过了生日
        if (currentDate.getMonthValue() < birthDate.getMonthValue()
                || (currentDate.getMonthValue() == birthDate.getMonthValue()
                && currentDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            _age--; // 还没过生日，年龄减一
        }
        return _age;
    }

    //新建用户目录
    private void newUserFile(){
        final String HOME_FILE = StandardData.getUSER_HOME_FILE(this.getPhoneNumber());
        final String PCAP_FILE = StandardData.getPCAP_FILE(HOME_FILE);

        File folder = new File(HOME_FILE);

        if (folder.mkdirs()) {
            System.out.println("用户目录:" + HOME_FILE + "创建成功");
            //用户信息文件
            folder = new File(HOME_FILE + "/info.json");
            try {
                if (folder.createNewFile()) {
                    System.out.println("用户信息存储done");
                } else {
                    System.out.println("用户信息存储失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //用户数据信息
            folder = new File(HOME_FILE + "/data.json");
            try {
                if (folder.createNewFile()) {
                    System.out.println("用户数据存储done");
                } else {
                    System.out.println("用户数据存储失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //用户数据信息
            folder = new File(PCAP_FILE);
            if (folder.mkdirs()) {
                File pcap = new File(PCAP_FILE + "/pcapManage.json");
                try {
                    if (pcap.createNewFile()) {
                        System.out.println("pcap管理创建done");
                    } else {
                        System.out.println("pcap管理创建失败");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("用户pcap目录:" + PCAP_FILE + "创建成功");
            } else {
                System.out.println("用户pcap目录:" + PCAP_FILE + "创建失败");
            }

        } else {
            System.out.println("用户目录:" + HOME_FILE + "创建失败");
        }

        renewUserInfomation();
        renewData();
        renewPcapManage();
    }

    //每日数据更新，每天零点触发，其他的数据更新可仿写
    public void renewDaily(){
        /*
        * 每日更新数据想一个办法输入，如下给出样例
        */
        int input_1=12,input_2=22,input_3 = 12;

        //更新data.json
        userRiskData.renewRiskPercent(input_1,input_2,input_3);
        //覆写
        renewData();
    }


    private void renewUserInfomation(){

        final String HOME_FILE = StandardData.getUSER_HOME_FILE(this.getPhoneNumber());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        try (FileWriter fileWriter = new FileWriter(HOME_FILE + "/info.json")) {
            fileWriter.write(json);
            System.out.println("用户信息写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renewData(){
        final String HOME_FILE = StandardData.getUSER_HOME_FILE(this.getPhoneNumber());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this.userRiskData);

        try (FileWriter fileWriter = new FileWriter(HOME_FILE + "/data.json")) {
            fileWriter.write(json);
            System.out.println("风险信息写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //暂时未写pcapManage的管理类,被方法不调用
    private void renewPcapManage(){
        final String HOME_FILE = StandardData.getUSER_HOME_FILE(this.getPhoneNumber());
        final String PCAP_FILE = StandardData.getPCAP_FILE(HOME_FILE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userPcapManage);
        try (FileWriter fileWriter = new FileWriter(PCAP_FILE + "/pcapManage.json")) {
            fileWriter.write(json);
            System.out.println("风险信息写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读入信息
    public void loadFromFile(){
        //获取用户根目录
        final String HOME_FILE = StandardData.getUSER_HOME_FILE(this.getPhoneNumber());

        //载入UserInfo信息
        try {
            // 读取JSON文件
            FileReader reader = new FileReader(HOME_FILE + "/info.json");
            // 使用Gson将JSON对象转换为HashMap
            Gson gson = new Gson();
            Type type = new TypeToken<UserInformation>() {}.getType();
            UserInformation tempInfo = gson.fromJson(reader, type);
            this.gender = tempInfo.gender;
            this.ID_Number = tempInfo.ID_Number;
            this.emailAddress = tempInfo.emailAddress;
            this.registeredAddress = tempInfo.registeredAddress;
            this.age = tempInfo.age;
            this.setUsrName(tempInfo.getUsrName());
            this.setPhoneNumber(tempInfo.getPhoneNumber());
            this.setUsrPassword(tempInfo.getUsrPassword());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //载入RiskData
        try {
            FileReader reader = new FileReader(HOME_FILE + "/data.json");
            Gson gson = new Gson();
            Type type = new TypeToken<RiskDataStatistics>() {}.getType();
            this.userRiskData = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //载入PcapManage
        try {
            FileReader reader = new FileReader(StandardData.getPCAP_FILE(HOME_FILE) + "/pcapManage.json");
            Gson gson = new Gson();
            Type type = new TypeToken<PcapManage>() {}.getType();
            userPcapManage = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void print(){
        System.out.println("姓名: " + getUsrName());
        System.out.println("电话: " + getPhoneNumber());
        System.out.println("户籍地址: " + registeredAddress);
        System.out.println("邮箱: " + emailAddress);
        System.out.println("身份证号: " + ID_Number);
        System.out.println("性别: " + (gender ? "男" : "女"));
        System.out.println("年龄: " + age);

        userPcapManage.print();
        userRiskData.print();
    }

    public Boolean getGender(){
        return gender;
    }
}
