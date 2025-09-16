/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcion;

/**
 *
 * @author Usuario
 */
public class PersistenciaException extends Exception {

    public PersistenciaException() {
        super();
    }

    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public PersistenciaException(Throwable causa) {
        super(causa);
    }

}
