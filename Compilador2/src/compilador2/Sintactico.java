/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador2;

import java.util.ArrayList;

/**
 *
 * @author Tsukunowo
 */
public class Sintactico {

    ArrayList comparacion;
    ArrayList<Token> comparacion2;

    public Sintactico(ArrayList aux) {
        comparacion = aux;
    }
    public boolean BuscarDeclaracion(int posicion, ArrayList<Token> sentencia, String texto)
    {
        if (sentencia.get(posicion).codigo == 52) {
            BuscarDeclaracion(posicion + 1, sentencia, "double");
        }
        if (sentencia.get(posicion).codigo == 53) {
            BuscarDeclaracion(posicion + 1, sentencia, "float");
        }
        if (sentencia.get(posicion).codigo == 54) {
            BuscarDeclaracion(posicion + 1, sentencia, "int");
        }
        if (sentencia.get(posicion).codigo == 55) {
            BuscarDeclaracion(posicion + 1, sentencia, "short");
        }
        if (sentencia.get(posicion).codigo == 74) {
            BuscarDeclaracion(posicion + 1, sentencia, "char");
        }
        if (sentencia.get(posicion).codigo == 75) {
            BuscarDeclaracion(posicion + 1, sentencia, "String");
        }
        if (sentencia.get(posicion).codigo > 1000) {
            sentencia.get(posicion).tipo_dato=texto;
            BuscarDeclaracion(posicion+1, sentencia, texto);
        }
         if (sentencia.get(posicion).codigo == 90) {
            return true;
        }
        
        return false;
    }

    public boolean BuscarMain(int posicion, ArrayList<Token> sentencia) {
        if (sentencia.get(posicion).codigo == 54) {
            BuscarMain(posicion + 1, sentencia);
            return true;
        }
        if (sentencia.get(posicion).codigo == 90) {
            return true;
        }
        return false;
    }

    public boolean BuscarAsignacion(int posicion, ArrayList<Token> sentencia) {
        if (sentencia.get(posicion).codigo >= 1000)
            BuscarAsignacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 250){
            BuscarOperacion(posicion + 1, sentencia);
        }    
        return false;
    }
    
    public boolean BuscarFor(int posicion, ArrayList<Token> sentencia)
    {
        if (sentencia.get(posicion).codigo == 62)
            BuscarFor(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 450)
            BuscarAsignacion(posicion + 1, sentencia);
        return false;
    }
    
    public boolean BuscarWhile(int posicion, ArrayList<Token> sentencia)
    {
        if (sentencia.get(posicion).codigo == 82)
            BuscarFor(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 450)
            BuscarAsignacion(posicion + 1, sentencia);
        return false;
    }
    
    public boolean BuscarIf(int posicion, ArrayList<Token> sentencia)
    {
        if (sentencia.get(posicion).codigo == 78)
            BuscarFor(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 450)
            BuscarAsignacion(posicion + 1, sentencia);
        return false;
    }

    public boolean BuscarOperacion(int posicion, ArrayList<Token> sentencia)
    {
        if (sentencia.get(posicion).codigo >= 1000)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 200)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 201)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 202)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 203)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 204)
            BuscarOperacion(posicion + 1, sentencia);
        if (sentencia.get(posicion).codigo == 302)
            return true;
        return false;
    }
    
    public void Analizar() {
        for (Object i : comparacion) {
            int cont=0;
            ArrayList<Token> aux = (ArrayList<Token>) i;
            if(!BuscarMain(0, aux))
                {
                   System.out.println("Main not found nigga!");
                    
                }
                
            /*BuscarDeclaracion(0, aux, null);
            BuscarFor(0, aux);
            BuscarIf(0, aux);
            BuscarOperacion(0, aux);
            BuscarWhile(0, aux);
                */

        }

    }

}
