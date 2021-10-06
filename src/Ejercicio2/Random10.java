package Ejercicio2;

import java.util.Random;
import java.util.Scanner;

public class Random10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cadena = "";
        Random rand = new Random();

        while (!cadena.equals("stop")) {
            cadena = input.nextLine();
            cadena = cadena.toLowerCase();
            System.out.println(rand.nextInt((10 - 0) + 1) + 0);
        }
        System.out.println("Fin");
    }
}
