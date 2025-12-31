public class Doctor {
    private final String doctorId;
    private WaitingRoom wr;

    public Doctor(String doctorId) {
        this.doctorId = doctorId;
        wr = new WaitingRoom();
    }
    public String getId(){
        return this.doctorId;
    }
    public WaitingRoom getWaitingRoom(){
        return wr;
    }
}
