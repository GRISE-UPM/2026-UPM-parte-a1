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
		this.speedLimit = null;
		this.speedSet = null;
		this.enabled = false;
	}
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {
		if (speedSet <= 0) {
			throw new IncorrectSpeedSetException();
		}
		if (speedLimit != null && speedSet > speedLimit) {
			throw new SpeedSetAboveLimitException();
		}
		this.speedSet = speedSet;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) {
		if (speedLimit <= 0) {
			throw new IncorrectSpeedLimitException();
		}
		this.speedLimit = speedLimit;
		if (speedSet != null && speedSet > speedLimit) {
			throw new CannotSetSpeedLimitException();
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
