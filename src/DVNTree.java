public abstract class DVNTree<P, K extends Comparable<K>> {
    protected DoubleValueNode<P> root;
    protected K leftSentinelValue;
    protected K rightSentinelValue;

    public DVNTree(K leftSentinelValue, K rightSentinelValue) {
        this.root = new DoubleValueNode<>();
        DoubleValueNode<P> leftSentinel = createSentinel(leftSentinelValue);
        DoubleValueNode<P> rightSentinel = createSentinel(rightSentinelValue);
        this.setChildren(root, leftSentinel, rightSentinel, null);
        setParent(leftSentinel, root);
        setParent(rightSentinel, root);
        this.updateKey(root);
        this.leftSentinelValue = leftSentinelValue;
        this.rightSentinelValue = rightSentinelValue;
    }

    protected abstract DoubleValueNode<P> createSentinel(K value);
    
    // Abstract Accessors
    protected abstract DoubleValueNode<P> getLeft(DoubleValueNode<P> node);
    protected abstract DoubleValueNode<P> getMid(DoubleValueNode<P> node);
    protected abstract DoubleValueNode<P> getRight(DoubleValueNode<P> node);
    protected abstract DoubleValueNode<P> getParent(DoubleValueNode<P> node);
    protected abstract K getKey(DoubleValueNode<P> node);
    protected abstract boolean isALeaf(DoubleValueNode<P> node);

    // Abstract Mutators
    protected abstract void setLeft(DoubleValueNode<P> node, DoubleValueNode<P> child);
    protected abstract void setMid(DoubleValueNode<P> node, DoubleValueNode<P> child);
    protected abstract void setRight(DoubleValueNode<P> node, DoubleValueNode<P> child);
    protected abstract void setParent(DoubleValueNode<P> node, DoubleValueNode<P> parent);
    protected abstract void setKey(DoubleValueNode<P> node, K key);
    
    // Abstract Stats Update
    protected abstract void updateStats(DoubleValueNode<P> node);


    public DoubleValueNode<P> search(DoubleValueNode<P> node, K value) {
        if (isALeaf(node)){
            if (getKey(node).compareTo(value) == 0){
                return node;
            }
            return null;
        }
        if(value.compareTo(getKey(getLeft(node))) <= 0){
            return search(getLeft(node), value);
        }
        if(value.compareTo(getKey(getMid(node))) <= 0){
            return search(getMid(node), value);
        }
        return search(getRight(node), value);
    }

    public DoubleValueNode<P> minimum() {
        DoubleValueNode<P> x = root;
        while (!isALeaf(x)) {
            x = getLeft(x);
        }
        x = getMid(getParent(x));
        if (getKey(x).compareTo(rightSentinelValue) != 0) {
            return x;
        }
        return null;
    }

    public DoubleValueNode<P> successor(DoubleValueNode<P> x) {
        DoubleValueNode<P> z = getParent(x);
        DoubleValueNode<P> y;
        while (x.equals(getRight(z)) || (getRight(z) == null && x.equals(getMid(z)))) {
            x = z;
            z = getParent(z);
        }
        if(x.equals(getLeft(z))){
            y = getMid(z);
        } else {
            y = getRight(z);
        }
        while(!isALeaf(y)){
            y = getLeft(y);
        }
        if(getKey(y).compareTo(rightSentinelValue) < 0){
            return y;
        }
        return null;
    }

    public void updateKey(DoubleValueNode<P> x) {
        setKey(x, getKey(getLeft(x)));
        if(getMid(x) != null){
            setKey(x, getKey(getMid(x)));
        }
        if(getRight(x) != null){
            setKey(x, getKey(getRight(x)));
        }
    }

    protected void setChildren(DoubleValueNode<P> x, DoubleValueNode<P> l, DoubleValueNode<P> m, DoubleValueNode<P> r) {
        setLeft(x, l);
        setMid(x, m);
        setRight(x, r);
        setParent(l, x);
        if(m != null){
            setParent(m, x);
        }
        if(r != null){
            setParent(r, x);
        }
        updateKey(x);
        updateStats(x);
    }

    public DoubleValueNode<P> insertAndSplit(DoubleValueNode<P> x, DoubleValueNode<P> z) {
        DoubleValueNode<P> l = getLeft(x);
        DoubleValueNode<P> m = getMid(x);
        DoubleValueNode<P> r = getRight(x);
        DoubleValueNode<P> y = new DoubleValueNode<>();
        
        K zKey = getKey(z);
        
        if(r == null){
            if(zKey.compareTo(getKey(l)) < 0){
                setChildren(x, z, l, m);
            } else if(zKey.compareTo(getKey(m)) < 0){
                setChildren(x, l, z, m);
            } else {
                setChildren(x, l, m, z);
            }
            return null;
        }
        if(zKey.compareTo(getKey(l)) < 0){
            setChildren(x, z, l, null);
            setChildren(y,m,r,null);
        } else if(zKey.compareTo(getKey(m)) < 0){
            setChildren(x, l, z, null);
            setChildren(y,m,r,null);
        } else if(zKey.compareTo(getKey(r)) < 0){
            setChildren(x,l,m, null);
            setChildren(y,z,r,null);
        } else{
            setChildren(x,l,m,null);
            setChildren(y,r,z,null);
        }
        return y;
    }

    public void insert(DoubleValueNode<P> z) {
        DoubleValueNode<P> y = root;
        K zKey = getKey(z);
        while (!isALeaf(y)){
            if(zKey.compareTo(getKey(getLeft(y))) < 0){
                y = getLeft(y);
            } else if(zKey.compareTo(getKey(getMid(y))) < 0){
                y = getMid(y);
            } else {
                y = getRight(y);
            }
        }
        DoubleValueNode<P> x = getParent(y);
        z = insertAndSplit(x,z);
        while(x != root){
            x = getParent(x);
            if (z != null){
                z = insertAndSplit(x,z);
            } else{
                updateKey(x);
                updateStats(x);
            }
        }
        if (z != null){
            DoubleValueNode<P> w = new DoubleValueNode<>();
            setChildren(w, x, z, null);
            root = w;
        }
    }

    public DoubleValueNode<P> borrowOrMerge(DoubleValueNode<P> y){
        DoubleValueNode<P> z = getParent(y);
        DoubleValueNode<P> x;
        if (y.equals(getLeft(z))){
            x = getMid(z);
            //borrowing sequence
            if(getRight(x) != null){
                setChildren(y, getLeft(y), getLeft(x), null);
                setChildren(x, getMid(x), getRight(x), null);
            }
            //merging sequence
            else {
                setChildren(x, getLeft(y), getLeft(x), getMid(x));
                setChildren(z, x, getRight(z), null);
            }
            return z;
        } if (y.equals(getMid(z))) {
            x = getLeft(z);
            if (getRight(x) != null){
                setChildren(y, getRight(x), getLeft(y), null);
                setChildren(x, getLeft(x), getMid(x), null);
            } else {
                setChildren(x, getLeft(x), getMid(x), getLeft(y));
                setChildren(z, x, getRight(z), null);
            }
            return z;
        }
        x = getMid(z);
        if (getRight(x) != null){
            setChildren(y, getRight(x), getLeft(y), null);
            setChildren(x, getLeft(x), getMid(x), null);
        } else {
            setChildren(x, getLeft(x), getMid(x), getLeft(y));
            setChildren(z, getLeft(z), x, null);
        }
        return z;
    }

    public void delete(DoubleValueNode<P> x) {
        DoubleValueNode<P> y = getParent(x);
        if (x.equals(getLeft(y))){
            setChildren(y, getMid(y), getRight(y), null);
        } else if (x.equals(getMid(y))){
            setChildren(y, getLeft(y), getRight(y), null);
        }else {
            setChildren(y, getLeft(y), getMid(y), null);
        }
        while (y != null){
            if (getMid(y)!=null){
                updateKey(y);
                updateStats(y);
                y = getParent(y);
            } else {
                if (y != root){
                    y = borrowOrMerge(y);
                } else {
                    root = getLeft(y);
                    setParent(getLeft(y), null);
                    return;
                }
            }
        }
    }

    public DoubleValueNode<P> getRoot() {
        return root;
    }
}
