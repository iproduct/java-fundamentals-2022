package course.java.util;

import java.util.StringJoiner;

public class Tuple <V1, V2> {
    private V1 v1;
    private V2 v2;

    public Tuple(V1 v1, V2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public V1 getV1() {
        return v1;
    }

    public V2 getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (getV1() != null ? !getV1().equals(tuple.getV1()) : tuple.getV1() != null) return false;
        return getV2() != null ? getV2().equals(tuple.getV2()) : tuple.getV2() == null;
    }

    @Override
    public int hashCode() {
        int result = getV1() != null ? getV1().hashCode() : 0;
        result = 31 * result + (getV2() != null ? getV2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tuple.class.getSimpleName() + "[", "]")
                .add("v1=" + v1)
                .add("v2=" + v2)
                .toString();
    }
}
