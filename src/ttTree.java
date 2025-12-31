public class ttTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private final T rightSentinalKey;
    private final T leftSentinalKey;
    public ttTree(TreeNode<T> ls, TreeNode<T> ms) {
        this.root = new TreeNode<T>();
        root.setLeft(ls);
        root.setMid(ms);
        ls.setParent(root);
        ms.setParent(root);
        root.setKey(ms.getKey());
        rightSentinalKey = ms.getKey();
        leftSentinalKey = ls.getKey();
    }


    public TreeNode<T> search(TreeNode<T> node, T key) {
        if (node.isALeaf()){
            if (node.getKey().compareTo(key) == 0){
                return node;
            }
            return null;
        }
        if(key.compareTo(node.getLeft().getKey()) <= 0){
            return search(node.getLeft(), key);
        }
        if(key.compareTo(node.getMid().getKey()) <= 0){
            return search(node.getMid(), key);
        }
        return search(node.getRight(), key);
    }

    public TreeNode<T> minimum() {
        TreeNode<T> x = root;
        while (!x.isALeaf()) {
            x = x.getLeft();
        }
        x = x.getParent().getMid();
        if (x.getKey().compareTo(rightSentinalKey) != 0) {
            return x;
        }
        return null;
        //we need to create and throw and expation!!!!!!!!!!!!!!!11
    }
    public TreeNode<T> successor(TreeNode<T> x) {
        TreeNode<T> z = x.getParent();
        TreeNode<T> y;
        while (x.equals(z.getRight()) || (z.getRight() == null && x.equals(z.getMid()))) {
            x = z;
            z = z.getParent();
        }
        if(x.equals(z.getLeft())){
           y = z.getMid();
        } else {
            y = z.getRight();
        }
        while(!y.isALeaf()){
            y = y.getLeft();
        }
        if(y.getKey().compareTo(rightSentinalKey) < 0){
            return y;
        }
        return null;
    }

    public void updateKey(TreeNode<T> x) {
        x.setKey(x.getLeft().getKey());
        if(x.getMid() != null){
            x.setKey(x.getMid().getKey());
        }
        if(x.getRight() != null){
            x.setKey(x.getRight().getKey());
        }
    }
    private void setChildren(TreeNode<T> x, TreeNode<T> l, TreeNode<T> m, TreeNode<T> r) {
        x.setLeft(l);
        x.setMid(m);
        x.setRight(r);
        l.setParent(x);
        if(m != null){
            m.setParent(x);
        }
        if(r != null){
            r.setParent(x);
        }
        updateKey(x);
    }
    public TreeNode<T> insertAndSplit(TreeNode<T> x, TreeNode<T> z) {
       TreeNode<T> l = x.getLeft();
       TreeNode<T> m = x.getMid();
       TreeNode<T> r = x.getRight();
       TreeNode<T> y = new TreeNode<>();
       if(r == null){
           if(z.getKey().compareTo(l.getKey()) < 0){
               setChildren(x, z, l, m);
           } else if(z.getKey().compareTo(m.getKey()) < 0){
               setChildren(x, l, z, m);
           } else {
               setChildren(x, l, m, z);
           }
           return null;
       }
       if(z.getKey().compareTo(l.getKey()) < 0){
           setChildren(x, z, l, null);
           setChildren(y,m,r,null);
       } else if(z.getKey().compareTo(m.getKey()) < 0){
           setChildren(x, l, z, null);
           setChildren(y,m,r,null);
       } else if(z.getKey().compareTo(r.getKey()) < 0){
           setChildren(x,l,m, null);
           setChildren(y,z,r,null);
       } else{
           setChildren(x,l,m,null);
           setChildren(y,r,z,null);
       }
       return y;
    }
    public void insert( TreeNode<T> z) {

    }

}
