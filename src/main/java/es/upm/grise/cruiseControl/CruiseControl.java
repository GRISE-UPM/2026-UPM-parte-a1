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
		if(speedSet <= 0) throw new IncorrectSpeedSetException();
		if(this.speedLimit != null && this.speedSet > this.speedLimit) throw new SpeedSetAboveSpeedLimitException();
		this.speedSet = new Integer(speedSet);
		this.enabled = true;
	}
	
	/* 
	 * Method to code/test
	 */

	public void setSpeedLimit(int speedLimit) {
		if(speedLimit <= 0) throw new IncorrectSpeedLimitException();
		if(speedSet != null) throw new CannotSetSpeedLimitException();
		this.speedLimit = new Integer(speedLimit); return;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		this.speedSet = null;
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
