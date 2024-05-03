package dataUtil.systemInfo;

public class RiskDataStatistics {

    //风险数据类别数目
    private final int classNumber = 5;

    //定义近期是几天
    private final int recentDayNumber = 30;

    //isPre = true则不用初始化，直接从文件中读取
    RiskDataStatistics(){
        MID_RiskPercent = new int[recentDayNumber];
        HIGH_RiskPercent = new int[recentDayNumber];
        LOW_RiskPercent = new int[recentDayNumber];
        LevelPercent = new int[classNumber];
    }


    //近30风险变化数据
    private int[] LOW_RiskPercent;
    private int[] MID_RiskPercent;
    private int[] HIGH_RiskPercent;

    //三十日各类风险数据占比
    private int[] LevelPercent;

    //更新每日风险百分比数据
    public void renewRiskPercent(int LOW_0 ,int MID_0, int HIGH_0){
        for(int i =1; i<30;++i){
            MID_RiskPercent[i] = MID_RiskPercent[i-1];
            HIGH_RiskPercent[i] = HIGH_RiskPercent[i-1];
            LOW_RiskPercent[i] = LOW_RiskPercent[i-1];
        }
        MID_RiskPercent[0] = MID_0;
        LOW_RiskPercent[0] = LOW_0;
        HIGH_RiskPercent[0] = HIGH_0;
    }

    //获取近 i 日的风险百分比数据
    public int getLowRiskPercent(int i){
        return LOW_RiskPercent[i-1];
    }
    public int getMiddleRiskPercent(int i){
        return MID_RiskPercent[i-1];
    }
    public int getHighRiskPercent(int i){
        return HIGH_RiskPercent[i-1];
    }

    public void print() {
        System.out.println("近30天风险变化数据:");
        for (int i = 0; i < recentDayNumber; i++) {
            System.out.println("近 " + (i + 1) + " 天 低/中/高 等风险等级: " +
                    LOW_RiskPercent[i] + "/" + MID_RiskPercent[i] + "/" + HIGH_RiskPercent[i]);
        }

        System.out.println("三十日各类风险数据占比:");
        for (int i = 0; i < classNumber; i++) {
            System.out.println("第 " + (i + 1) + " 类风险数据占比: " + LevelPercent[i] + "%");
        }
    }
}
