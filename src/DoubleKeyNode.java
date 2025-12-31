public class DoubleKeyNode<P,V extends Comparable<V>> {
    protected DoubleKeyNode<P,V> left;
    protected DoubleKeyNode<P,V> mid;
    protected DoubleKeyNode<P,V> right;
    protected DoubleKeyNode<P,V> parent;
    protected P person;


    protected V value;

    public DoubleKeyNode() {
        this(null, null, null, null, null, null);
    }
    public DoubleKeyNode(DoubleKeyNode<P,V> parent, P person, V value) {
        this(null, null,null, parent, person, value);
    }
    public DoubleKeyNode(P person, V value) {
        this(null, null,null, null, person, value);
    }
    public DoubleKeyNode(DoubleKeyNode<P,V> left, DoubleKeyNode<P,V> mid,
                         DoubleKeyNode<P,V> right, DoubleKeyNode<P,V> parent, P person, V value) {
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.parent = parent;
        this.person = person;
        this.value = value;
    }
    public boolean isALeaf() {
        return left == null;
    }
    public void setLeft(DoubleKeyNode<P,V> left) {
        this.left = left;
    }
    public void setMid(DoubleKeyNode<P,V> mid) {
        this.mid = mid;
    }
    public void setRight(DoubleKeyNode<P,V> right) {
        this.right = right;
    }
    public void setParent(DoubleKeyNode<P,V> parent) {
        this.parent = parent;
    }
    public void setPerson(P person) {
        this.person = person;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public DoubleKeyNode<P,V> getLeft() {
        return left;
    }
    public DoubleKeyNode<P,V> getMid() {
        return mid;
    }
    public DoubleKeyNode<P,V> getRight() {
        return right;
    }
    public DoubleKeyNode<P,V> getParent() {
        return parent;
    }
    public P getPerson() {
        return person;
    }
    public V getValue() {
        return value;
    }
}
