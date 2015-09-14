/*
 * Revisa si el elemento mandado matchea con una expresion regular
 * La clase lexico separa el string en saltos de linea y despues en espacios en blanco y ve cada token en que clase de expresion regular metchea y lo agrega a un Array list 
 *   
 */
package compilador2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexico {

    private StringTokenizer lista_separada_linea;
    private Map<String, Integer> map;
    private int i;
    int num_linea;
    private ArrayList sentencias;
    private String texto;
    ArrayList<Token> errores;

    public Lexico(String texto) {
        this.texto = texto;
        //String con todo el codigo
        lista_separada_linea = new StringTokenizer(texto, "\n");
        map = new HashMap<>();
        sentencias = new ArrayList<>();
        errores = new ArrayList<>();
        i = 1000;
        num_linea = 0;      
        init();
    }

    /**
     * Recorre el string linea a linea y lo manda al metodo Tokens para separar
     * cada sentencia por tokens
     */
    public void init() {
        while (lista_separada_linea.hasMoreTokens()) {
            sentencias.add(Tokens(lista_separada_linea.nextToken()));
            num_linea++;
        }
    }
    /**
     * Devuelve el texto tokenizado como un solo arreglo
     * @return Una lista de tokens
     */
    public ArrayList<Token> init2() {
        return Tokens(texto);
    }

    public ArrayList<Token> Tokens(String linea) {
        ArrayList<Token> sentencia = new ArrayList<>();
        StringTokenizer lista_separada_espacio = new StringTokenizer(linea);
        String sig_elemento = "";
        while (lista_separada_espacio.hasMoreTokens()) {
            Token token_posible = null;
            sig_elemento = lista_separada_espacio.nextToken();
            token_posible = Expresiones(sig_elemento);
            if (token_posible != null) {
                sentencia.add(token_posible);
            } else {
                Token token_error = new Token(sig_elemento, num_linea);
                Errores(token_error);
            }
        }

        return sentencia;

    }

    public void Errores(Token error) {
        errores.add(error);
    }

    /**
     * Regresa un Arraylist de Tokens donde el string es el error y el int es la
     * linea
     * @return la lista de errores
     */
    public ArrayList<Token> getErrores() {

        return errores;
    }

    /**
     * Revisa si el elemento mandado matchea con una expresion regular
     *
     * @param elemento string que puede ser simbolo o palabra que esta separada
     * en un token
     * @return el token
     */
    public Token Expresiones(String elemento) {
        Token token = null;
        Token token2 = null;
        String expresion = null;
        Pattern pat_librerias = Pattern.compile("(iostream|fstream|iosfwd|list|cmath|memory|numeric|cstdio|cstring|cstdlib)");
        Matcher mat_librerias = pat_librerias.matcher(elemento);
        if (mat_librerias.matches()) {
            expresion = "libreria";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_reservadas = Pattern.compile("(auto|const|double|float|int|"
                + "short|struct|unsigned|break|continue|else|for|long|signed|"
                + "switch|void|case|default|enum|goto|register|sizeof|typedef|"
                + "volatile|char|string|do|extern|if|return|static|union|while|"
                + "true|false|using|namespace|std|cout|cin|main|include)");
        Matcher mat_reservadas = pat_reservadas.matcher(elemento);
        if (mat_reservadas.matches()) {
            expresion = "palabra_reservada";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_opAritmeticos = Pattern.compile("(\\+|-|\\*|/|%)");
        Matcher mat_opAritmeticos = pat_opAritmeticos.matcher(elemento);
        if (mat_opAritmeticos.matches()) {
            expresion = "operador_aritmetico";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_opAsignacion = Pattern.compile("(=|\\*=|/=|%=|\\+=|-=|<<=|>>=|&=|\\^=|\\|=)");
        Matcher mat_opAsignacion = pat_opAsignacion.matcher(elemento);
        if (mat_opAsignacion.matches()) {
            expresion = "operador_asignacion";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        } 
        Pattern pat_puntuacion = Pattern.compile("([.|,|;|:])");
        Matcher mat_puntuacion = pat_puntuacion.matcher(elemento);
        if (mat_puntuacion.matches()) {
            expresion = "simbolo_puntuacion";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_opComparacion = Pattern.compile("(==|!=|>|<|<=|>=)");
        Matcher mat_opComparacion = pat_opComparacion.matcher(elemento);
        if (mat_opComparacion.matches()) {
            expresion = "simbolo_comparacion";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        } 

        Pattern pat_opLogicos = Pattern.compile("(&&|\\|\\||!)");
        Matcher mat_opLogicos = pat_opLogicos.matcher(elemento);
        if (mat_opLogicos.matches()) {
            expresion = "simbolo_logico";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        } 
        Pattern pat_agrupan = Pattern.compile("(\\(|\\)|\\{|\\}|\\[|\\]|[\'])");
        Matcher mat_agrupan = pat_agrupan.matcher(elemento);
        if (mat_agrupan.matches()) {
            expresion = "simbolo_agrupacion";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_agrupamiento_de_texto = Pattern.compile("\"");
        Matcher mat_agrupamiento_de_texto = pat_agrupamiento_de_texto.matcher(elemento);
        if (mat_agrupamiento_de_texto.matches()) {
            expresion = "agrupacion_texto";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_simbolos = Pattern.compile("(#|<<|>>)");
        Matcher mat_simbolos = pat_simbolos.matcher(elemento);
        if (mat_simbolos.matches()) {
            expresion = "simbolo";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_identificador = Pattern.compile("([a-zA-Z]([a-zA-Z]|[0-9]|_)*)");
        Matcher mat_identificador = pat_identificador.matcher(elemento);
        if (mat_identificador.matches()) {
            expresion = "identificador";
            mapeo(elemento, expresion);
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_constante = Pattern.compile("(-?[0-9]+)");
        Matcher mat_constante = pat_constante.matcher(elemento);
        if (mat_constante.matches()) {
            expresion = "numero_constante";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        Pattern pat_real = Pattern.compile("(-?[0-9]+.[0-9]+)");
        Matcher mat_real = pat_real.matcher(elemento);
        if (mat_real.matches()) {
            expresion = "numero_real";
            int val = mapeo(elemento, expresion);
            return new Token(expresion, val);
        }
        return null;
    }

    /**
     * Revisa si el elemento mandado matchea con una expresion regular
     *
     * @param elemento string que puede ser simbolo o palabra que esta separada
     * en un token
     * @param expresion string que dice en que tipo de expresion regular matcheo
     * el token
     */
    public int mapeo(String elemento, String expresion) {

        //LIBRERIAS
        map.put("iostream", 10);
        map.put("fsting", 11);
        map.put("iosfw", 12);
        map.put("list", 13);
        map.put("cmath", 14);
        map.put("memory", 15);
        map.put("numeric", 16);
        map.put("cstdio", 17);
        map.put("cstring", 18);
        map.put("cstdlib", 19);

        //RESERVADAS
        map.put("auto", 50);
        map.put("const", 51);
        map.put("double", 52);
        map.put("float", 53);
        map.put("int", 54);
        map.put("short", 55);
        map.put("struct", 56);
        map.put("unsigned", 57);
        map.put("break", 58);
        map.put("continue", 59);
        map.put("else", 60);
        map.put("for", 61);
        map.put("long", 62);
        map.put("signed", 63);
        map.put("switch", 64);
        map.put("void", 65);
        map.put("case", 66);
        map.put("default", 67);
        map.put("enum", 68);
        map.put("goto", 69);
        map.put("register", 70);
        map.put("sizeof", 71);
        map.put("typedef", 72);
        map.put("volatile", 73);
        map.put("char", 74);
        map.put("string", 75);
        map.put("do", 76);
        map.put("extern", 77);
        map.put("if", 78);
        map.put("return", 79);
        map.put("static", 80);
        map.put("union", 81);
        map.put("while", 82);
        map.put("true", 83);
        map.put("false", 84);
        map.put("using", 85);
        map.put("namespace", 86);
        map.put("std", 87);
        map.put("cout", 88);
        map.put("cin", 89);
        map.put("main", 90);
        map.put("include", 91);

        //ARITMETICOS
        map.put("+", 200);
        map.put("-", 201);
        map.put("*", 202);
        map.put("/", 203);
        map.put("%", 204);

        //ASIGNACION
        map.put("=", 250);
        map.put("*=", 251);
        map.put("/=", 252);
        map.put("%=", 253);
        map.put("+=", 254);
        map.put("-=", 255);
        map.put("<<=", 256);
        map.put(">>=", 257);
        map.put("&=", 258);
        map.put("^=", 259);
        map.put("|=", 260);

        //PUNTUACION
        map.put(".", 300);
        map.put(",", 301);
        map.put(";", 302);
        map.put(":", 303);

        //COMPARACION
        map.put("==", 350);
        map.put("!=", 351);
        map.put(">", 352);
        map.put("<", 353);
        map.put("<=", 354);
        map.put(">=", 355);

        //LOGICOS
        map.put("&&", 400);
        map.put("||", 401);
        map.put("!", 402);

        //AGRUPADORES
        map.put("(", 450);
        map.put(")", 451);
        map.put("{", 452);
        map.put("}", 453);
        map.put("[", 454);
        map.put("]", 455);
        map.put("\"", 456);
        map.put("'", 457);

        //SIMBOLOS
        map.put("\\", 500);
        map.put("#", 501);
        map.put("<<", 502);
        map.put(">>", 503);

        if ((expresion.equals("identificador")) && !(map.containsValue(elemento))) {
            map.put(elemento, i++);
            i = i + 1;
        }
        if ((expresion.equals("numero_constante")) && !(map.containsValue(elemento))) {
            map.put(elemento, i++);
            i = i + 1;
        }

        int val = map.get(elemento);

        return val;

    }

    public Map<String, Integer> getMapa() {

        return map;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Object i : sentencias) {
            for (Token j : (ArrayList<Token>) i) {
                aux += j.toString() + " || ";
            }
            aux += "\n";
        }
        return aux;
    }

    public String toStringTextoCompleto() {
        String aux = "";
        for (Token j : init2()) {
            aux += j.toString() + " || ";
        }
        aux += "\n";

        return aux;
    }
     public ArrayList getSentencias(){
         return sentencias;
     }
    
    
}
