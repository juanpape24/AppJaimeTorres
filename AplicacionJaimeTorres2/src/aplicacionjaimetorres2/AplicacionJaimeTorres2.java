/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionjaimetorres2;

import aplicacionjaimetorres2.BD.BD;
import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class AplicacionJaimeTorres2 {

    /**
     * @param args the command line arguments
     */
    static BD bd = new BD();
    static Scanner sc = new Scanner(System.in);
    static Connection con;

    public static void main(String[] args) {
        System.out.println("Inicio de Aplicacion");

        con = bd.connectDatabase("localhost", "5432", "BDJaime", "postgres", "admin");
        String opcion = "";
        while (!opcion.equals("4")) {
            System.out.println("Ingrese la opcion que desea: ");
            System.out.println("1. Consultar las pinturas de un artista especifico");
            System.out.println("2. Consultar el artista de una pintura especifica");
            System.out.println("3. Consultar el precio total de las pinturas de un museo especifico");
            System.out.println("4. Salir");
            opcion = sc.next();
            switch (opcion) {
                case "1":
                    opcion1();
                    break;
                case "2":
                    opcion2();
                    break;
                case "3":
                    opcion3();
                    break;
            }

        }

        bd.CloseConnection(con);
    }

    public static void opcion1() {
        System.out.println("Por favor elija un artista: ");
        bd.getArtistas(con);
        String opcA = sc.next();
        bd.getPinturasbyArtista(con, opcA);
    }

    public static void opcion2() {
        System.out.println("Por favor elija una Pintura: ");
        bd.getPinturas(con);
        String opcP = sc.next();
        bd.getArtistabyPintura(con, opcP);
    }
    
    public static void opcion3() {
        System.out.println("Por favor elija un Museo: ");
        bd.getMuseos(con);
        String opcM = sc.next();
        bd.getPrecioPinturasMuseo(con, opcM);
    }

}
