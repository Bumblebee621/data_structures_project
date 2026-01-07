public class DVNTreeS<P> extends DVNTree<P, String> {

    public DVNTreeS(String leftSentinelValue, String rightSentinelValue) {
        super(leftSentinelValue, rightSentinelValue);
    }

    protected Node<P> createSentinel(String value) {
        return new Node<>(null, value, 0, -1); // value is String id, num is 0
    }
    protected Node<P> getLeft(Node<P> node) {
        return node.getLeftById();
    }
    protected Node<P> getMid(Node<P> node) {
        return node.getMidById();
    }
    protected Node<P> getRight(Node<P> node) {
        return node.getRightById();
    }
    protected Node<P> getParent(Node<P> node) {
        return node.getParentById();
    }
    protected String getKey(Node<P> node) {
        return node.getIdentifier();
    }
    protected boolean isALeaf(Node<P> node) {
        return node.isALeafById();
    }
    protected void setLeft(Node<P> node, Node<P> child) {
        node.setLeftById(child);
    }
    protected void setMid(Node<P> node, Node<P> child) {
        node.setMidById(child);
    }
    protected void setRight(Node<P> node, Node<P> child) {
        node.setRightById(child);
    }
    protected void setParent(Node<P> node, Node<P> parent) {
        node.setParentById(parent);
    }
    protected void setKey(Node<P> node, String key) {
        node.setIdentifier(key);
    }
    protected void updateStats(Node<P> node) {
        // No statistics maintenance needed for String tree
    }
}
