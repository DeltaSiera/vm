
public final class RealMachine {

    //    private int r1;         //bendros paskirties registras
//    private int r2;         //bendros paskirties registras
//    private int ic;         //2 baitų virtualios mašinos porgramų skaitiklis
//    private byte c;         //1 baito loginis trigeris
//    private int ptr;        //4 baitų puslapių lentelės registras
//    private int sp;         //4baitų steko viršūnės registras
//    private Mode mode;      // vartotojo arba supervizoriaus režimas
//    private byte si;
//    private byte pi;
//    private byte k1;
//    private byte k2;
//    private byte k3;
//    private byte ti;        //taimerio trigeris
//
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
