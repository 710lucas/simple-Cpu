package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cpu {

    int[] registers = new int[0xF];
    int pc;
    byte[] opcodes;

    List<Integer> stack = new ArrayList<>();

    public Cpu(byte[] opcodes){
        this.opcodes = opcodes;
        pc = 0;
    }


    public void startCpu(){

        while(pc < opcodes.length){
            cpuRoutine();
        }

    }

    public void cpuRoutine(){
        int opcode = fetchOpcode();
        pc+=2;

        execOpcode(opcode);


    }

    public void execOpcode(int opcode){
        byte firstByte = (byte) ((opcode&0xFF00) >> 8);
        byte lastByte = (byte)(opcode&0x00FF);


        if(firstByte == 0x70) {
            registers[0] = lastByte;
        }
        else if(firstByte == 0x71)
            registers[1] = lastByte;

        else if(firstByte == 0x72)
            registers[2] = lastByte;

        else if((firstByte&0xF0) == 0x10){
            stack.add(pc);
            pc = lastByte;
        }
        else if((firstByte&0xF0) == 0x20)
            pc = lastByte;

        else if((firstByte&0xF0) == 0x30)
            registers[lastByte] = registers[firstByte & 0x0F];

        else if((firstByte&0xF0) == 0x40){
            registers[lastByte&0x0F] = registers[firstByte&0x0F] + registers[(lastByte&0xF0) >> 4];
        }

        else if((firstByte&0xF0) == 0x50){
            registers[lastByte&0x0F] = registers[firstByte&0x0F] - registers[(lastByte&0xF0) >> 4];
        }

        else if(firstByte == 0x0D) {
            System.out.println(registers[lastByte]);
        }
        else if(opcode == 0x000F) {
            pc = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
        }
        else if(firstByte == 0x0E){
            if(registers[lastByte - 0x0A] == 0){
                pc += 2;
            }
        }



    }

    public int fetchOpcode(){
        return (opcodes[pc] << 8) | opcodes[pc+1];
    }


}
