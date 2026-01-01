public class DVNTreeS<P, Vs extends Comparable<Vs>, Vi extends Comparable<Vi>> extends DVNTree<P, Vs, Vi, Vs> {

    public DVNTreeS(Vs leftSentinelValue, Vs rightSentinelValue) {
        super(leftSentinelValue, rightSentinelValue);
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> createSentinel(Vs value) {
        return new DoubleValueNode<>(null, value, null);
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getLeft(DoubleValueNode<P, Vs, Vi> node) {
        return node.getLefts();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getMid(DoubleValueNode<P, Vs, Vi> node) {
        return node.getMids();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getRight(DoubleValueNode<P, Vs, Vi> node) {
        return node.getRights();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getParent(DoubleValueNode<P, Vs, Vi> node) {
        return node.getParents();
    }

    @Override
    protected Vs getKey(DoubleValueNode<P, Vs, Vi> node) {
        return node.getId();
    }

    @Override
    protected boolean isALeaf(DoubleValueNode<P, Vs, Vi> node) {
        return node.isALeafs();
    }

    @Override
    protected void setLeft(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setLefts(child);
    }

    @Override
    protected void setMid(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setMids(child);
    }

    @Override
    protected void setRight(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setRights(child);
    }

    @Override
    protected void setParent(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> parent) {
        node.setParents(parent);
    }

    @Override
    protected void setKey(DoubleValueNode<P, Vs, Vi> node, Vs key) {
        node.setId(key);
    }
}
