public class DKNTree<P, V extends Comparable<V>> {
    private DoubleKeyNode<P,V> root;
    private V leftSentinelValue;
    private V rightSentinelValue;
    
    public DKNTree(V leftSentinelValue, V rightSentinelValue) {
        this.root = new DoubleKeyNode<P,V>();
        DoubleKeyNode<P,V> leftSentinel = new DoubleKeyNode<>(null, leftSentinelValue);
        DoubleKeyNode<P,V> rightSentinel = new DoubleKeyNode<>(null, rightSentinelValue);
        this.setChildren(root, leftSentinel, rightSentinel, null);
        leftSentinel.setParent(root);
        rightSentinel.setParent(root);
        this.updateKey(root);
        this.leftSentinelValue = leftSentinelValue;
        this.rightSentinelValue = rightSentinelValue;
    }


    public DoubleKeyNode<P,V> search(DoubleKeyNode<P,V> node, V value) {
        if (node.isALeaf()){
            if (node.getValue().compareTo(value) == 0){
                return node;
            }
            return null;
        }
        if(value.compareTo(node.getLeft().getValue()) <= 0){
            return search(node.getLeft(), value);
        }
        if(value.compareTo(node.getMid().getValue()) <= 0){
            return search(node.getMid(), value);
        }
        return search(node.getRight(), value);
    }
    public DoubleKeyNode<P,V> minimum() {
        DoubleKeyNode<P,V> x = root;
        while (!x.isALeaf()) {
            x = x.getLeft();
        }
        x = x.getParent().getMid();
        if (x.getValue().compareTo(rightSentinelValue) != 0) {
            return x;
        }
        return null;
        //we need to create and throw and expation!!!!!!!!!!!!!!!11
    }
    public DoubleKeyNode<P,V> successor(DoubleKeyNode<P,V> x) {
        DoubleKeyNode<P,V> z = x.getParent();
        DoubleKeyNode<P,V> y;
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
        if(y.getValue().compareTo(rightSentinelValue) < 0){
            return y;
        }
        return null;
    }
    public void updateKey(DoubleKeyNode<P,V> x) {
        x.setValue(x.getLeft().getValue());
        if(x.getMid() != null){
            x.setValue(x.getMid().getValue());
        }
        if(x.getRight() != null){
            x.setValue(x.getRight().getValue());
        }
    }
    private void setChildren(DoubleKeyNode<P,V> x, DoubleKeyNode<P,V> l, DoubleKeyNode<P,V> m, DoubleKeyNode<P,V> r) {
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
    public DoubleKeyNode<P,V> insertAndSplit(DoubleKeyNode<P,V> x, DoubleKeyNode<P,V> z) {
        DoubleKeyNode<P,V> l = x.getLeft();
        DoubleKeyNode<P,V> m = x.getMid();
        DoubleKeyNode<P,V> r = x.getRight();
        DoubleKeyNode<P,V> y = new DoubleKeyNode<>();
        if(r == null){
            if(z.getValue().compareTo(l.getValue()) < 0){
                setChildren(x, z, l, m);
            } else if(z.getValue().compareTo(m.getValue()) < 0){
                setChildren(x, l, z, m);
            } else {
                setChildren(x, l, m, z);
            }
            return null;
        }
        if(z.getValue().compareTo(l.getValue()) < 0){
            setChildren(x, z, l, null);
            setChildren(y,m,r,null);
        } else if(z.getValue().compareTo(m.getValue()) < 0){
            setChildren(x, l, z, null);
            setChildren(y,m,r,null);
        } else if(z.getValue().compareTo(r.getValue()) < 0){
            setChildren(x,l,m, null);
            setChildren(y,z,r,null);
        } else{
            setChildren(x,l,m,null);
            setChildren(y,r,z,null);
        }
        return y;
    }
    public void insert( DoubleKeyNode<P,V> z) {
        DoubleKeyNode<P,V> y = root;
        while (!y.isALeaf()){
            if(z.getValue().compareTo(y.getLeft().getValue()) < 0){
                y = y.getLeft();
            } else if(z.getValue().compareTo(y.getMid().getValue()) < 0){
                y = y.getMid();
            } else {
                y = y.getRight();
            }
        }
        DoubleKeyNode<P,V> x = y.getParent();
        z = insertAndSplit(x,z);
        while(x != root){
            x = x.getParent();
            if (z != null){
                z = insertAndSplit(x,z);
            } else{
                updateKey(x);
            }
        }
        if (z != null){
            DoubleKeyNode<P,V> w = new DoubleKeyNode<>();
            setChildren(w, x, z, null);
            root = w;
        }
    }
    public DoubleKeyNode<P,V> borrowOrMerge(DoubleKeyNode<P,V> y){
        DoubleKeyNode<P,V> z = y.getParent();
        DoubleKeyNode<P,V> x;
        if (y.equals(z.getLeft())){
            x = z.getMid();
            //borrowing sequence
            if(x.getRight() != null){
                setChildren(y, y.getLeft(), x.getLeft(), null);
                setChildren(x, x.getMid(), x.getRight(), null);
            }
            //merging sequence
            else {
                setChildren(x, y.getLeft(), x.getLeft(), x.getMid());
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        } if (y.equals(z.getMid())) {
            x = z.getLeft();
            if (x.getRight() != null){
                setChildren(y, x.getRight(), y.getLeft(), null);
                setChildren(x, x.getLeft(), x.getMid(), null);
            } else {
                setChildren(x, x.getLeft(), x.getMid(), y.getLeft());
                setChildren(z, x, z.getRight(), null);
            }
            return z;
        }
        x = z.getMid();
        if (x.getRight() != null){
            setChildren(y, x.getRight(), y.getLeft(), null);
            setChildren(x, x.getLeft(), x.getMid(), null);
        } else {
            setChildren(x, x.getLeft(), x.getMid(), y.getLeft());
            setChildren(z, z.getLeft(), x, null);
        }
        return z;
    }
    public void delete(DoubleKeyNode<P,V> x) {
        DoubleKeyNode<P,V> y = x.getParent();
        if (x.equals(y.getLeft())){
            setChildren(y, y.getMid(), y.getRight(), null);
        } else if (x.equals(y.getMid())){
            setChildren(y, y.getLeft(), y.getRight(), null);
        }else {
            setChildren(y, y.getLeft(), y.getMid(), null);
        }
        while (y != null){
            if (y.getMid()!=null){
                updateKey(y);
                y = y.getParent();
            } else {
                if (y != root){
                    y = borrowOrMerge(y);
                } else {
                    root = y.getLeft();
                    y.getLeft().setParent(null);
                    return;
                }
            }
        }
    }
    public DoubleKeyNode<P,V> getRoot() {
        return root;
    }
}
