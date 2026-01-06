public class DVNTreeS<P> extends DVNTree<P, String> {

    public DVNTreeS(String leftSentinelValue, String rightSentinelValue) {
        super(leftSentinelValue, rightSentinelValue);
    }

    @Override
    protected DoubleValueNode<P> createSentinel(String value) {
        return new DoubleValueNode<>(null, value, 0); // value is String id, num is 0
    }

    @Override
    protected DoubleValueNode<P> getLeft(DoubleValueNode<P> node) {
        return node.getLeftById();
    }

    @Override
    protected DoubleValueNode<P> getMid(DoubleValueNode<P> node) {
        return node.getMidById();
    }

    @Override
    protected DoubleValueNode<P> getRight(DoubleValueNode<P> node) {
        return node.getRightById();
    }

    @Override
    protected DoubleValueNode<P> getParent(DoubleValueNode<P> node) {
        return node.getParentById();
    }

    @Override
    protected String getKey(DoubleValueNode<P> node) {
        return node.getIdentifier();
    }

    @Override
    protected boolean isALeaf(DoubleValueNode<P> node) {
        return node.isALeafById();
    }

    @Override
    protected void setLeft(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setLeftById(child);
    }

    @Override
    protected void setMid(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setMidById(child);
    }

    @Override
    protected void setRight(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setRightById(child);
    }

    @Override
    protected void setParent(DoubleValueNode<P> node, DoubleValueNode<P> parent) {
        node.setParentById(parent);
    }

    @Override
    protected void setKey(DoubleValueNode<P> node, String key) {
        node.setIdentifier(key);
    }

    @Override
    protected void updateStats(DoubleValueNode<P> node) {
        // No statistics maintenance needed for String tree
    }
}
