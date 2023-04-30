import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static int[] leArquivoCompilado(String filename) throws IOException {
        List<String> file = Files.readAllLines(Path.of(filename));
        int[] out = new int[file.size()];
        for(int i = 0; i<file.size(); i++){
            out[i] = Integer.parseInt(file.get(i));
        }
        return out;
    }

    public static void main(String[] args) throws Exception {

        /*
            ler arquivo
            ler linha por linha
            executar opcodes

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

         */

//         IMPROVISO PARA LER ARQUIVO EM """""""HEXADECIMAL"""""""""
//        Scanner sc = new Scanner(System.in);
//        String nomeArquivo =sc.nextLine();
//        String arquivoStr = new String(Files.readAllBytes(Paths.get(nomeArquivo)));
//
//
//
//        for(String quatro : separaEmQuatro(arquivoStr.replace("\n", "").replace("\r", ""))){
//            System.out.println(Integer.parseInt(quatro, 16));
//        }



//        int[] subtracao = {
//                0x1100,
//                0x1011,
//                0x9000,
//                0x7006,
//                0x6000,
//                0x9000,
//                0x4010,
//                0x6000,
//                0x9000,
//                0x8000
//        };
//
//
////        int[] arquivo = {0x10A0, 0x6000};
//
//        int[] arquivo = subtracao;
//
        int[] arquivo = leArquivoCompilado("teste");
        Cpu cpu = new Cpu(arquivo);
        cpu.startCpu();





//        sc.close();

    }
}