package shentuChain.controller;

import dataUtil.systemInfo.StandardData;
import dataUtil.systemInfo.UserDigest;
import dataUtil.systemInfo.UserInformation;
import dataUtil.systemInfo.UserMain;

import dataUtil.Cipher.CommunicationCipher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shentuChain.controller.ctrData.htmlUser;

@RestController
public class UserRegisterCtr {
    final String USER_KEY = StandardData.getUserKey();
    UserMain usrMain;

    @PostMapping("/user")
    public ResponseEntity<Boolean[]> getUserDetails(@RequestBody htmlUser user) {
        //第一位布尔值判断是否通过口令,第二位是判断通过口令后注册成功与否
        Boolean[] result = new Boolean[2];
        result[0] = false;
        result[1] = false;
        if(USER_KEY.equals(user.shentuKey)) {
            result[0] = true;
            System.out.println("通关口令正确");
            usrMain = new UserMain();

            //完成对用户密码的哈希
            user.usrPassword = CommunicationCipher.getPasswordHash(user.usrPassword);
            UserDigest newer = new UserDigest(user.usrName,user.phoneNumber,user.usrPassword);
            result[1] = usrMain.newUser(newer);

            UserInformation userInfo = new UserInformation(
                    user.usrName, user.phoneNumber,
                    user.usrPassword, user.gender,
                    user.ID_Number,
                    user.registeredAddress, user.emailAddress
            );

        }else {System.out.println("通关口令错误");}
        return ResponseEntity.ok(result);
    }
}
