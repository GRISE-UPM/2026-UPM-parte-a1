package es.upm.grise.cruiseControl;

public class CruiseControl {
	
	@SuppressWarnings("unused")
	private Speedometer speedometer;
	private Integer speedLimit = null; 
	private Integer speedSet = null;
	private boolean enabled = false;
	
	/*
	 * Constructor
	 */
	
	public CruiseControl(Speedometer speedometer) {}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {

    if (speedSet <= 0) {
        throw new IncorrectSpeedSetException("La velocidad a mantener debe ser mayor que cero.");
    }

    if (this.speedLimit != null && speedSet > this.speedLimit) {
        throw new SpeedSetAboveSpeedLimitException("La velocidad a mantener no puede superar el límite de velocidad.");
    }
	
    this.speedSet = speedSet;
    this.enabled = true; //Control crucero
}
	
	public void setSpeedLimit(int speedLimit) {
	
	if (speedSet != null){
		 throw new CannotSetSpeedLimitException("Ya se ha establecido un límite de velocidad anteriormente.");
	}
	if (this.speedLimit <= 0) {
        throw new IncorrectSpeedSetException("La velocidad a mantener debe ser mayor que cero.");
    }
	speedSet = this.speedLimit;
	}
	
	public void disable() {
	
	CruiseControl=false;
	speedSet=null;
	}

	
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
