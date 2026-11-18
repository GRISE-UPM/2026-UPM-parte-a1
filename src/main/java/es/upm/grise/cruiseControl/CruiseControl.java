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
		speedLimit = null;
		speedSet = null;
		this.speedometer = speedometer;
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
			if(speedSet <= 0){
				throw new IncorrectSpeedSetException();
			}
			if(speedLimit != null && speedSet > speedLimit){
				throw new SpeedSetAboveSpeedLimitException();
			}
			this.speedSet = speedSet;
			enabled = true;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) throws CannotSetSpeedLimitException, IncorrectSpeedLimitException {
		if(enabled){
			throw new CannotSetSpeedLimitException();
		}else if(speedLimit <= 0){
			throw new IncorrectSpeedLimitException();
		}
		this.speedLimit = speedLimit;
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		if(enabled){
			enabled = false;
			speedSet = null;
		}
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


