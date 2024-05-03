package shentuChain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataUtil.Cipher.CommunicationCipher;
import dataUtil.systemInfo.LearnDataCtr;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shentuChain.controller.addSrc.WebConfig;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class LearnOlineDown {
    static class getPost{
        public String path;
        public String type;
    }

    @PostMapping("/learn_ol_down")
    public String getUserDetails(@RequestBody getPost post) {
        //地址解码
        post.path = CommunicationCipher.deMessage_AES192(post.path);

        if (post.path.length() >= 5 && post.path.startsWith("LIST-")) {
            System.out.println("服务清单无需映射");
            post.path = post.path.substring(5);
        } else {
            System.out.print("已将本机地址:" + post.path);
            String input = post.path;
            post.path = input.replaceFirst("\\./src/main/resources/data/system/", "/main/");
            System.out.println("映射到服务器可访问地址:" + post.path);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(post);
    }
}
