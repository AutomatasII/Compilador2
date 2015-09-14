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

    public boolean BuscarMain(int posicion, ArrayList<Token> sentencia) {
        if (sentencia.get(posicion).codigo == 54) {
            BuscarMain(posicion + 1, sentencia);
        }
        if (sentencia.get(posicion).codigo == 1) {
            BuscarMain(posicion + 1, sentencia);
        }
        if (sentencia.get(posicion).codigo == 90 || sentencia.get(posicion).codigo == 302) {
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

    public boolean BuscarOperacion(int posicion, ArrayList<Token> sentencia){
        
        return false;
    }
    
    public void Analizar() {
        for (Object i : comparacion) {
            ArrayList<Token> aux = (ArrayList<Token>) i;
            for (int j = 0; j < aux.size(); j++) {
                BuscarMain(j, aux);

            }

        }

    }

}