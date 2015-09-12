/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador2;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexico {

    private String[] lista;
    //Uriel
    public Lexico(String texto) {

        lista = texto.split("");
        
        Scanner sc = new Scanner(System.in);
        String prueba;
        
        System.out.print("Probando librerias: ");
        prueba = sc.nextLine();
        Pattern patLibreria = Pattern.compile("#include");
        Matcher matLibreria = patLibreria.matcher(prueba);
        
         if(matLibreria.find())
         {
             System.out.println("Libreria Válida");
         }
         else
         {
          System.out.println("Libreria No Válida");
         }
    

        lista = texto.split("\n|;| ");
    }
    public void impLista(){
        for(String i : lista)
            if(!i.equals(""))
                System.out.println(i);
        //hola desde beto

    }
    
}
