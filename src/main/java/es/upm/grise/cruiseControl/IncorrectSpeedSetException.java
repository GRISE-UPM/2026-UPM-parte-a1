package es.upm.grise.cruiseControl;

/**
 * IncorrectSpeedSetException
 */
public class IncorrectSpeedSetException extends RuntimeException {
    public IncorrectSpeedSetException() {
        super("La velocidad establecida debe ser mayor que 0");
    }
}
