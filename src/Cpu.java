import java.util.Arrays;


/*

OPCODES
1XXA    -> carrega o valor XX no registrador A
20AB    -> pega o valor do registrador A e armazena no reg B
3ABC    -> soma o valor do registrador A, soma com B e armazena em C
4ABC    -> subtrai o valor do registrador A com B e armazena em C
5XXX    -> pula para o valor XXX da memoria
600A    -> printa o valor no reg A

7XXX    -> chama a subroutine no local de memoria XXX
8000    -> sai da subroutine

900A    -> pula a proxima instrução se o valor no registrador A for 0
0XXX    -> pula para o endereço de memoria XXX

TODO
    [ ] Subroutine
 */

public class Cpu {
    private int pc = 0;
    private int tmpReg;
    private int[] regs = new int[4];
    //[0] -> A
    //[1] -> B
    //[2] -> C
    //[3] -> Carry

    private  int[] stack = new int[100];

    private Memory mem = new Memory(100); //1000b (actually more because it's a string and yeah, but that's just for testing)

    public Cpu(int[] opcodes) {
        int i = 0;

        mem.setMemory(opcodes);

        Arrays.fill(stack, -1);
    }

    public void startCpu() throws Exception {
        while (pc < mem.getMemory().length && mem.getMemory()[pc] != 0) {
//            System.out.printf("%d %d    A: %d  B: %d  C: %d\n", pc, mem.get(pc), regs[0], regs[1], regs[2]);
//            System.out.println(Arrays.toString(stack));
            execOpcode(mem.get(pc));
        }
    }

    public void execOpcode(int opcode) throws Exception {


        int firstDigit = ((opcode&0xF000)); //>>12
        int secondDigit = (opcode&0x0F00); //>>8
        int thirdDigit = (opcode&0x00F0); //>>4
        int lastDigit = (opcode&0x000F);

        int middle = (opcode&0x0FF0);
//        System.out.println(firstDigit);
//        System.out.println(secondDigit);
//        System.out.println(thirdDigit);
//        System.out.println(lastDigit);
//        pc++;


        //1234
        //1
        //12 - 10 -> 2
        //123 - (10+2)*10 -> 123 - 120 = 3
        //1234 - ((((1*10)+2)*10)+3)*10 = 4


        switch (firstDigit){
            case(0x1000):
                regs[lastDigit] = middle>>4;
                pc++;
                break;

            case (0x2000):
                regs[lastDigit] = regs[thirdDigit>>4];
                pc++;
                break;

            case(0x3000):
                regs[lastDigit]=regs[secondDigit>>8]+regs[thirdDigit>>4];
                pc++;
                break;

            case (0x4000):
                regs[lastDigit] = regs[secondDigit>>8]-regs[thirdDigit>>4];
                pc++;
                break;

            case(0x5000):
                pc = ((middle>>4)*10)+ lastDigit;
                pc++;
                break;

            case 0x6000:
                System.out.println(regs[lastDigit]);
                pc++;
                break;

            case (0x7000):
                addValueToStack(pc);
                pc = ((middle>>4)*10)+ lastDigit;
                break;

            case (0x8000):
                pc = getValueFromStack();
                break;

            case (0x9000):
                if(regs[lastDigit] == 0)
                    pc+=2;
                else
                    pc++;
                break;

            default:
                pc++;
                break;
        }


    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * This method takes a value and add it to the next position
     * in the stack
     * the next position is defined by a value that's -1
     * @param val: value to be added to stack
     */
    public void addValueToStack(int val){
        for(int i = 0; i<stack.length; i++){
            if(stack[i] == -1){
                stack[i] = val;
                return;
            }
        }
    }
    private int getValueFromStack() throws Exception {
        for (int i = stack.length - 1; i >= 0; i--) {
            if (stack[i] != -1) {
                int tmp = stack[i];
                stack[i] = -1;
                return tmp;
            }
        }
        throw new Exception("There is fuck");
    }


}
