package es.upm.grise.cruiseControl;

/**
 * IncorrectSpeedLimitException
 */
public class IncorrectSpeedLimitException extends RuntimeException {
    public IncorrectSpeedLimitException() {
        super("El limite de velocidad debe ser mayor que 0");
    }

}
