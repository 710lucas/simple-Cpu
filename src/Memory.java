public class Memory {
    private int[] memory;
    public Memory(int size){
        memory = new int[size];
    }
    public int get(int pos){
        return memory[pos];
    }

    public void setMemory(int[] memory){
        this.memory = memory;
    }

    public int[] getMemory(){
        return memory;
    }

}
