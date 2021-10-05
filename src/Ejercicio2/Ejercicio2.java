package Ejercicio2;

import java.io.*;
import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        String OS = System.getProperty("os.name");
        String commands="";
        if (!OS.equals("Linux")){
            commands = "java -jar out\\artifacts\\Random10_jar\\U1Practica2.jar";
        }else{
            commands = "java -jar out/artifacts/Random10_jar/U1Practica2.jar";
        }

        List<String> argsList = new ArrayList<>(Arrays.asList(commands.split(" ")));
        ProcessBuilder pb = new ProcessBuilder(argsList);

        try {
            Process process = pb.start();
            OutputStream os = process.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
