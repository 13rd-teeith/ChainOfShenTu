package dataUtil.systemInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.logging.Logger;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

//用户数据存储
public class UserMain {
    private static final Logger LOGGER = Logger.getLogger(UserMain.class.getName());
    private final int MAX_SIZE = 20;//允许存储最多的用户数据
    private final String fileName;
    private HashMap<String, UserDigest> usrDig;//用户摘要信息存储

    public UserMain(){
        //初始化hashmap
        fileName = StandardData.getMAIN_FILE();
        usrDig = null;
        loadFromFile();
    }
    //新建用户
    public boolean newUser(UserDigest User){
        //在摘要信息中加入用户信息摘要
        if(usrDig.size() == MAX_SIZE) {
            LOGGER.info("已达到用户注册上限");
            return false;
        }
        if(searchUser(User.getPhoneNumber()) != null){
            LOGGER.info("该用户已注册");
            return false;
        }
        usrDig.put(User.getPhoneNumber(),User);

        //更新数据
        renewUserMain();
        return true;
    }

    //初始化用户摘要信息
    public void loadFromFile(){
        usrDig = new HashMap<>();
        //载入User摘要信息
        try {
            // 读取JSON文件
            FileReader reader = new FileReader(fileName);
            // 使用Gson将JSON对象转换为HashMap
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, UserDigest>>() {}.getType();
            usrDig = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //更新用户摘要信息
    private void renewUserMain(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(usrDig);

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
            System.out.println("UserMain覆写完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //搜索用户摘要信息
    public UserDigest searchUser(String phoneNumber) {
        return usrDig.get(phoneNumber);
    }
    public void print(){
        // 打印解析后的HashMap
        for (String key : usrDig.keySet()) {
            usrDig.get(key).print();
        }
    }


}
