package dataUtil.systemInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LearnDataCtr {
    public static class Info{
        public Info(String name, String address, String type){
            this.path = address;
            this.name = name;
            this.type = type;
        }
        public String name, path, type;
    }

    public ArrayList<Info> listOfService;
    public ArrayList<Info> againstCybercrime;
    public ArrayList<Info> anti_fraudAssistant;
    public ArrayList<Info> classicCases;
    public LearnDataCtr(boolean isInit){
        if(isInit) load();
        else{
            listOfService = new ArrayList<>();
            againstCybercrime = new ArrayList<>();
            anti_fraudAssistant = new ArrayList<>();
            classicCases = new ArrayList<>();
        }
    }

    private void load(){
        String set_load = StandardData.getArticleIndexFile();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(set_load));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            LearnDataCtr temp = gson.fromJson(stringBuilder.toString(), new TypeToken<LearnDataCtr>(){}.getType());

            listOfService = temp.listOfService;
            againstCybercrime = temp.againstCybercrime;
            anti_fraudAssistant = temp.anti_fraudAssistant;
            classicCases = temp.classicCases;

            System.out.println("文章目录加载成功");
        } catch (Exception e) {
            System.out.println("文章目录加载失败: " + e.getMessage());
        }
    }

    private void save(){
        String set_load = StandardData.getArticleIndexFile();

        try {
            FileWriter writer = new FileWriter(set_load);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            LearnDataCtr temp = new LearnDataCtr(false);
            temp.classicCases = classicCases;
            temp.anti_fraudAssistant = anti_fraudAssistant;
            temp.listOfService = listOfService;
            temp.againstCybercrime =againstCybercrime;
            String json = gson.toJson(temp);
            writer.write(json);
            writer.close();

            System.out.println("数据成功写入文件");
        } catch (IOException e) {
            System.out.println("数据写入文件失败: " + e.getMessage());
        }
    }

    //根据源地址
    public String add(String src, String tags, String type) throws IOException {
        ArrayList<Info> manage;
        switch (tags) {
            case StandardData.ACC -> manage = againstCybercrime;
            case StandardData.CC -> manage = classicCases;
            case StandardData.AFA -> manage = anti_fraudAssistant;
            case StandardData.LOS -> manage = listOfService;
            default -> {
                System.out.println("执行失败,无对应标签");
                return null;
            }
        }

        String dst = null;
        File file = new File(src);
        String extension = FilenameUtils.getExtension(src);
        String name = FilenameUtils.getBaseName(src);

        if (file.exists()) {
            long timestamp = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String formattedTimestamp = sdf.format(new Date(timestamp));

            //进行索引编号
            dst = StandardData.getArticleFile() + "/" + tags + "/" + formattedTimestamp + "." +extension;
            Info article = new Info(name,dst,type);

            //移动
            Files.move(Path.of(src), Path.of(dst));

            manage.add(article);

            save();

            System.out.println("已将路径:" + src + "文件移至目的地址:" + dst);

        } else {
            System.out.println("此路径下不存在该文件");
        }
        return dst;
    }

    public void del(String tags ,String name){
        ArrayList<Info> manage;
        switch (tags) {
            case StandardData.ACC -> manage = againstCybercrime;
            case StandardData.CC -> manage = classicCases;
            case StandardData.AFA -> manage = anti_fraudAssistant;
            case StandardData.LOS -> manage = listOfService;
            default -> {
                System.out.println("执行失败,无对应标签");
                return;
            }
        }

        Info dst = search(manage,name);
        if(dst == null) {
            System.out.println("不存在此文件");
            return;
        }

        File file = new File(dst.path);
        if (file.exists()) {
            if (file.delete()) {
                manage.remove(dst);
                save();
                System.out.println("已将路径:" + dst + "下文件删除成功");
            } else {
                System.out.println("删除文件失败");
            }
        } else {
            System.out.println("此路径下不存在该文件");
        }
    }

    //根据目录索引(Info数组) 和文章名字进行索引
    private Info search(ArrayList<Info> manage, String name){

        for (Info info : manage) {
            if (name.equals(info.name)) {
                System.out.println("找到对应目标文件");
                return info;
            }
        }
        return null;
    }

    public void print(String tag) {
        ArrayList<Info> manage;
        String hello = null;
        switch (tag) {
            case StandardData.ACC -> {
                manage = againstCybercrime;
                hello = StandardData.ACC_HELLO;
            }
            case StandardData.CC -> {
                manage = classicCases;
                hello = StandardData.CC_HELLO;
            }
            case StandardData.AFA -> {
                manage = anti_fraudAssistant;
                hello = StandardData.AFA_HELLO;
            }
            case StandardData.LOS -> {
                manage = listOfService;
                hello = StandardData.LOS_HELLO;
            }
            default -> {
                System.out.println("无对应标签可输出");
                return;
            }
        }

        System.out.println("======[" + hello + "文章路径如下]======");
        for(int i =1;i<=manage.size();++i){
            Info info = manage.get(i-1);
            System.out.println("文章" + i + ":" + info.name + "\n    文章类型:" + info.type + "\n    路径:" + info.path);
        }
        System.out.println();
    }

    public void print(){
        print(StandardData.ACC);
        print(StandardData.AFA);
        print(StandardData.CC);
        print(StandardData.LOS);
    }
}
