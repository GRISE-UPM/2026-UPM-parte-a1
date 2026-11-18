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
		this.speedometer=speedometer;
		speedLimit=null;
		speedSet=null;
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {
		if(speedSet <= 0) {
			throw new IncorrectSpeedSetException();
		}
		if(speedLimit != null && speedSet > speedLimit) {
			throw new SpeedAboveLimitException();
		}
		this.speedSet = speedSet;
		enabled = true;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) {
		if(speedLimit <= 0) {
			throw new IncorrectSpeedLimitException();
		}
		if(speedSet != null) {
			throw new CannotSetSpeedLimitException();
		}
		this.speedLimit = speedLimit;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		enabled = false;
		speedSet = null;
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
