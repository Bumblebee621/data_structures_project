public class WaitingRoom {
    private int numInLine;
    public WaitingRoom(){
        this.numInLine = 0;
    }
    public boolean addPatient(Patient patient) {
        numInLine++;
        TreeNode temp = new PatientNode(patient, );
    }
}
