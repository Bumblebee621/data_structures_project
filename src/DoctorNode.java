public class DoctorNode extends TreeNode {
    private final Doctor doc;

    public DoctorNode(Doctor doc) {
        this.doc = doc;
    }
    public String getDocId() {
        return doc.getId();
    }
    public WaitingRoom getWaitingRoom() {
        return doc.getWaitingRoom();
    }
}
