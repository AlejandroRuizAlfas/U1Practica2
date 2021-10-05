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
        String OS = System.getProperty("os.name");
        String commands = "";
        if (!OS.equals("Linux")){
            commands = "java -jar out\\artifacts\\minus_jar\\U1Practica2.jar";
        }else{
            commands = "java -jar out/artifacts/minus_jar/U1Practica2.jar";
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
            while (!linea.equalsIgnoreCase("finalizar")){
                bw.write(linea);
                bw.newLine();
                bw.flush();
                System.out.println(procesoSC.nextLine());
                linea = sc.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
