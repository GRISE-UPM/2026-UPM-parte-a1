package es.upm.grise.cruiseControl;

public class CruiseControl {

    @SuppressWarnings("unused")
    private Speedometer speedometer;

    private Integer speedLimit;
    private Integer speedSet;
    private boolean enabled = false;

    /*
     * Constructor
     */
    public CruiseControl(Speedometer speedometer) {
        this.speedometer = speedometer;
    }

    /*
     * Method to code/test
     */
    public void setSpeedSet(int speedSet) {
        if (speedSet < 0) {
            throw new IllegalArgumentException("speedSet cannot be negative");
        }

        // Si hay un límite y la velocidad fijada lo supera, se desactiva
        if (speedLimit != null && speedSet > speedLimit) {
            disable();
            return;
        }

        this.speedSet = speedSet;
        this.enabled = true;
    }

    /*
     * Method to code/test
     */
    public void setSpeedLimit(int speedLimit) {
        if (speedLimit < 0) {
            throw new IllegalArgumentException("speedLimit cannot be negative");
        }

        this.speedLimit = speedLimit;

        // Si la velocidad fijada actual supera el nuevo límite, se desactiva
        if (speedSet != null && speedSet > speedLimit) {
            disable();
        }
    }

    /*
     * Method to code/test
     */
    public void disable() {
        this.enabled = false;
        this.speedSet = null;
    }

    /*
     * Others getters and setters
     */
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public Integer getSpeedSet() {
        return speedSet;
    }
}