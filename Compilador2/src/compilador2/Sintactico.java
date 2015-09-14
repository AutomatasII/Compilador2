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
    public Sintactico(ArrayList aux){
        comparacion=aux;
    }
    public boolean Buscar(int posicion, ArrayList<Token> sentencia)
    {
            if(sentencia.get(posicion).codigo==54)
            {
                Buscar(posicion+1,sentencia);
            }
            if(sentencia.get(posicion).codigo==90 || sentencia.get(posicion).codigo==302)
                return true;
        return false;
    }
    public void EncontrarMain(){
        for (Object i : comparacion) {
            ArrayList<Token> aux=(ArrayList<Token>) i;
            for (int j=0; j<aux.size();j++)
            {
                Buscar(j,aux);
            }
                
        }
        
    }
    
}
