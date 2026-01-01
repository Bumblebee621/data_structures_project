public class DVNTreeI<P, Vs extends Comparable<Vs>, Vi extends Comparable<Vi>> extends DVNTree<P, Vs, Vi, Vi> {

    public DVNTreeI(Vi leftSentinelValue, Vi rightSentinelValue) {
        super(leftSentinelValue, rightSentinelValue);
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> createSentinel(Vi value) {
        return new DoubleValueNode<>(null, null, value);
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getLeft(DoubleValueNode<P, Vs, Vi> node) {
        return node.getLefti();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getMid(DoubleValueNode<P, Vs, Vi> node) {
        return node.getMidi();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getRight(DoubleValueNode<P, Vs, Vi> node) {
        return node.getRighti();
    }

    @Override
    protected DoubleValueNode<P, Vs, Vi> getParent(DoubleValueNode<P, Vs, Vi> node) {
        return node.getParenti();
    }

    @Override
    protected Vi getKey(DoubleValueNode<P, Vs, Vi> node) {
        return node.getNum();
    }

    @Override
    protected boolean isALeaf(DoubleValueNode<P, Vs, Vi> node) {
        return node.isALeafi();
    }

    @Override
    protected void setLeft(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setLefti(child);
    }

    @Override
    protected void setMid(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setMidi(child);
    }

    @Override
    protected void setRight(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child) {
        node.setRighti(child);
    }

    @Override
    protected void setParent(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> parent) {
        node.setParenti(parent);
    }

    @Override
    protected void setKey(DoubleValueNode<P, Vs, Vi> node, Vi key) {
        node.setNum(key);
    }
}
