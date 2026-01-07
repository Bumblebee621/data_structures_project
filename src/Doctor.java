public class Doctor {
    private final String doctorId;
    private DVNTreeI<Patient> queue;
    private int lastestNum;
    private String nextPatientId;

    public Doctor(String doctorId, Integer leftSentinelValue, Integer rightSentinelValue) {
        this.doctorId = doctorId;
        queue = new DVNTreeI<>(leftSentinelValue, rightSentinelValue);
        lastestNum = 0;
        nextPatientId = null;
    }
    public String getId(){
        return this.doctorId;
    }
    public DVNTreeI<Patient> getQueue(){
        return queue;
    }

    public int getAndPlusLastestNum() {
        return lastestNum++;
    }
    public String getNextPatientId() {
        return nextPatientId;
    }
    public void setNextPatientId(String nextPatientId) {
        this.nextPatientId = nextPatientId;
    }

}
