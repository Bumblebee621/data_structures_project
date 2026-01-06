public class LoadKey implements Comparable<LoadKey> {
    private final Integer num;
    private final Integer timeStamp;

    public LoadKey(Integer num, Integer timeStamp) {
        this.num = num;
        this.timeStamp = timeStamp;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(LoadKey other) {
        int res = this.num.compareTo(other.num);
        if (res != 0) {
            return res;
        }
        return this.timeStamp.compareTo(other.timeStamp);
    }
}
