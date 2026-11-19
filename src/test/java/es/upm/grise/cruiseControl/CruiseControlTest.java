package es.upm.grise.cruiseControl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CruiseControlTest {
	
	@Test 
	void testInitialValue() {
		CruiseControl cruise = new CruiseControl(null);
		assertNull(cruise.getSpeedLimit());
		assertNull(cruise.getSpeedSet());
		assertFalse(cruise.isEnabled());
	}

	@Test 
	void testSetSpeedSet() {
		CruiseControl cruise = new CruiseControl(null);
		cruise.setSpeedSet(100);
		assertEquals(100, cruise.getSpeedSet());
	}

	@Test 
	void testSpeedSetAboveSpeedLimit() {
		CruiseControl cruise = new CruiseControl(null);
		cruise.setSpeedLimit(120);
		assertThrows(SpeedSetAboveLimitException.class, () -> cruise.setSpeedSet(150));
	}

	@Test 
	void testSetSpeedLimit() {
		CruiseControl cruise = new CruiseControl(null);
		cruise.setSpeedLimit(120);
		assertEquals(120, cruise.getSpeedLimit());
	}

	@Test 
	void testDisable() {
		CruiseControl cruise = new CruiseControl(null);
		cruise.setSpeedSet(100);
		cruise.disable();
		assertFalse(cruise.isEnabled());
		assertNull(cruise.getSpeedSet());
	}
}