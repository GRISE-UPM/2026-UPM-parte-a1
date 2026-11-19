package es.upm.grise.cruiseControl;

/**
 * CannotSetSpeedLimitException
 */
public class CannotSetSpeedLimitException extends RuntimeException {
    public CannotSetSpeedLimitException() {
        super("No se puede establecer el límite despues de haber establecido SpeedSet");
    }

}
