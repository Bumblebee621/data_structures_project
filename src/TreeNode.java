public class TreeNode<T extends Comparable<T>> {
    protected TreeNode<T> left;
    protected TreeNode<T> mid;
    protected TreeNode<T> right;
    protected TreeNode<T> parent;
    protected T key;

    public TreeNode() {
        this(null, null, null, null, null);
    }
    public TreeNode(TreeNode<T> parent, T key) {
        this(null, null,null, parent, key);
    }
    public TreeNode(T key) {
        this(null, null,null, null, key);
    }
    public TreeNode(TreeNode<T> left, TreeNode<T> mid, TreeNode<T> right, TreeNode<T> parent, T key) {
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.parent = parent;
        this.key = key;
    }
    public boolean isALeaf() {
        return left == null;
    }
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }
    public void setMid(TreeNode<T> mid) {
        this.mid = mid;
    }
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }
    public void setKey(T key) {
        this.key = key;
    }
    public TreeNode<T> getLeft() {
        return left;
    }
    public TreeNode<T> getMid() {
        return mid;
    }
    public TreeNode<T> getRight() {
        return right;
    }
    public TreeNode<T> getParent() {
        return parent;
    }
    public T getKey() {
        return key;
    }
}
