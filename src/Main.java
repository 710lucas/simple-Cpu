import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> separaEmQuatro(String inp){

        ArrayList<String> out = new ArrayList<>();
        String tmp="";
        int contador = 0; //conta quantas letras foram lidas
        for(int i = 0; i<inp.length(); i++){
            if(contador == 4 && i != inp.length()-1){
                out.add(tmp);
                tmp="";
                contador = 1;
                tmp+=inp.charAt(i);
            }
            else if(i == inp.length()-1){
                tmp+=inp.charAt(i);
                out.add(tmp);
            }
            else{
                tmp += inp.charAt(i);
                contador++;
            }
        }

        return out;
    }

    public static void main(String[] args) throws Exception {

        /*
            ler arquivo
            ler linha por linha
            executar opcodes

            OPCODES (decimal):
            1AXX --> carrega o valor A no local de memoria XX
            20XX --> armazena o valor no local XX no registrador temporario
            30XX --> soma o valor no local XX com o valor no tmpReg e armazena o resultado no local XX
            40XX --> soma o valor no local XX com o valor no tmpReg e armazena o resultado no local tmpReg
            50XX --> printa o valor em XX

            80XX --> pula para valor X de memoria

         */
        Scanner sc = new Scanner(System.in);
        String nomeArquivo =sc.nextLine();
        String arquivo = new String(Files.readAllBytes(Paths.get(nomeArquivo))).replace("\r", "").replace("\n", "");

        ArrayList<String> opcodes = separaEmQuatro(arquivo);
        Cpu cpu = new Cpu(opcodes);
        cpu.startCpu();



        sc.close();

    }
}