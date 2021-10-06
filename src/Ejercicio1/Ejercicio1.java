package Ejercicio1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ejercicio1 {
    public static void main(String[] args) {
        // El fragmemnto de codigo detecta si se han pasado argumentos o no y cierra el programa en caso negativo
        if (args.length <= 0){
            System.out.println("No se ha pasado ningun argumento, cerrando programa");
            System.exit(-1);
        }
        //Crea la lista de argumentos que se le pasen al ejecutar el jar desde comando
        List<String> listaArgs = new ArrayList<>();

        //Permite detectar si el SO es Windows o Linux para escoger la ruta relativa del archivo .jar correcta
        String OS = System.getProperty("os.name");
        if (!OS.equals("Linux")){
            listaArgs.add("cmd.exe");
            listaArgs.add("/c");
        }
        //Se guardan los argumentos en la lista de argumentos
        for (int i=0;i< args.length;i++){
            listaArgs.add(args[i]);
        }
        //Se crear a tarvés del ProcessBuilder el proceso el cual recibe los argumentos antes de su ejecución
        try{
            ProcessBuilder pb = new ProcessBuilder(listaArgs);
            Process process = pb.start();
            //El padre espera dos segundos a que el proceso hijo termine. Si este termina antes, se ejecutará correctamente, si pasan dos segundo y aun no termina,
            //el padre interrumpirá la ejecución del proceso hijo
            if (process.waitFor(2, TimeUnit.SECONDS)){
                InputStream inputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);

                //Se guarda el resultado de la ejecución del comando en el fichero output.txt
                String line;
                File f = new File("output.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                while ((line = br.readLine()) != null){
                    writer.write(line+"\n");
                }

                //Utilizando el valor de salida (cantidad de errores que se han producido) se indica si la ejecución ha ido bien o no.
                int valorSalida = process.waitFor();
                if (valorSalida == 0){
                    System.out.println("El proceso ha finalizado correctamente y el resultado se ha guardado en output.txt. \nEl valor de salida a sido: "+valorSalida);
                }else{
                    System.out.println("Error. El comando no existe o esta mal escrito");
                }
                writer.close();
            }
        //Si el programa falla y se lanza una excepción, ya sea de entrada/salida o interrupción de al espera del proceso, se capturarán en los catch correspondientes
        }catch(IOException ex) {
            System.err.println("Tiempo de espera agotado");
            System.exit(-1);
        }catch(InterruptedException ex) {
            System.err.println("Proceso interrumpido");
            System.exit(-1);
        }
    }
}
