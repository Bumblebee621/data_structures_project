public class IntPatientNode<T extends Comparable<T>> extends TreeNode<T> {
    private Patient p;
    public IntPatientNode(Patient p) {
        super((T) p.getNumInLine());
        this.p = p;
    }
    public Patient getPatient() {
        return p;
    }
}
