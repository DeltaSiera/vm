public class Main {
    static int[] hello = new int[]{
            OperationCode.PRINT,
            OperationCode.HALT,
    };


    public static void main(String[] args) {
        RealMachine realMachine = new RealMachine(hello);
        realMachine.push(44);
        realMachine.run();
    }
}
