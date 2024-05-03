package shentuChain.controller.ctrData;

public class UserDisplay {
    public String PCAP_PATH;
    public String DATA_PATH;
    public String usrName;
    public boolean gender;
    public UserDisplay(String PCAP_PATH,String DATA_PATH,String usrName,Boolean gender){
        this.DATA_PATH = DATA_PATH;
        this.PCAP_PATH = PCAP_PATH;
        this.gender = gender;
        this.usrName = usrName;
    }
}
