package org.example;

/*

    0x7AXX -> armazena o valor de X em A
    0x1XXX -> vai para a subrotina que esta no valor XXX da memoria
    0x2XXX -> pula para o valor XXX da memoria
    0x3A0B -> tira o valor de A e armazena em B
    0x4ABC -> C = A + B
    0x5ABC -> C = A - B
    0x0D0A -> printa o valor em A
    0x000F -> sai da subrotina
    0x0E0A -> pula a proxima instrução se o valor em a for 0

 */


public class Main {
    public static void main(String[] args) {

        byte[] teste = {
                0x70, 0x15,
                0x0D, 0x00,
                0x20, 0x08,
                0x71, 0x05,
                0x0D, 0x01,
                0x30, 0x01,
                0x0D, 0x01,
                0x40, 0x12,
                0x0D, 0x02,
                0x40, 0x00,
                0x0D, 0x00

        };

        Cpu cp = new Cpu(teste);
        cp.startCpu();


    }
}