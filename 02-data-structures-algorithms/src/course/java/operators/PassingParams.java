package course.java.operators;

class MyInt{
    private int value;
    public int getValue(){
        return value;

    }
    public MyInt(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }
}

public class PassingParams {
    public static void update(MyInt n){
        n = new MyInt(42);
    }
    public static void main(String[] args) {
        var x = new MyInt(12);
        update(x);
        System.out.println(x);
    }
}
