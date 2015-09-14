/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador2;

/**
 *
 * @author Uriel
 */
public class Token{
    int codigo;
    String categoria;
    String tipo_dato;

    public Token(String cate, int cod) {
        categoria = cate;
        codigo = cod;
        tipo_dato = null;
    }
     public Token() {
        
    }
    @Override
    public String toString(){
        return categoria + " : " + codigo;
    }
     //asdfdfasdfsdff
    //hola manco del beto
    
    
}
