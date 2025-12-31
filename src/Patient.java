public class Patient {
    private final String id;
    private final Doctor doc;
    private int numInLine;

    public Patient(String id, Doctor doc, int numInLine) {
        this.id = id;
        this.doc = doc;
        this.numInLine = numInLine;
    }
    public String getDoctorId(){
        return doc.getId();
    }
    public String getPatientId(){
        return this.id;
    }
    public Integer getNumInLine(){
        return numInLine;
    }
}
