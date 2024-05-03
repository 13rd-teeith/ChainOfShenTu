package shentuChain.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataUtil.Cipher.CommunicationCipher;
import dataUtil.systemInfo.StandardData;
import dataUtil.systemInfo.UserInformation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shentuChain.controller.ctrData.UserDisplay;

@RestController
public class UserAfterLogin {
    static class htmlPhoneNumber{
        public String phoneNumber;
    }

    @PostMapping("/apply_usr_data")
    public String getUserDetails(@RequestBody htmlPhoneNumber get) {
        if(get.phoneNumber == null){ return null;}

        System.out.println("原始号码数据" + get.phoneNumber);
        get.phoneNumber = CommunicationCipher.deMessage_AES128(get.phoneNumber);
        System.out.println("解析号码数据" + get.phoneNumber);

        UserInformation usrInfo = new UserInformation(get.phoneNumber);
        final String HOME_PATH = StandardData.getUSER_HOME_FILE(get.phoneNumber);
        final String PCAP_PATH = StandardData.getPCAP_FILE(HOME_PATH);

        UserDisplay ret =  new UserDisplay(PCAP_PATH,HOME_PATH + "/data.json",usrInfo.getUsrName(),usrInfo.getGender());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(ret);
    }
}
