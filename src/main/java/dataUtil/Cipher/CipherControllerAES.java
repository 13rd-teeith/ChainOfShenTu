package dataUtil.Cipher;

import dataUtil.systemInfo.StandardData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

//加密控制
public class CipherControllerAES {
    private final byte[] KEY;
    public CipherControllerAES(String mode) {
        byte[] temp;
//        System.out.println("使用" + mode + "位密钥进行AES加密");
        if(mode.equals("128")) {
            temp = StandardData.getCommunicationKey_128().getBytes();
        }else if(mode.equals("192")){
            temp = StandardData.getCommunicationKey_192().getBytes();
        }else if(mode.equals("256")){
            temp = StandardData.getCommunicationKey_256().getBytes();
        }else{
            System.out.println("无适用密钥长度,默认采用128位进行加密");
            temp = StandardData.getCommunicationKey_128().getBytes();
        }
        this.KEY = Arrays.copyOf(temp, temp.length);
    }
    public String getEncrypt(String plain){
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec key = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getDecrypt(String cipher){
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec key = new SecretKeySpec(KEY, "AES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(cipher));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
