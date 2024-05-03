package shentuChain.controller;

import dataUtil.Cipher.CommunicationCipher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComByDecrypt {
    @PostMapping("/decrypt")
    public ResponseEntity<StrInfo> getUserDetails(@RequestBody StrInfo get) {
        if(get.info == null) return null;
        System.out.println("解密前消息:"+get.info);
        get.info = CommunicationCipher.deMessage_AES128(get.info);
        System.out.println("解密后消息:"+get.info);
        return ResponseEntity.ok(get);
    }

    private static class StrInfo{
        public String info;
    }
}
