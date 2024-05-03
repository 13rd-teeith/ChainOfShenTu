package dataUtil.systemInfo;

public class StandardData {

    static public final String LOS ="listOfService";
    static public final String ACC = "againstCybercrime";
    static public final String AFA = "anti_fraudAssistant";

    static public final String CC = "classicCases";

    static public final String LOS_HELLO = "机关服务清单";
    static public final String ACC_HELLO = "警惕网络犯罪";
    static public final String AFA_HELLO = "反诈助手";
    static public final String CC_HELLO = "经典案例学习";


    static private final String MODEL_INTERFACE_ING = "http://60.205.214.150:5000/chat";
    static private final String MODEL_INTERFACE_START = "http://60.205.214.150:5000/chat_start";
    static private final String USER_KEY = "Meredith13-___";
    static private final String ORGANIZATION_KEY = "CHECKED_BY_SC_UNIVERSITY";
    static private final String MAIN_FILE =  "./src/main/resources/data/system/user_digest.json";
    static private final String USR_HOME_FILE = "./src/main/resources/data/user";

    static private final String SYS_FILE = "./src/main/resources/data/system";
    //用户通信密钥
    static private final String COMMUNICATION_KEY_128 = "2TnREy7YuXqZdTGE";
    static private final String COMMUNICATION_KEY_192 = "xCSmGGu19V3yvlv9dWL0N03p";
    static private final String COMMUNICATION_KEY_256 = "kd9i9CpYkcMh8yXu29tkwMdXDQtUD4xz";
    static private final String COMMUNICATION_KEY_512 = "m9WMvbPtkWtFnmH3t4Ejs9AwfN8ZyXfYRwlIbJhuScDBxEZazf12f8qhgGmH6Yo2";
    static private final String COMMUNICATION_KEY_1024 = "F+h+Q2Pos69GJGgZmD3TxdapgdaHwogamX+qcXQ18tyaWpi+oorDiMbb9nZLLyupZcwKqjYGGhhyFM3O8sKIjWRpG+9DzSBT76TB4lriaK91r5itx7RZerYUOuU4K/yz";
    static private final String NOT_EXIST = "00";
    static private final String LOGIN_FAILED = "01";

    static public String getUSER_HOME_FILE(String phoneNumber){
        return USR_HOME_FILE + "/" + phoneNumber;
    }

    static public String getPCAP_FILE(String USR_HOME_FILE){
        return USR_HOME_FILE + "/pcap";
    }

    static public String getMAIN_FILE(){
        return MAIN_FILE;
    }

    static public String getOrganizationKey(){
        return ORGANIZATION_KEY;
    }
    static public String getUserKey(){
        return USER_KEY;
    }

    static public String getCommunicationKey_128(){
        return COMMUNICATION_KEY_128;
    }
    static public String getCommunicationKey_192(){
        return COMMUNICATION_KEY_192;
    }
    static public String getCommunicationKey_256(){
        return COMMUNICATION_KEY_256;
    }
    static public String getCommunicationKey_512(){
        return COMMUNICATION_KEY_512;
    }
    static public String getCommunicationKey_1024(){
        return COMMUNICATION_KEY_1024;
    }

    static public String getUserNotExist(){
        return NOT_EXIST;
    }

    static public String getLoginFailed(){
        return LOGIN_FAILED;
    }

    static public String getArticleFile(){
        return SYS_FILE + "/article";
    }
    static public String getArticleIndexFile(){
        return SYS_FILE + "/learnMat.json";
    }

    static public String getModelInterfaceIng(){
        return MODEL_INTERFACE_ING;
    }

    static public String getModelInterfaceStart(){
        return MODEL_INTERFACE_START;
    }

    static public String getSysFile(){
        return SYS_FILE;
    }
}
