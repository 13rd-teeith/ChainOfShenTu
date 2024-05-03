package shentuChain.controller;

import dataUtil.systemInfo.StandardData;
import dataUtil.Cipher.CommunicationCipher;
import dataUtil.systemInfo.UserDigest;
import dataUtil.systemInfo.UserMain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shentuChain.controller.ctrData.htmlLoginUser;

@RestController
public class UsrLoginCtr {
    @PostMapping("/login_usr")
    public ResponseEntity<String> getUserDetails(@RequestBody htmlLoginUser user) {
        UserMain newer = new UserMain();
        UserDigest checkBody = newer.searchUser(user.phoneNumber);
        if(checkBody == null){
            System.out.println("用户不存在,请先注册");
            return ResponseEntity.ok(StandardData.getUserNotExist());
        }

        //完成对用户密码的哈希
        user.password = CommunicationCipher.getPasswordHash(user.password);


        String check_password = checkBody.getUsrPassword();
        if(check_password.equals(user.password)) {
            System.out.println("口令校验成功");
            return ResponseEntity.ok(CommunicationCipher.enMessage_AES128(user.phoneNumber));
        }else {
            System.out.println("口令校验失败");
            return ResponseEntity.ok(StandardData.getLoginFailed());
        }
    }
}
