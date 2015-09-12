/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador2;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author amito_000
 */
public class ExtractorTexto {
    
    private ArrayList<String> lista;
    private String ruta;

    public ExtractorTexto(String ruta) {
        this.ruta = ruta;
        lista = new ArrayList();
        //un comentatrio
    }
    
    public void sacaTexto() throws FileNotFoundException, IOException{
        File archivo = new File(ruta);
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String aux = lector.readLine();
        while(aux != null){
            lista.add(aux);
            aux = lector.readLine();
        }
        lector.close();
    }    
    
    public ArrayList<String> getLista(){
        return lista;
    }
}
