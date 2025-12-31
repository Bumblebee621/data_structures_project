public class Clinic {
    private final Doctor doc;
    private int amountInLine;
    private ttTree<Integer> waitingRoom;


    public Clinic(String docID) {
        this.doc = new Doctor(docID);
        this.amountInLine = 0;
        TreeNode<Integer> leftS = new TreeNode<>(0);
        TreeNode<Integer> rightS = new TreeNode<>(2^32 - 1);

    }
    public String getDocId() {
        return doc.getId();
    }
    public Integer getAmountInLine() {
        return amountInLine;
    }

}
