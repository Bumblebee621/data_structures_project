public class DoubleValueNode<P> {
    protected DoubleValueNode<P> leftByValue;
    protected DoubleValueNode<P> midByValue;
    protected DoubleValueNode<P> rightByValue;
    protected DoubleValueNode<P> parentByValue;
    protected DoubleValueNode<P> leftById;
    protected DoubleValueNode<P> midById;
    protected DoubleValueNode<P> rightById;
    protected DoubleValueNode<P> parentById;
    protected P person;
    protected String identifier;
    protected int value;
    protected int insertionTime;
    protected int leafCount;
    protected long subtreeValueSum;

    public DoubleValueNode() {
        this(null, null, 0);
    }

    public DoubleValueNode(P person, String identifier, int value) {
        this(person, identifier, value, 0);
    }

    public DoubleValueNode(P person, String identifier, int value, int insertionTime) {
        this.person = person;
        this.identifier = identifier;
        this.value = value;
        this.insertionTime = insertionTime;
        this.leafCount = 1; // Default to 1 (leaf)
        this.subtreeValueSum = value; // value is int, implicitly long
        this.leftByValue = null;
        this.midByValue = null;
        this.rightByValue = null;
        this.parentByValue = null;
        this.leftById = null;
        this.midById = null;
        this.rightById = null;
        this.parentById = null;
    }

    public boolean isALeafByValue() {
        return leftByValue == null;
    }

    public boolean isALeafById() {
        return leftById == null;
    }

    // Setters
    public void setLeftByValue(DoubleValueNode<P> leftByValue) {
        this.leftByValue = leftByValue;
    }
    public void setMidByValue(DoubleValueNode<P> midByValue) {
        this.midByValue = midByValue;
    }
    public void setRightByValue(DoubleValueNode<P> rightByValue) {
        this.rightByValue = rightByValue;
    }
    public void setParentByValue(DoubleValueNode<P> parentByValue) {
        this.parentByValue = parentByValue;
    }

    public void setLeftById(DoubleValueNode<P> leftById) {
        this.leftById = leftById;
    }
    public void setMidById(DoubleValueNode<P> midById) {
        this.midById = midById;
    }
    public void setRightById(DoubleValueNode<P> rightById) {
        this.rightById = rightById;
    }
    public void setParentById(DoubleValueNode<P> parentById) {
        this.parentById = parentById;
    }

    public void setPerson(P person) {
        this.person = person;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void setInsertionTime(int insertionTime) {
        this.insertionTime = insertionTime;
    }
    public void setLeafCount(int leafCount) {
        this.leafCount = leafCount;
    }
    public void setSubtreeValueSum(long subtreeValueSum) {
        this.subtreeValueSum = subtreeValueSum;
    }

    // Getters
    public DoubleValueNode<P> getLeftByValue() {
        return leftByValue;
    }
    public DoubleValueNode<P> getMidByValue() {
        return midByValue;
    }
    public DoubleValueNode<P> getRightByValue() {
        return rightByValue;
    }
    public DoubleValueNode<P> getParentByValue() {
        return parentByValue;
    }

    public DoubleValueNode<P> getLeftById() {
        return leftById;
    }
    public DoubleValueNode<P> getMidById() {
        return midById;
    }
    public DoubleValueNode<P> getRightById() {
        return rightById;
    }
    public DoubleValueNode<P> getParentById() {
        return parentById;
    }

    public P getPerson() {
        return person;
    }
    public int getValue() {
        return value;
    }
    public String getIdentifier() {
        return identifier;
    }
    public int getInsertionTime() {
        return insertionTime;
    }
    public int getLeafCount() {
        return leafCount;
    }
    public long getSubtreeValueSum() {
        return subtreeValueSum;
    }
}