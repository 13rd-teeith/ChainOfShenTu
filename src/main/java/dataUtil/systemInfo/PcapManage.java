package dataUtil.systemInfo;

public class PcapManage {
    PcapManage(){
        pcapNumber = new int[recentDayNumber];
        for(int i = 0; i < 30;++i){
            pcapNumber[i] = 0;
        }
    }

    private final int recentDayNumber = 30;
    private int[] pcapNumber;

    //day   是指近day天的数据
    public void setPcapNumber(int day, int pcapNumber){
        this.pcapNumber[day] = pcapNumber;
    }

    public void print() {
        System.out.println("近30天的 pcap 数据管理信息:");
        for (int i = 0; i < recentDayNumber; i++) {
            System.out.println("第 " + (i + 1) + " 天的 pcap 数量: " + pcapNumber[i]);
        }
    }
}
