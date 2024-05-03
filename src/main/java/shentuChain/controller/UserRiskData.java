package shentuChain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dataUtil.Cipher.CommunicationCipher;
import dataUtil.systemInfo.RiskDataStatistics;
import dataUtil.systemInfo.StandardData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

@RestController
public class UserRiskData {
    @PostMapping("/getRiskData")
    public ResponseEntity<String> getUserDetails(@RequestBody Html get) {
        get.phoneNumber = CommunicationCipher.deMessage_AES128(get.phoneNumber);

        String path = StandardData.getUSER_HOME_FILE(get.phoneNumber) + "/data.json";

        RiskDataStatistics retData = null;
        //解析data path读入用户风险数据
        try {
            // 读取JSON文件
            FileReader reader = new FileReader(path);

            Gson gson = new Gson();
            Type type = new TypeToken<RiskDataStatistics>() {}.getType();
            retData = gson.fromJson(reader, type);

            System.out.println("查找用户数据为:");
            retData.print();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gsonMake = new GsonBuilder().setPrettyPrinting().create();

        return ResponseEntity.ok(gsonMake.toJson(retData));
    }

    static private class Html{
        public String phoneNumber;
    }
}
