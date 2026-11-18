package es.upm.grise.cruiseControl;

import org.junit.jupiter.api.Test;

class CruiseControlTest {

	@Test
	public void smokeTest() {
	
	Speedometer p1 = new Speedometer();
	
	CruiseControlTest piloto = new CruiseControlTest(p1);
	
	assert.equals(piloto.speedLimit, null);
	assert.equals(piloto.speedSet, null);
	
	piloto1.setSpeedSet(5);
	
	assert.equals(p1.speedSet, 5);
	
	
	Speedometer p2 = new Speedometer();
	
	CruiseControlTest piloto2 = new CruiseControlTest(p2);
	
	piloto2.setSpeedLimit(3);
	
	assert.equals(p2.speedLimit, 3);
	
	
	}

}
