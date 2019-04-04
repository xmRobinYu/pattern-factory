
public class Test {

    public static void main(String[] args) {
        String className = "Hello";
        System.out.println(firstCharToLow(className));
    }

    private static String firstCharToLow(String className) {
        char[] clssCharArray = className.toCharArray();
        if (clssCharArray.length == 0) {
            return className;
        }
        if (clssCharArray[0] <= 90 && clssCharArray[0] >= 65) {
            clssCharArray[0] += 32;
            return String.valueOf(clssCharArray);
        }
        return className;
    }
}
