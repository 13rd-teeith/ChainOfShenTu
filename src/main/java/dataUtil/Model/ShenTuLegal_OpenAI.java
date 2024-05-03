package dataUtil.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dataUtil.systemInfo.StandardData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ShenTuLegal_OpenAI {

    public Message init(){
        Message retMessage = null;
        try {
            URL url = new URL(StandardData.getModelInterfaceStart());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求为 GET
            connection.setRequestMethod("GET");
            // 获取响应
            int responseCode = connection.getResponseCode();

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Type type = new TypeToken<Message>() {}.getType();

            Gson gson = new Gson();
            retMessage = gson.fromJson(response.toString(), type);
            retMessage.message = URLDecoder.decode(retMessage.message, StandardCharsets.UTF_8);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMessage;
    }

    public Message conversation(String send, String uid) throws IOException {

        String get = null;
        //转码为UTF-8
        send = URLEncoder.encode(send, StandardCharsets.UTF_8);

        URL url = new URL(StandardData.getModelInterfaceIng());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法为POST
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        // 使用Gson库构建JSON对象
        Gson gson = new Gson();
        String postData = gson.toJson(new Message(send,uid));

        // 发送POST请求
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(postData);
        outputStream.flush();
        outputStream.close();

        // 读取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response;
        StringBuilder responseBuilder = new StringBuilder();
        while ((response = reader.readLine()) != null) {
            responseBuilder.append(response);
        }
        reader.close();

        //解析为消息
        String json_str = responseBuilder.toString();
        Type type = new TypeToken<Message>() {}.getType();
        Message retMessage = gson.fromJson(json_str, type);
        retMessage.message = URLDecoder.decode(retMessage.message, StandardCharsets.UTF_8);
        retMessage.user_id = uid;
        return retMessage;
    }

    public void deleteConversation(){

    }

    public static class Message {
        public String message;
        public String user_id;
        Message(String message, String user_id){
            this.message = message;
            this.user_id = user_id;
        }
    }
}
