public class RealMachineMemory {
    private int memory[];

//    private static int REAL_MACHINE_INSTANCE;

    public RealMachineMemory() {
       this.memory = new int[512];
    }

    public void addToMemory(int address, int word){
        memory[address]=word;
    }
}
