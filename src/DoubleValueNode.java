public class DoubleValueNode<P,Vs extends Comparable<Vs>, Vi extends Comparable<Vi>> {
    protected DoubleValueNode<P,Vs, Vi> lefti;
    protected DoubleValueNode<P,Vs, Vi> midi;
    protected DoubleValueNode<P,Vs, Vi> righti;
    protected DoubleValueNode<P,Vs, Vi> parenti;
    protected DoubleValueNode<P,Vs, Vi> lefts;
    protected DoubleValueNode<P,Vs, Vi> mids;
    protected DoubleValueNode<P,Vs, Vi> rights;
    protected DoubleValueNode<P,Vs, Vi> parents;
    protected P person;
    protected Vs id;
    protected Vi num;

    public DoubleValueNode() {
        this(null, null, null);
    }

    public DoubleValueNode(P person, Vs id, Vi num) {
        this.person = person;
        this.id = id;
        this.num = num;
        this.lefti = null;
        this.midi = null;
        this.righti = null;
        this.parenti = null;
        this.lefts = null;
        this.mids = null;
        this.rights = null;
        this.parents = null;
    }

    public boolean isALeafi() {
        return lefti == null;
    }

    public boolean isALeafs() {
        return lefts == null;
    }

    public void setLefti(DoubleValueNode<P,Vs, Vi> lefti) {
        this.lefti = lefti;
    }
    public void setMidi(DoubleValueNode<P,Vs, Vi> midi) {
        this.midi = midi;
    }
    public void setRighti(DoubleValueNode<P,Vs, Vi> righti) {
        this.righti = righti;
    }
    public void setParenti(DoubleValueNode<P,Vs, Vi> parenti) {
        this.parenti = parenti;
    }

    public void setLefts(DoubleValueNode<P,Vs, Vi> lefts) {
        this.lefts = lefts;
    }
    public void setMids(DoubleValueNode<P,Vs, Vi> mids) {
        this.mids = mids;
    }
    public void setRights(DoubleValueNode<P,Vs, Vi> rights) {
        this.rights = rights;
    }
    public void setParents(DoubleValueNode<P,Vs, Vi> parents) {
        this.parents = parents;
    }

    public void setPerson(P person) {
        this.person = person;
    }
    public void setNum(Vi num) {
        this.num = num;
    }
    public void setId(Vs id) {
        this.id = id;
    }

    public DoubleValueNode<P,Vs, Vi> getLefti() {
        return lefti;
    }
    public DoubleValueNode<P,Vs, Vi> getMidi() {
        return midi;
    }
    public DoubleValueNode<P,Vs, Vi> getRighti() {
        return righti;
    }
    public DoubleValueNode<P,Vs, Vi> getParenti() {
        return parenti;
    }

    public DoubleValueNode<P,Vs, Vi> getLefts() {
        return lefts;
    }
    public DoubleValueNode<P,Vs, Vi> getMids() {
        return mids;
    }
    public DoubleValueNode<P,Vs, Vi> getRights() {
        return rights;
    }
    public DoubleValueNode<P,Vs, Vi> getParents() {
        return parents;
    }

    public P getPerson() {
        return person;
    }
    public Vi getNum() {
        return num;
    }
    public Vs getId() {
        return id;
    }
}
