public class DVNTreeI<P> extends DVNTree<P, LoadKey> {

    public DVNTreeI(Integer leftSentinelValue, Integer rightSentinelValue) {
        super(new LoadKey(leftSentinelValue, Integer.MIN_VALUE), new LoadKey(rightSentinelValue, Integer.MAX_VALUE));
    }

    protected Node<P> createSentinel(LoadKey value) {
        return new Node<>(null, null, value.getNum(), value.getTimeStamp());
    }
    protected Node<P> getLeft(Node<P> node) {
        return node.getLeftByValue();
    }
    protected Node<P> getMid(Node<P> node) {
        return node.getMidByValue();
    }
    protected Node<P> getRight(Node<P> node) {
        return node.getRightByValue();
    }
    protected Node<P> getParent(Node<P> node) {
        return node.getParentByValue();
    }
    protected LoadKey getKey(Node<P> node) {
        return new LoadKey(node.getValue(), node.getInsertionTime());
    }
    protected boolean isALeaf(Node<P> node) {
        return node.isALeafByValue();
    }
    protected void setLeft(Node<P> node, Node<P> child) {
        node.setLeftByValue(child);
    }
    protected void setMid(Node<P> node, Node<P> child) {
        node.setMidByValue(child);
    }
    protected void setRight(Node<P> node, Node<P> child) {
        node.setRightByValue(child);
    }
    protected void setParent(Node<P> node, Node<P> parent) {
        node.setParentByValue(parent);
    }
    protected void setKey(Node<P> node, LoadKey key) {
        node.setValue(key.getNum());
        node.setInsertionTime(key.getTimeStamp());
    }
    @Override
    protected void updateStats(Node<P> node) {
        if (isALeaf(node)) {
            LoadKey key = getKey(node);

            // Check if this node is one of the sentinels
            // We use 'this.leftSentinelValue' inherited from DVNTree
            boolean isLeftSentinel = key.compareTo(this.leftSentinelValue) == 0;
            boolean isRightSentinel = key.compareTo(this.rightSentinelValue) == 0;

            if (isLeftSentinel || isRightSentinel) {
                // Sentinels contribute NOTHING to the rank or sum
                node.setLeafCount(0);
                node.setSubtreeValueSum(0);
            } else {
                // Real data nodes contribute 1 to rank and their value to sum
                node.setLeafCount(1);
                node.setSubtreeValueSum(node.getValue());
            }
        } else {
            // Internal nodes sum up the stats of their children
            int size = 0;
            long sum = 0;
            Node<P> l = getLeft(node);
            Node<P> m = getMid(node);
            Node<P> r = getRight(node);

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
        Node<P> node = getRoot();
        int rank = 0;
        while (!isALeaf(node)) {
            Node<P> l = getLeft(node);
            Node<P> m = getMid(node);
            Node<P> r = getRight(node);
            
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
        Node<P> node = getRoot();
        long sum = 0;
        while (!isALeaf(node)) {
            Node<P> l = getLeft(node);
            Node<P> m = getMid(node);
            Node<P> r = getRight(node);
            
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