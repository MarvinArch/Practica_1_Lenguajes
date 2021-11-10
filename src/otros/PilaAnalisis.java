/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.util.Stack;
import lenguajesformales1.PantallaInicial;

/**
 *
 * @author alpha
 */
public class PilaAnalisis {
     private Stack pila = new Stack();
    int valor=50;
    char actual;
    public PilaAnalisis() {
    }
    
    public int analisisPila(String token , int posicion){
        valor=50;
        if (pila.empty()==true && token.equalsIgnoreCase("identificador")) {
            pila.push('A');
            pila.push('i');
            actual=(char) pila.pop(); 
        }
        while (valor>3 ) {   
            if (actual=='i' || actual =='N' && (token.equalsIgnoreCase("identificador") || token.equalsIgnoreCase("numero")) || token.equalsIgnoreCase("Decimal")) {
                actual=(char) pila.pop(); 
                valor=3;
            }else if (actual==token.charAt(0)) {
                actual=(char) pila.pop();
                valor=3;
            }else if(actual=='F' && token.equalsIgnoreCase("FIN") && pila.empty()==true){
                valor=0;
            }else{
                String insertar= Reglas(actual, token);
                
                if (insertar.equals("Error")==true) {
                    valor=-1;
                } else {
                    char[] arreglo= insertar.toCharArray();
                    for (int i = 0; i < arreglo.length; i++) {
                        pila.push(arreglo[i]);
                    }
                    actual=(char) pila.pop();
                }
            }
        }
        return valor;
    }
    
    public String Reglas(char actual, String token){
        String estado="Error";
        if (actual=='s' && token.equalsIgnoreCase("identificador")) {
            estado="Ai";
        }else if (actual=='B' || actual=='O' && (token.equalsIgnoreCase("identificador") || token.equalsIgnoreCase("Numero")) || token.equalsIgnoreCase("Decimal")) {
            estado="PN";
        }else if (actual=='B' || actual=='O' || actual=='P' && (token.equalsIgnoreCase("+") || token.equalsIgnoreCase("*")) || token.equalsIgnoreCase("(")) {
            if (token.equalsIgnoreCase("+")) {
                estado="O+";
            }else if (token.equalsIgnoreCase("*")) {
                estado="O*";
            }else if (token.equalsIgnoreCase("(")) {
                estado=")O(";
            }
        }else if (actual=='A' && token.equalsIgnoreCase("=")) {
            estado="FO=";
        }else if (actual=='B' && token.equalsIgnoreCase("=")) {
            estado="O=";
        }else if (actual=='F' && token.equalsIgnoreCase("FIN")) {
            estado="FIN";
        }else if (actual=='N' && (token.equalsIgnoreCase("identificador") || token.equalsIgnoreCase("numero")) || token.equalsIgnoreCase("Decimal")) {
            estado="N";
        }else if (actual=='P' && (token.equalsIgnoreCase(")") || token.equalsIgnoreCase("FIN"))) {
            estado="";
        }else{
            estado="Error";
        }
        return estado;
    }

    public Stack getPila() {
        return pila;
    }
}
