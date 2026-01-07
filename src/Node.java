public class Node<P> {
    protected Node<P> leftByValue;
    protected Node<P> midByValue;
    protected Node<P> rightByValue;
    protected Node<P> parentByValue;
    protected Node<P> leftById;
    protected Node<P> midById;
    protected Node<P> rightById;
    protected Node<P> parentById;
    protected P person;
    protected String identifier;
    protected int value;
    protected int insertionTime;
    protected int leafCount;
    protected long subtreeValueSum;

    public Node() {
        this(null, null, 0);
    }

    private Node(P person, String identifier, int value) {
        this(person, identifier, value, 0);
    }

    public Node(P person, String identifier, int value, int insertionTime) {
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
    public void setLeftByValue(Node<P> leftByValue) {
        this.leftByValue = leftByValue;
    }
    public void setMidByValue(Node<P> midByValue) {
        this.midByValue = midByValue;
    }
    public void setRightByValue(Node<P> rightByValue) {
        this.rightByValue = rightByValue;
    }
    public void setParentByValue(Node<P> parentByValue) {
        this.parentByValue = parentByValue;
    }

    public void setLeftById(Node<P> leftById) {
        this.leftById = leftById;
    }
    public void setMidById(Node<P> midById) {
        this.midById = midById;
    }
    public void setRightById(Node<P> rightById) {
        this.rightById = rightById;
    }
    public void setParentById(Node<P> parentById) {
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
    public Node<P> getLeftByValue() {
        return leftByValue;
    }
    public Node<P> getMidByValue() {
        return midByValue;
    }
    public Node<P> getRightByValue() {
        return rightByValue;
    }
    public Node<P> getParentByValue() {
        return parentByValue;
    }

    public Node<P> getLeftById() {
        return leftById;
    }
    public Node<P> getMidById() {
        return midById;
    }
    public Node<P> getRightById() {
        return rightById;
    }
    public Node<P> getParentById() {
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