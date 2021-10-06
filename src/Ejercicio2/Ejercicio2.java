package Ejercicio2;

import java.io.*;
import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {

        //Permite detectar si el SO es Windows o Linux para escoger la ruta relativa del archivo .jar correcta
        String OS = System.getProperty("os.name");
        String commands="";
        if (!OS.equals("Linux")){
            commands = "java -jar out\\artifacts\\Random10_jar\\Random10.jar";
        }else{
            commands = "java -jar out/artifacts/Random10_jar/Random10.jar";
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

            //A través del Scanner trae el resultado de la salida del proceso hijo al proceso padre, y procede a ejecutar el programa Random10.jar hasta
            //que al proceso hijo le llegue la palabra "stop" y se acabe la comunicación. Durante la comunicación, el proceso padre irá guardando todos los numeros
            //aleatorios que genere el proceso hijo en el fichero randoms.txt.
            Scanner procesoSC = new Scanner(process.getInputStream());
            Scanner sc = new Scanner(System.in);
            String linea = sc.nextLine();
            BufferedWriter wrFichero = new BufferedWriter(new FileWriter("randoms.txt"));
            while (!linea.equalsIgnoreCase("stop")){
                bw.write(linea);
                bw.newLine();
                bw.flush();
                String cadenaRecibida = procesoSC.next();
                System.out.println(cadenaRecibida);
                wrFichero.write(cadenaRecibida+"\n");
                linea = sc.nextLine();
            }
            wrFichero.close();
         //En caso de ocurrir un problema o excepción durante la comunicación y lectura/escritura, se capturaria dicha excepción
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
