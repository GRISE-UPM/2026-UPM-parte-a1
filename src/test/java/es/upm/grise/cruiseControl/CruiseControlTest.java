package es.upm.grise.cruiseControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {

	@Test
    public void correctSpeedSetTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertEquals(null, cruiseControl.getSpeedSet());
		assertEquals(null, cruiseControl.getSpeedLimit());
		assertEquals(false, cruiseControl.isEnabled());
	}
    
	@Test
	public void correctSpeedSetTest1() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedSet(1);
		assertEquals(1, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}
    @Test
	public void correctSpeedSetTest2() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(1);
		cruiseControl.setSpeedSet(1);
		assertEquals(1, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
		assertEquals(1, cruiseControl.getSpeedLimit());

	}

	@Test
	public void wrongSpeedSetTest1() {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertThrowsExactly(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-1));
	}
	@Test
	public void wrongSpeedSetTest2() {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertThrowsExactly(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
	}
	@Test
	public void wrongSpeedSetTest3() throws IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(100);
		assertThrowsExactly(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(120));
	}

	@Test
	public void correctSetSpeedLimitTest1() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(1);
		assertEquals(1, cruiseControl.getSpeedLimit());
	}
    @Test
	public void correctSetSpeedLimitTest2() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(10);
		assertEquals(10, cruiseControl.getSpeedLimit());
		cruiseControl.setSpeedSet(1);
		cruiseControl.disable();
		cruiseControl.setSpeedLimit(1);
		assertEquals(1, cruiseControl.getSpeedLimit());
	}
    @Test
	public void wrongSetSpeedLimitTest1() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedSet(1);
		assertThrowsExactly(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(1));
	}
    @Test
	public void wrongSetSpeedLimitTest2() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertThrowsExactly(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-1));
	}
    
	@Test	
	public void DisableTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(50);
		cruiseControl.setSpeedSet(40);
		assertTrue(cruiseControl.isEnabled());
		cruiseControl.disable();
		assertEquals(false, cruiseControl.isEnabled());
		assertEquals(null, cruiseControl.getSpeedSet());
	}



}
