package Ejercicio1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ejercicio1 {
    public static void main(String[] args) {
        if (args.length <= 0){
            System.out.println("No se ha pasado ningñun argumento, cerrando programa");
            System.exit(-1);
        }

        List<String> listaArgs = new ArrayList<>();
        String OS = System.getProperty("os.name");

        if (!OS.equals("Linux")){
            listaArgs.add("cmd.exe");
            listaArgs.add("/c");
        }

        for (int i=0;i< args.length;i++){
            listaArgs.add(args[i]);
        }

        try{
            ProcessBuilder pb = new ProcessBuilder(listaArgs);
            Process process = pb.start();
            if (process.waitFor(2, TimeUnit.SECONDS)){
                InputStream inputStream = process.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line;
                File f = new File("output.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                while ((line = br.readLine()) != null){
                    writer.write(line+"\n");
                    System.out.println(line);
                }
                writer.close();
            }
        }catch(IOException ex) {
            System.err.println("Tiempo de espera agotado");
            System.exit(-1);
        }catch(InterruptedException ex) {
            System.err.println("Proceso interrumpido");
            System.exit(-1);
        }
    }
}
