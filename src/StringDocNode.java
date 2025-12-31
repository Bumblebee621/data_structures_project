public class StringDocNode <T extends Comparable<T>> extends TreeNode<T> {


    private Clinic clinic;
    public StringDocNode(Clinic clinic) {
        super((T) clinic.getDocId());
        this.clinic = clinic;
    }
    public Clinic getClinic() {
        return clinic;
    }
}
