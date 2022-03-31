package course.java.operators;

public class BitOps {
    public static String toBinary(int n) {
        var result = new StringBuilder();
        for(int i = 0; i < 32; i ++) {
//        do{
            result.append(Math.abs(n % 2));
            n = n >>> 1;
        }
//        } while (n > 0);
        return result.reverse().toString(); // TODO write code here
    }

    public static int fromBinary(String binary) {
        int result = 0;
        for (int i = binary.length() - 1, j = 0; i >= 0; i--, j++) {
            result += (1 << j) * (binary.charAt(i) - '0');
        }
        return result;
    }


    public static void main(String[] args) {
//        System.out.printf(toBinary(495));
        var n = 495;
        System.out.printf("%32.32s%n", Integer.toBinaryString(n));
//        System.out.printf("%32.32s%n", Integer.toBinaryString(n >> 1));
        System.out.printf("%32.32s%n", toBinary(n));
//        System.out.printf("%32.32s%n", toBinary(n >> 1));
        System.out.printf("%d%n", fromBinary(toBinary(n)));
        var m = 219;
        System.out.printf("%32.32s%n", toBinary(n));
        System.out.printf("%32.32s%n", toBinary(m));
        System.out.printf("%32.32s%n", toBinary(~m));
    }
}
