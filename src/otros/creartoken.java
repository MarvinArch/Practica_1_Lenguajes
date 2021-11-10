/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author alpha
 */
public class creartoken {
    
    private ArrayList<String> devolucion= new ArrayList<>();
    private int contado=0;
    private String leer;
    private String separar;

    public creartoken(String linea, String separador) {
        this.leer=linea;
        this.separar=separador;
        CrearTokens();
    }
    
    public void CrearTokens(){
        char[] arreglo= leer.toCharArray();
        char sep= ' ';
        int inicio=0;
        String palabra="";
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i]=='\t') {
                devolucion.add("tabulac");
            }
            if (arreglo[i]=='/' && arreglo[i+1]=='/') {
                break;
            }
            
            if (arreglo[i]==')' || arreglo[i]=='>' || arreglo[i]=='}'|| arreglo[i]==';' || arreglo[i]==':' || arreglo[i]==',' || arreglo[i]=='/' 
                    || arreglo[i]=='*' || arreglo[i]=='+' ||  arreglo[i]=='(' || arreglo[i]=='<' || arreglo[i]=='{' || arreglo[i]=='=' || arreglo[i]=='\'') {
                char[] conjunto;
                if (i<(arreglo.length)) {
                    conjunto= new char[i-inicio];
                }else{
                    conjunto = new char[0];
                }
                for (int j = 0; j < conjunto.length; j++) {
                    conjunto[j]=arreglo[inicio+j];
                }
                palabra=String.valueOf(conjunto);
                devolucion.add(palabra.trim());
                devolucion.add(String.valueOf(arreglo[i]));
                inicio=i+1;
            }else if (arreglo[i]==sep ||  arreglo[i]=='\n' ||  arreglo[i]=='(' || arreglo[i]=='<' || arreglo[i]=='{' || i==(arreglo.length-1)) {
                char[] conjunto= new char[i-inicio+1];
                for (int j = 0; j < conjunto.length; j++) {
                    conjunto[j]=arreglo[inicio+j];
                }
                inicio=i+1;
                palabra=String.valueOf(conjunto);
                devolucion.add(palabra.trim());
                if (arreglo[i]=='\n') {
                    devolucion.add("newLine");
                }
                if (Character.isSpaceChar(arreglo[i])) {
                    devolucion.add("Epsilon");
                }
            }
        }
    }
    
    public int tamañoArreglo(){
        return devolucion.size();
    }
    
    public String token(){
        String dev = devolucion.get(contado);
        contado++;
        return dev;
    }

    

}
