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
		this.speedLimit = null;
		this.speedSet = null;
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {
		if (speedSet<=0) {
			throw new IncorrectSpeedSetException("Speed tiene que ser > 0");
		}else if (speedLimit!=null && speedSet>speedLimit) {
			throw new SpeedSetAvobeSpeedLimitException("Speed tiene que ser <= speedLimit");
		}
		this.speedSet = speedSet;
		this.enabled = true;

	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) {
		if (speedLimit<=0) {
			throw new IncorrectSpeedLimitException("Speed tiene que ser > 0");
		}else if (speedSet!=null) {
			throw new CannotSetSpeedLimitException("Ya hay Speed Set");
		}
		this.speedLimit = speedLimit;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		this.enabled = false;
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
