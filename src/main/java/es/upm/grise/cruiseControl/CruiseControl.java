package es.upm.grise.cruiseControl;
import ./exceptions.*; 

public class CruiseControl {
	
	@SuppressWarnings("unused")
	private Speedometer speedometer;
	private Integer speedLimit;
	private Integer speedSet;
	private boolean enabled = false;
	

	
	public CruiseControl(Speedometer speedometer) {
	
	speedLimit = null;
	speedSet = null;
	
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {
	
		speedLimit = getSpeedLimit();
			
	if (speedSet < 0) {
		throw new IncorrectSpeedSetException("Velocidad no válida");
	} else if (speedLimit != null && speedSet > speedLimit) {
	    throw new SpeedSetAboveSpeedLimitException("Velocidad mayor que el límite de velocidad");
	  } else {
	    this.speedSet = speedSet;
	 }
	}
	
	/* 
	 *
	 */
	
	public void setSpeedLimit(int speedLimit) {
	
		speedSet = getSpeedSet();
	
		if(speedLimit <= 0){
			throw new IncorrectSpeedLimitException("La velocidad límite tiene que ser positiva");
		}else if(speedSet != null){
			throw new CannotSetSpeedLimitException("Ya existe un límite de velocidad que mantener");
		}else{
		 	this.speedLimit = speedLimit;
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
