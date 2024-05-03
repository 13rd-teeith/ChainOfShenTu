package dataUtil.Cipher;

public class CommunicationCipher {
    public static String getPasswordHash(final String password){
        //用户密码采用256位加密,并加一串混淆代码
        CipherControllerAES cTool = new CipherControllerAES("256");
        return cTool.getEncrypt(password);
    }

    public static String enMessage_AES128(final String message){
        CipherControllerAES cTool = new CipherControllerAES("128");
        return cTool.getEncrypt(message);
    }

    public static String deMessage_AES128(String message){
        CipherControllerAES cTool = new CipherControllerAES("128");
        return cTool.getDecrypt(message);
    }


    public static String enMessage_AES192(final String message){
        CipherControllerAES cTool = new CipherControllerAES("192");
        return cTool.getEncrypt(message);
    }

    public static String deMessage_AES192(String message){
        CipherControllerAES cTool = new CipherControllerAES("192");
        return cTool.getDecrypt(message);
    }

    public static String enMessage_AES256(final String message){
        CipherControllerAES cTool = new CipherControllerAES("256");
        return cTool.getEncrypt(message);
    }

    public static String deMessage_AES256(String message){
        CipherControllerAES cTool = new CipherControllerAES("256");
        return cTool.getDecrypt(message);
    }
}
