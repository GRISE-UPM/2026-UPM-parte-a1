package es.upm.grise.cruiseControl;
import es.upm.grise.SpeedSetAboveSpeedLimitException;
import es.upm.grise.IncorrectSpeedSetException;
import es.upm.grise.IncorrectSpeedLimitException;
import es.upm.grise.CannotSetSpeedLimitException;

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
		this.speedSet = null;
		this.speedLimit = null;
	}
	
	/*
	 * Method to code/test
	 */
	
	public void setSpeedSet(int speedSet) {

		speedSet = getSpeedSet();

		if(speedSet >= 0){
			if(speedSet > speedLimit){
				throw new SpeedSetAboveSpeedLimitException ("Velocidad por encima del limite");
			}
		}
		else{
			throw new IncorrectSpeedSetException ("Velocidad menor o igual a 0");
		}
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void setSpeedLimit(int speedLimit) {

		speedLimit = getSpeedLimit();

		if(speedLimit <= 0){
			throw new IncorrectSpeedLimitException ("Limite menor o igual a 0");
		}
		if(speedLimit != null){
			throw new CannotSetSpeedLimitException ("Limite ya existente");
		}
	}
	
	/* 
	 * Method to code/test
	 */
	
	public void disable() {
		this.enabled = true;
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
