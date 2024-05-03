package shentuChain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataUtil.Cipher.CommunicationCipher;
import dataUtil.systemInfo.LearnDataCtr;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class LearnOline {
    static class getPost{
        public String info;
    }

    @PostMapping("/learn_ol")
    public String getUserDetails(@RequestBody getPost post) {
        if(post.info == null){
            System.out.println("请求为空");
        }

        if(post.info.equals("learnReady")){
            LearnDataCtr info = new LearnDataCtr(true);
            //加密发送使用192位进行传输
            for(LearnDataCtr.Info item: info.classicCases){
                item.path = CommunicationCipher.enMessage_AES192(item.path);
            }
            for(LearnDataCtr.Info item: info.anti_fraudAssistant){
                item.path = CommunicationCipher.enMessage_AES192(item.path);
            }
            for(LearnDataCtr.Info item: info.againstCybercrime){
                item.path = CommunicationCipher.enMessage_AES192(item.path);
            }
            for(LearnDataCtr.Info item: info.listOfService){
                item.path = CommunicationCipher.enMessage_AES192(item.path);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(info);
        }
        else{
            System.out.println("请求错误,无法上传学习资料");
            return null;
        }
    }
}
