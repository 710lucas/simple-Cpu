public class Memory {
    private int[] memory;
    public Memory(int size){
        memory = new int[size];
    }
    public int get(int pos){
        return memory[pos];
    }

    public int[] getMemory(){
        return memory;
    }

}
