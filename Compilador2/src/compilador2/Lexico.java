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
            String expresion = "lib";
           int val = mapeo(elemento,expresion);
           System.out.println("Valor : "+val);
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_reservadas = Pattern.compile("(auto|const|double|float|int|"
                + "short|struct|unsigned|break|continue|else|for|long|signed|"
                + "switch|void|case|default|enum|goto|register|sizeof|typedef|"
                + "volatile|char|string|do|extern|if|return|static|union|while|"
                + "true|false|using|namespace|std)");
        Matcher mat_reservadas = pat_reservadas.matcher(elemento);
        if (mat_reservadas.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_opAritmeticos = Pattern.compile("(\\+|-|\\*|/|%)");
        Matcher mat_opAritmeticos = pat_opAritmeticos.matcher(elemento);
        if (mat_opAritmeticos.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }*/
        
        
        Pattern pat_opAsignacion = Pattern.compile("(=|\\*=|/=|%=|\\+=|-=|<<=|>>=|&=|\\^=|\\|=)");
        Matcher mat_opAsignacion = pat_opAsignacion.matcher(elemento);
        if (mat_opAsignacion.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_puntuacion = Pattern.compile("(.|,|;|:)");
        Matcher mat_puntuacion = pat_puntuacion.matcher(elemento);
        if (mat_puntuacion.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_opComparacion = Pattern.compile("(==|!=|>|<|<=|>=)");
        Matcher mat_opComparacion = pat_opComparacion.matcher(elemento);
        if (mat_opComparacion.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_opLogicos = Pattern.compile("(&&|\\|\\||!)");
        Matcher mat_opLogicos = pat_opLogicos.matcher(elemento);
        if (mat_opLogicos.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_agrupan = Pattern.compile("(\\(|\\)|\\{|\\}|\\[|\\]|[\"]|[\'])");
        Matcher mat_agrupan = pat_agrupan.matcher(elemento);
        if (mat_agrupan.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_simbolos = Pattern.compile("(#|<<|>>)");
        Matcher mat_simbolos = pat_simbolos.matcher(elemento);
        if (mat_simbolos.matches())
        {
            System.out.println("SI");
        }
        else
        {
            System.out.println("NO");
        }
        
        
        Pattern pat_identificador = Pattern.compile("([a-zA-Z]([a-zA-Z]|[0-9]|_)*)");
        Matcher mat_identificador = pat_identificador.matcher(elemento);
        if (mat_identificador.matches())
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
    
    public int mapeo(String elemento, String expresion){
                //Librerias
                Map<String,Integer> map = new HashMap<>();
                map.put("iostream",10);		
                map.put("fsting",11 );		
                map.put("iosfw",12 );	
                map.put("list",13 );	
                map.put("cmath",14 );	
                map.put("memory",15 );
                map.put("numeric",16 );
                map.put("cstdio",17 );
                map.put("cstring",18 );
                map.put("cstdlib",19 );
               
              int val = map.get(elemento);
              
              return val;
     
                
    }
    
    
    
}
