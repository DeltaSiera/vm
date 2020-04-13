import java.util.HashMap;

public class OperationCode {
    public static final int ADD = 1;
    public static final int SUB = 2;
    public static final int MUL = 3;
    public static final int DIV = 4;
    public static final int CMP = 5;
    public static final int HALT = 16;
    public static final int PRINT = 20;
    public static HashMap<Integer, String> instructionsList = new HashMap<>();

    static {
        instructionsList.put(1, "ADD");
        instructionsList.put(2, "SUB");
        instructionsList.put(3, "MUL");
        instructionsList.put(4, "DIV");
        instructionsList.put(5, "CMP");
        instructionsList.put(20, "PRINT");
        instructionsList.put(16, "HALT");
    }

    public static class Instruction {
        String name;
        int number;

        public Instruction(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }
}
