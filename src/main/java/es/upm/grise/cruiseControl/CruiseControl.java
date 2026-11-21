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
	}
	

	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
        if(speedSet <= 0 ){
			throw new IncorrectSpeedSetException();
		}
		else if( speedLimit != null && speedSet > speedLimit){
			throw new SpeedSetAboveSpeedLimitException();
		}
		else
		{
		this.speedSet = speedSet;
		this.enabled = true;
		}
	}
	

	
	public void setSpeedLimit(int speedLimit) throws IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		if(speedLimit <= 0){
			throw new IncorrectSpeedLimitException();
		}
		else if(speedSet != null){
			throw new CannotSetSpeedLimitException();
		}
		else
		this.speedLimit = speedLimit;
	}
	
	
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
