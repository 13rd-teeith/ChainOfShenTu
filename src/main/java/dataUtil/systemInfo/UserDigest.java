package dataUtil.systemInfo;

//用户数据摘要
public class UserDigest {
    public UserDigest(String _name, String _phoneNumber, String _password){
        name = _name;
        phoneNumber = _phoneNumber;
        password = _password;
    }
    private String name;//姓名
    private String phoneNumber;//电话
    private String password;//口令

    public void setUsrName(String name){this.name = name;}
    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber;}
    public void setUsrPassword(String password){this.password = password;}

    public String getUsrName(){return this.name;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getUsrPassword(){return password;}

    public void print(){
        System.out.println(this.name +
                "-" + this.password +
                "-" + this.password +
                "-" + this.phoneNumber);
    }

}
