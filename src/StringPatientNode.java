public class StringPatientNode<T extends Comparable<T>> extends TreeNode<T> {


    private Patient p;

    public StringPatientNode(Patient p) {
        super((T) p.getPatientId());
        this.p = p;
    }
    public Patient getPatient() {
        return p;
    }
}
