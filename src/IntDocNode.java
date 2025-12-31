public class IntDocNode<T extends Comparable<T>> extends TreeNode<T> {
    private Clinic clinic;
    public IntDocNode(Clinic clinic) {
        super((T) clinic.getAmountInLine());
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }


}
