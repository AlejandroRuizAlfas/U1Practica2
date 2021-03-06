package Ejercicio3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {

        //Permite detectar si el SO es Windows o Linux para escoger la ruta relativa del archivo .jar correcta
        String OS = System.getProperty("os.name");
        String commands = "";
        if (!OS.equals("Linux")){
            commands = "java -jar out\\artifacts\\minus_jar\\Minusculas.jar";
        }else{
            commands = "java -jar out/artifacts/minus_jar/Minusculas.jar";
        }
        //Guarda los argumentos en una lista separados cada y crea una estancia de ProcessBuilder pasándole dichos argumentos
        List<String> argsList = new ArrayList<>(Arrays.asList(commands.split(" ")));
        ProcessBuilder pb = new ProcessBuilder(argsList);

        //Intenta iniciar el proceso hijo y prepara el canal de comunicación entre padre e hijo
        try {
            Process process = pb.start();
            OutputStream os = process.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            Scanner procesoSC = new Scanner(process.getInputStream());

            //A través del Scanner trae el resultado de la salida del proceso hijo al proceso padre, y procede a ejecutar el programa Minusculas.jar hasta
            //que al proceso hijo le llegue la palabra "finalizar" y se acabe la comunicación
            Scanner sc = new Scanner(System.in);
            String linea = sc.nextLine();
            while (!linea.equalsIgnoreCase("finalizar")){
                bw.write(linea);
                bw.newLine();
                bw.flush();
                System.out.println(procesoSC.nextLine());
                linea = sc.nextLine();
            }
            //En caso de ocurrir un problema o excepción durante la comunicación y lectura/escritura, se capturaria dicha excepción
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
