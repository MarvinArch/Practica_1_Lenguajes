/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.util.ArrayList;

/**
 *
 * @author alpha
 */
public class AnalizarToken {
    private int linea;
    private int columna=1;
    private ArrayList<infoToken> resultados= new ArrayList<>();
    
    public AnalizarToken() {
        resultados.clear();
    }
    
    public void LimpiarArreglo(){
        resultados.clear();
    }
    
    public void Analizartoken(String token, int linean){
        if (linean!=linea) {
            columna=1;
            linea=linean;
        }
        char [] analizar = token.toCharArray();
        int Estado=0;
        boolean isString=false;
        int valor=-1;
        int inicia=0;
        for (int i = 0; i < analizar.length; i++) {
            valor=TipoCaracter(analizar[i]);
            if (Estado>3 && Estado<7 && valor<100) {
                Error(linea, columna, 3, inicia, i+1, analizar);
                    inicia=i+1;
                    Estado=0;
                    break;
            }
            switch (Estado){
                case 0: //Inicio
                    if (valor==2) {
                        Estado=1;
                    }else if (valor==1) {
                        Estado=2;
                    }else if (valor==3 && i==0 ) {
                        Error(linea, columna, 2, inicia, i+2, analizar);
                        inicia=i+1;
                    }else if (valor==4) {
                        Estado=4;
                    }else if (valor==5) {
                        Estado=5;
                    }else if (valor==6) {
                        Estado=6;
                    }else if (valor==100) {
                        Estado=0;
                    }
                    break;
                case 1: // Letra
                    if (valor==2 || valor==100) {
                        Estado=1;
                    }else if (valor==1) {
                        Estado=1;
                    }else if (valor==3) {
                        Error(linea, columna, 2, inicia, i+1, analizar);
                        inicia=i+1;
                        Estado=0;
                        isString=false;
                    }else{
                        Error(linea, columna, 4, inicia, i+1, analizar);
                        inicia=i+1;
                        Estado=0;
                        isString=false;
                    }
                    break;
                case 2://numero
                    if (valor==1 || valor==100) {
                        Estado=2;
                    }else if (valor==2){
                        Estado=0;
                        Error(linea, columna, 1, inicia, i+1, analizar);
                        inicia=i+1;
                        isString=false;
                    }else if (valor==3 && (analizar.length>(i+2))) {
                        Estado=3;
                    }else{
                        Error(linea, columna, 1, inicia, i+1, analizar);
                        inicia=i+1;
                        Estado=0;
                        isString=false;
                    }
                    break;
                case 3: //decimal
                    if (valor==1 || valor==100) {
                        Estado=3;
                    }else{
                        Error(linea, columna, 1, inicia, i+1, analizar);
                        inicia=i+1;
                        Estado=0;
                        isString=false;
                    }
                    break;
               
            }
            columna++;
        }
        aceptado(linea, columna, inicia, analizar, isString, Estado);
        
    }

    public ArrayList<infoToken> getResultados() {
        return resultados;
    }
    
    public int TipoCaracter(char a){
        int valor=0;
        if (Character.isDigit(a)) {
                valor=1;
            }else if (Character.isLetter(a)) {
                valor=2;
            }else if ('.'==a) {
                valor=3;
            }else if (Character.isSpaceChar(a)) {
                valor=100;
            }else {
                valor=OtrosCaracteres(a);
            }
        return  valor;
    }
    
    public int OtrosCaracteres(char a){
        int resultado=-1;
        char[] puntuacion={'.', ',',';',':'};
        char[] aritmeticos={'+', '-', '*', '/', '%'};
        char[] agrupacion={'(', ')', '[', ']', '{', '}'};
        for (int i = 0; i < agrupacion.length; i++) {
            if (i<4 && a==puntuacion[i]) {
                resultado=4;
            }else if (i<5 && aritmeticos[i]==a) {
                resultado=5;
            }else if (agrupacion[i]==a) {
                resultado=6;
            }
        }
        return resultado;
    }
    
    public void Error(int linea, int columna, int error, int inicia, int fin, char[] b ){
        String Descripcion="";
        char[] token2= new char[(fin)-inicia];
        for (int i = inicia; i < (inicia+token2.length); i++) {
            token2[i-inicia]=b[i];
        }
        if (error==1) {
            Descripcion="Esperaba otro digito";
        }else if (error==2) {
            Descripcion="Antes del punto debe haber un digito o despues del punto un espacio";
        }else if (error==3) {
            Descripcion="Esperaba que el siguiente caracter fuera un espacio";
        }else if (error==4) {
            Descripcion="Esperaba un digito o una letra";
        }
        resultados.add(new infoToken("Error", linea, columna, Descripcion, String.valueOf(token2)));
    }
    
    public void aceptado(int linea, int columna, int inicia, char[] b, boolean isString, int estado){
        char[] token2= new char[(b.length)-inicia];
        String tipo="";
        for (int i = inicia; i < b.length; i++) {
            token2[i-inicia]=b[i];
        }
        if (estado==1) {
            tipo="Identificador";
        }else if (estado==2 ) {
            tipo="Numero";
        }else if (estado==3 ) {
            tipo="Decimal";
        }else if (estado==4) {
            tipo="Puntuacion";
        }else if (estado==5) {
            tipo="Operado";
        }else if (estado==6) {
            tipo="Agrupacion";
        }
        resultados.add(new infoToken(tipo, linea, columna-token2.length, "Aceptado", String.valueOf(token2).trim()));
        if (inicia!=0 && estado!=0) {
            resultados.get(resultados.size()-1).setRecuperado(true);
        }
        if (estado==0) {
            resultados.remove(resultados.size()-1);
        }
    }
        
}
