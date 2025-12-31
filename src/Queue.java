public class Queue {
    private TreeNode head;
    private TreeNode tail;
    private int size;

    public Queue(TreeNode head) {
        this.head = head;
        this.tail = head;
        this.size = 1;
    }
    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public TreeNode show(){
        return head;
    }
    public void insert(TreeNode p){
        TreeNode temp = tail;
        temp.setPrev(p);
        p.setNext(temp);
        p.setPrev(null);
        tail = p;
        size++;
    }
    public TreeNode remove(){
        TreeNode temp = head;
        head = head.getPrev();
        head.setNext(null);
        size--;
        return temp;
    }
    public int getSize() {
        return size;
    }
}
