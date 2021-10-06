package Ejercicio3;

import java.util.Scanner;

public class Minusculas {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cadena = "";
        while (!cadena.equals("finalizar")){
            cadena = input.nextLine();
            cadena = cadena.toLowerCase();
            if (cadena.equals("finalizar"))
                break;
            System.out.println(cadena);
        }
    }
}
