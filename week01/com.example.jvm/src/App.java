public class App {
    public static void main(String[] args) throws Exception { 
        int a = 30, b = 10;
        add(a, b);
        subtract(a, b);
        multiply(a, b);
        divide(a, b);
        testIf(a,b);
        for(int i=0;i<3;i++){
            a++;
        }
        System.out.println("Hello, World!");
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }


    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int divide(int a, int b) {
        return a / b;
    }

    private static void testIf(int a, int b) {
        if(a>b){
            a++;
        }else {
            a--;
        }
    }
}
