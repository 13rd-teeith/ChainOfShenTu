package dataUtil.utilTest;


import dataUtil.systemInfo.LearnDataCtr;
import dataUtil.systemInfo.StandardData;

import java.io.File;
import java.io.IOException;

//文档管理测试
public class Test_2 {
    public static void main(String[] args) throws IOException {

        //若为索引json空或损坏需要重置, 则应用false启动; 若json完整正确,用true启动
        LearnDataCtr ctrTool = new LearnDataCtr(true);
        ctrTool.print();
        String folderPath = "C:/Users/dell/Desktop/刑案/in"; // 指定文件夹路径
        String tag="专业学习";
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        ctrTool.add(folderPath + "/" +file.getName(), StandardData.ACC,tag);
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("文件夹为空或无法读取文件列表");
            }
        } else {
            System.out.println("指定路径不是一个文件夹或文件夹不存在");
        }
    }
}
