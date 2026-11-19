package es.upm.grise.cruiseControl;

/**
 * SpeedSetAboveLimitException
 */
public class SpeedSetAboveLimitException extends RuntimeException {
    public SpeedSetAboveLimitException() {
        super("La velocidad establecida deseada es mayor que el límite de velocidad");
    }

}
