public class Doctor {
    private final String doctorId;
    private DVNTreeI queue;
    private int lastestNum;


    public Doctor(String doctorId, Integer leftSentinelValue, Integer rightSentinelValue) {
        this.doctorId = doctorId;
        queue = new DVNTreeI(leftSentinelValue, rightSentinelValue);
        lastestNum = 0;
    }
    public String getId(){
        return this.doctorId;
    }
    public DVNTreeI getQueue(){
        return queue;
    }
}
