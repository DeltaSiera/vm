public class VirtualMemory {
    private int[] memory;
    private int size = 0;

    public VirtualMemory(int size) {
        this.size = size;
        this.memory = new int[size];
    }
}
