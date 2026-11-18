package es.upm.grise.cruiseControl;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

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
	
	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		if (speedSet <= 0) throw new IncorrectSpeedSetException();
		if (speedLimit != null && speedSet > speedLimit) throw new SpeedSetAboveSpeedLimitException();
		this.speedSet = speedSet;
		this.enabled = true;
	}
	
	public void setSpeedLimit(int speedLimit) throws IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		if (speedLimit <= 0) throw new IncorrectSpeedLimitException();
		if (this.speedSet != null) throw new CannotSetSpeedLimitException();
		this.speedLimit = speedLimit;
	}

	public void disable() {
		this.enabled = false;
        this.speedSet = null;
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
