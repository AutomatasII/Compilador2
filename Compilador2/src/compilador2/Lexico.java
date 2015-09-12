/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador2;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexico {

    private StringTokenizer lista_separada_linea;
    
    //
    public Lexico(String texto) {
        //String con todo el codigo
        lista_separada_linea = new StringTokenizer(texto,"\n");
        init();
      
    }
    
    public void Tokens(String linea){
        StringTokenizer lista_separada_espacio = new StringTokenizer(linea);
        while(lista_separada_espacio.hasMoreTokens()){
            Expresiones(lista_separada_espacio.nextToken());
        }
    
    }
   /**
    * Revisa si el elemento mandado matchea con una expresion regular
    *@param elemento string que puede ser simbolo o palabra que esta separada en un token
    */
    public void Expresiones(String elemento){
        
        Pattern pat_librerias = Pattern.compile("(iostream|fstream|iosfwd|list|cmath|memory|numeric|cstdio|cstring|cstdlib)");
        Matcher mat_librerias = pat_librerias.matcher(elemento);
        if (mat_librerias.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        Pattern pat_reservadas = Pattern.compile("(auto|const|double|float|int|"
                + "short|struct|unsigned|break|continue|else|for|long|signed|"
                + "switch|void|case|default|enum|goto|register|sizeof|typedef|"
                + "volatile|char|do|extern|if|return|static|union|while)");
        Matcher mat_reservadas = pat_reservadas.matcher(elemento);
        if (mat_reservadas.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        Pattern pat_opAritmeticos = Pattern.compile("(+|-|*|/)");
        Matcher mat_opAritmeticos = pat_opAritmeticos.matcher(elemento);
        if (mat_reservadas.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
    }
    
    
    public void init(){
        while(lista_separada_linea.hasMoreTokens()){
            Tokens(lista_separada_linea.nextToken());
        }
   
    }
    
    
    
}
