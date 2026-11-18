package es.upm.grise.cruiseControl;

import org.junit.jupiter.api.Test;

class CruiseControlTest {

	@Test
	public void smokeTest() {
		Speedometer speed = new Speedometer();
		int speedSet = speed.getSpeedSet(15);
		int speedLimit = speed.getSpeedLimit(200);
	}

}
