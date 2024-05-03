package shentuChain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataUtil.Model.ShenTuLegal_OpenAI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatByOpenAI {

    ShenTuLegal_OpenAI  model;

    ChatByOpenAI(){
        model = new ShenTuLegal_OpenAI();
    }

    @PostMapping("/chat_ing")
    public String chat(@RequestBody ShenTuLegal_OpenAI.Message get) throws IOException {
        System.out.println("id为:" + get.user_id + "的对话");
        System.out.println("用户消息:" + get.message);

        //post请求获取
        ShenTuLegal_OpenAI.Message info = model.conversation(get.message,get.user_id);
        System.out.println("模型消息:" + info.message);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(info);
    }
    @GetMapping("/chat_begin")
    public String chatInit() throws IOException {
        ShenTuLegal_OpenAI.Message info = model.init();
        System.out.println("对话初始化成功,对话id:" + info.user_id);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(info);
    }


}
