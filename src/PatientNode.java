public class PatientNode extends TreeNode {
    private final Patient patient;
    private final Doctor doc;
    private int numInLine;

    public PatientNode(Patient p, Doctor doc, int numInLine) {
        this.patient = p;
        this.doc = doc;
        this.numInLine = numInLine;
    }
    public String getDoctorId(){
        return doc.getId();
    }
    public String getPatientId(){
        return patient.getId();
    }
}
