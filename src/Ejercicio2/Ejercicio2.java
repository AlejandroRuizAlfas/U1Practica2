package Ejercicio2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        String commands = "java -jar out\\artifacts\\Random10_jar\\U1Practica2.jar";
        List<String> argsList = new ArrayList<>(Arrays.asList(commands.split(" ")));
        ProcessBuilder pb = new ProcessBuilder(argsList);

        try {
            Process process = pb.start();
            OutputStream os = process.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            Scanner precesoSC = new Scanner(process.getInputStream());

            Scanner sc = new Scanner(System.in);
            String linea = sc.nextLine();
            while (!linea.equals("stop")){
                bw.write(linea);
                bw.newLine();
                bw.flush();
                System.out.println(precesoSC.nextLine());
                linea = sc.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
