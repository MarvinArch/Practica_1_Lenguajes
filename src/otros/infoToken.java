/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

/**
 *
 * @author alpha
 */
public class infoToken {
    private String identificador;
    private int linea;
    private int columna;
    private String Descripcion;
    private String token;
    private boolean recuperado =false;
    private int repeticiones=0;

    public infoToken(String identificador, int linea, int columna, String Descripcion, String token) {
        this.identificador = identificador;
        this.linea = linea;
        this.columna = columna;
        this.Descripcion = Descripcion;
        this.token = token;
    }

    
    public infoToken() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getLinea() {
        return linea+1;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public boolean isRecuperado() {
        return recuperado;
    }

    public void setRecuperado(boolean recuperado) {
        this.recuperado = recuperado;
    }
    
    public String mensajerecuperado(){
    String mensa="";
    mensa="Luego del error se pudo recupera el token "+token;
    return mensa;
    }
    
}
