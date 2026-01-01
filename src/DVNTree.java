public abstract class DVNTree<P, Vs extends Comparable<Vs>, Vi extends Comparable<Vi>, K extends Comparable<K>> {
    protected DoubleValueNode<P, Vs, Vi> root;
    protected K leftSentinelValue;
    protected K rightSentinelValue;

    public DVNTree(K leftSentinelValue, K rightSentinelValue) {
        this.root = new DoubleValueNode<>();
        DoubleValueNode<P, Vs, Vi> leftSentinel = createSentinel(leftSentinelValue);
        DoubleValueNode<P, Vs, Vi> rightSentinel = createSentinel(rightSentinelValue);
        this.setChildren(root, leftSentinel, rightSentinel, null);
        setParent(leftSentinel, root);
        setParent(rightSentinel, root);
        this.updateKey(root);
        this.leftSentinelValue = leftSentinelValue;
        this.rightSentinelValue = rightSentinelValue;
    }

    protected abstract DoubleValueNode<P, Vs, Vi> createSentinel(K value);
    
    // Abstract Accessors
    protected abstract DoubleValueNode<P, Vs, Vi> getLeft(DoubleValueNode<P, Vs, Vi> node);
    protected abstract DoubleValueNode<P, Vs, Vi> getMid(DoubleValueNode<P, Vs, Vi> node);
    protected abstract DoubleValueNode<P, Vs, Vi> getRight(DoubleValueNode<P, Vs, Vi> node);
    protected abstract DoubleValueNode<P, Vs, Vi> getParent(DoubleValueNode<P, Vs, Vi> node);
    protected abstract K getKey(DoubleValueNode<P, Vs, Vi> node);
    protected abstract boolean isALeaf(DoubleValueNode<P, Vs, Vi> node);

    // Abstract Mutators
    protected abstract void setLeft(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child);
    protected abstract void setMid(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child);
    protected abstract void setRight(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> child);
    protected abstract void setParent(DoubleValueNode<P, Vs, Vi> node, DoubleValueNode<P, Vs, Vi> parent);
    protected abstract void setKey(DoubleValueNode<P, Vs, Vi> node, K key);


    public DoubleValueNode<P, Vs, Vi> search(DoubleValueNode<P, Vs, Vi> node, K value) {
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

    public DoubleValueNode<P, Vs, Vi> minimum() {
        DoubleValueNode<P, Vs, Vi> x = root;
        while (!isALeaf(x)) {
            x = getLeft(x);
        }
        x = getMid(getParent(x));
        if (getKey(x).compareTo(rightSentinelValue) != 0) {
            return x;
        }
        return null;
    }

    public DoubleValueNode<P, Vs, Vi> successor(DoubleValueNode<P, Vs, Vi> x) {
        DoubleValueNode<P, Vs, Vi> z = getParent(x);
        DoubleValueNode<P, Vs, Vi> y;
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

    public void updateKey(DoubleValueNode<P, Vs, Vi> x) {
        setKey(x, getKey(getLeft(x)));
        if(getMid(x) != null){
            setKey(x, getKey(getMid(x)));
        }
        if(getRight(x) != null){
            setKey(x, getKey(getRight(x)));
        }
    }

    protected void setChildren(DoubleValueNode<P, Vs, Vi> x, DoubleValueNode<P, Vs, Vi> l, DoubleValueNode<P, Vs, Vi> m, DoubleValueNode<P, Vs, Vi> r) {
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
    }

    public DoubleValueNode<P, Vs, Vi> insertAndSplit(DoubleValueNode<P, Vs, Vi> x, DoubleValueNode<P, Vs, Vi> z) {
        DoubleValueNode<P, Vs, Vi> l = getLeft(x);
        DoubleValueNode<P, Vs, Vi> m = getMid(x);
        DoubleValueNode<P, Vs, Vi> r = getRight(x);
        DoubleValueNode<P, Vs, Vi> y = new DoubleValueNode<>();
        
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

    public void insert(DoubleValueNode<P, Vs, Vi> z) {
        DoubleValueNode<P, Vs, Vi> y = root;
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
        DoubleValueNode<P, Vs, Vi> x = getParent(y);
        z = insertAndSplit(x,z);
        while(x != root){
            x = getParent(x);
            if (z != null){
                z = insertAndSplit(x,z);
            } else{
                updateKey(x);
            }
        }
        if (z != null){
            DoubleValueNode<P, Vs, Vi> w = new DoubleValueNode<>();
            setChildren(w, x, z, null);
            root = w;
        }
    }

    public DoubleValueNode<P, Vs, Vi> borrowOrMerge(DoubleValueNode<P, Vs, Vi> y){
        DoubleValueNode<P, Vs, Vi> z = getParent(y);
        DoubleValueNode<P, Vs, Vi> x;
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

    public void delete(DoubleValueNode<P, Vs, Vi> x) {
        DoubleValueNode<P, Vs, Vi> y = getParent(x);
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

    public DoubleValueNode<P, Vs, Vi> getRoot() {
        return root;
    }
}
