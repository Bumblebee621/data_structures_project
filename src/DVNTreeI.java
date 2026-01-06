public class DVNTreeI<P> extends DVNTree<P, LoadKey> {

    public DVNTreeI(Integer leftSentinelValue, Integer rightSentinelValue) {
        super(new LoadKey(leftSentinelValue, Integer.MIN_VALUE), new LoadKey(rightSentinelValue, Integer.MAX_VALUE));
    }

    @Override
    protected DoubleValueNode<P> createSentinel(LoadKey value) {
        return new DoubleValueNode<>(null, null, value.getNum(), value.getTimeStamp());
    }

    @Override
    protected DoubleValueNode<P> getLeft(DoubleValueNode<P> node) {
        return node.getLeftByValue();
    }

    @Override
    protected DoubleValueNode<P> getMid(DoubleValueNode<P> node) {
        return node.getMidByValue();
    }

    @Override
    protected DoubleValueNode<P> getRight(DoubleValueNode<P> node) {
        return node.getRightByValue();
    }

    @Override
    protected DoubleValueNode<P> getParent(DoubleValueNode<P> node) {
        return node.getParentByValue();
    }

    @Override
    protected LoadKey getKey(DoubleValueNode<P> node) {
        return new LoadKey(node.getValue(), node.getInsertionTime());
    }

    @Override
    protected boolean isALeaf(DoubleValueNode<P> node) {
        return node.isALeafByValue();
    }

    @Override
    protected void setLeft(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setLeftByValue(child);
    }

    @Override
    protected void setMid(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setMidByValue(child);
    }

    @Override
    protected void setRight(DoubleValueNode<P> node, DoubleValueNode<P> child) {
        node.setRightByValue(child);
    }

    @Override
    protected void setParent(DoubleValueNode<P> node, DoubleValueNode<P> parent) {
        node.setParentByValue(parent);
    }

    @Override
    protected void setKey(DoubleValueNode<P> node, LoadKey key) {
        node.setValue(key.getNum());
        node.setInsertionTime(key.getTimeStamp());
    }

    @Override
    protected void updateStats(DoubleValueNode<P> node) {
        if (isALeaf(node)) {
            node.setLeafCount(1);
            node.setSubtreeValueSum(node.getValue());
        } else {
            int size = 0;
            long sum = 0;
            DoubleValueNode<P> l = getLeft(node);
            DoubleValueNode<P> m = getMid(node);
            DoubleValueNode<P> r = getRight(node);

            if (l != null) {
                size += l.getLeafCount();
                sum += l.getSubtreeValueSum();
            }
            if (m != null) {
                size += m.getLeafCount();
                sum += m.getSubtreeValueSum();
            }
            if (r != null) {
                size += r.getLeafCount();
                sum += r.getSubtreeValueSum();
            }
            node.setLeafCount(size);
            node.setSubtreeValueSum(sum);
        }
    }
    
    // Requested functions
    
    public DoubleValueNode<P> findMinTimeStamp(int num) {
        return findNodeWithNum(num, true);
    }

    public DoubleValueNode<P> findMaxTimeStamp(int num) {
        return findNodeWithNum(num, false);
    }
    
    private DoubleValueNode<P> findNodeWithNum(int num, boolean findMin) {
        DoubleValueNode<P> node = getRoot();
        LoadKey target = new LoadKey(num, findMin ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        
        while (!isALeaf(node)) {
            LoadKey lKey = getKey(getLeft(node));
            LoadKey mKey = getKey(getMid(node));
            
            if (target.compareTo(lKey) <= 0) {
                node = getLeft(node);
            } else if (target.compareTo(mKey) <= 0) {
                node = getMid(node);
            } else {
                node = getRight(node);
            }
        }
        
        // At leaf, check if num matches
        if (node.getValue() == num) {
            return node;
        }
        return null;
    }

    public int numDoctorsWithLoadInRange(int a, int b) {
        if (a > b) return 0;
        int rankA = getRank(new LoadKey(a, Integer.MIN_VALUE));
        int rankB = getRank(new LoadKey(b, Integer.MAX_VALUE));
        return rankB - rankA;
    }

    public int averageLoadWithinRange(int a, int b) {
        if (a > b) return 0;
        int count = numDoctorsWithLoadInRange(a, b);
        if (count == 0) return 0;
        
        long sumA = getPrefixSum(new LoadKey(a, Integer.MIN_VALUE));
        long sumB = getPrefixSum(new LoadKey(b, Integer.MAX_VALUE));
        long totalSum = sumB - sumA;
        
        return (int) (totalSum / count);
    }

    private int getRank(LoadKey key) {
        DoubleValueNode<P> node = getRoot();
        int rank = 0;
        while (!isALeaf(node)) {
            DoubleValueNode<P> l = getLeft(node);
            DoubleValueNode<P> m = getMid(node);
            DoubleValueNode<P> r = getRight(node);
            
            LoadKey lKey = getKey(l);
            LoadKey mKey = getKey(m);

            if (key.compareTo(lKey) <= 0) {
                node = l;
            } else if (key.compareTo(mKey) <= 0) {
                rank += l.getLeafCount();
                node = m;
            } else {
                rank += l.getLeafCount() + m.getLeafCount();
                node = r;
            }
        }
        
        // At leaf
        if (getKey(node).compareTo(key) < 0) {
            rank += node.getLeafCount(); // Should be 1
        }
        return rank;
    }

    private long getPrefixSum(LoadKey key) {
        DoubleValueNode<P> node = getRoot();
        long sum = 0;
        while (!isALeaf(node)) {
            DoubleValueNode<P> l = getLeft(node);
            DoubleValueNode<P> m = getMid(node);
            DoubleValueNode<P> r = getRight(node);
            
            LoadKey lKey = getKey(l);
            LoadKey mKey = getKey(m);

            if (key.compareTo(lKey) <= 0) {
                node = l;
            } else if (key.compareTo(mKey) <= 0) {
                sum += l.getSubtreeValueSum();
                node = m;
            } else {
                sum += l.getSubtreeValueSum() + m.getSubtreeValueSum();
                node = r;
            }
        }
        
        // At leaf
        if (getKey(node).compareTo(key) < 0) {
            sum += node.getSubtreeValueSum();
        }
        return sum;
    }
}