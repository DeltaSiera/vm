
public final class RealMachine {

    private static final int MAX_CODE_SEGMENT_SIZE = 256;
    private static final int MAX_STACK_SEGMENT_SIZE = 256;

    private int sp;
    private int ip;

    private final int[] memory;
    public boolean trace;
    private Mode mode;

    public RealMachine(int[] code) {
        this.sp = MAX_CODE_SEGMENT_SIZE;
        this.ip = 0;
        this.mode = Mode.USER;
        this.memory = new int[MAX_CODE_SEGMENT_SIZE + MAX_STACK_SEGMENT_SIZE];
        System.arraycopy(code, 0, memory, 0, code.length);
    }

    public void push(int value) {
        memory[++sp] = value;
    }

    private int pop() {
        return memory[sp--];
    }

    public void run() {
        while (this.ip < MAX_CODE_SEGMENT_SIZE && this.memory[ip] != 0) {
            int opcode = memory[ip];
            if (trace) {
                System.err.printf("%04d: %s\n", ip, OperationCode.instructionsList.get(opcode));
            }
            this.ip++;
            switch (opcode) {
                case OperationCode.ADD:
                    int first = pop();
                    int second = pop();
                    push(second + first);
                    break;
                case OperationCode.SUB:
                    first = pop();
                    second = pop();
                    push(second - first);
                    break;
                case OperationCode.MUL:
                    first = pop();
                    second = pop();
                    push(first * second);
                    break;
                case OperationCode.DIV:
                    first = pop();
                    second = pop();
                    push(second / first);
                    break;
                case OperationCode.CMP:
                    first = memory[sp];
                    second = memory[sp - 1];
                    if (first == second) {
                        memory[++sp] = 0;
                    } else if (first > second) {
                        memory[++sp] = -1;
                    } else {
                        memory[++sp] = 1;
                    }
                    break;
                case OperationCode.PRINT:
                    System.out.println(memory[sp]);
                    break;
                case OperationCode.HALT:
                    System.out.println("Halt");
                    return;
                default:
                    break;
            }
        }
    }
}
