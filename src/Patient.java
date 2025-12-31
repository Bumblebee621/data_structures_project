public class Patient {
    private final String patientId;

    public Patient(String id) {
        this.patientId = id;
    }

    public String getId(){
        return this.patientId;
    }
}
