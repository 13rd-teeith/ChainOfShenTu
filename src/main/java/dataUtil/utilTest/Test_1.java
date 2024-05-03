package dataUtil.utilTest;


import dataUtil.Model.ShenTuLegal_OpenAI;

import java.io.IOException;
import java.util.Scanner;


//大模型交流的接口测试
public class Test_1 {

    public static void main(String[] args) throws IOException {
        ShenTuLegal_OpenAI model = new ShenTuLegal_OpenAI();
        Scanner in = new Scanner(System.in);

        String myMessage = null;

        ShenTuLegal_OpenAI.Message rec = model.init();

        System.out.println(rec.message);
        System.out.println("本次对话id:" + rec.user_id +"\n输入:STOP终止对话");
        while(true) {
            System.out.print("我:");
            myMessage = in.nextLine();
            if(myMessage.equals("STOP")){
                break;
            }
            ShenTuLegal_OpenAI.Message get = model.conversation(myMessage,rec.user_id);
            System.out.println("神荼法律助手:" + get.message);
        }
    }
}
