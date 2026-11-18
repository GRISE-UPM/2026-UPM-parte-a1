package es.upm.grise.cruiseControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CruiseControlTest {

    private CruiseControl cruiseControl;

    @BeforeEach
    void setUp() {
        // Se usa un mock de Speedometer para cumplir con la dependencia
        Speedometer speedometerMock = Mockito.mock(Speedometer.class);
        cruiseControl = new CruiseControl(speedometerMock);
    }

    @Test
    void initialStateIsDisabledAndWithoutValues() {
        assertFalse(cruiseControl.isEnabled());
        assertNull(cruiseControl.getSpeedSet());
        assertNull(cruiseControl.getSpeedLimit());
    }

    @Test
    void setSpeedSetEnablesCruiseControlAndStoresValue() {
        cruiseControl.setSpeedSet(100);

        assertTrue(cruiseControl.isEnabled());
        assertEquals(100, cruiseControl.getSpeedSet());
    }

    @Test
    void setSpeedSetAboveLimitIsRejectedAndDisablesCruiseControl() {
        cruiseControl.setSpeedLimit(100);
        cruiseControl.setSpeedSet(120);

        assertFalse(cruiseControl.isEnabled());
        assertNull(cruiseControl.getSpeedSet());
        assertEquals(100, cruiseControl.getSpeedLimit());
    }

    @Test
    void setSpeedLimitStoresValue() {
        cruiseControl.setSpeedLimit(120);

        assertEquals(120, cruiseControl.getSpeedLimit());
    }

    @Test
    void loweringLimitBelowSpeedSetDisablesCruiseControl() {
        cruiseControl.setSpeedSet(100);
        cruiseControl.setSpeedLimit(90);

        assertFalse(cruiseControl.isEnabled());
        assertNull(cruiseControl.getSpeedSet());
        assertEquals(90, cruiseControl.getSpeedLimit());
    }

    @Test
    void disableResetsEnabledAndSpeedSet() {
        cruiseControl.setSpeedSet(100);

        cruiseControl.disable();

        assertFalse(cruiseControl.isEnabled());
        assertNull(cruiseControl.getSpeedSet());
    }

    @Test
    void negativeSpeedSetThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cruiseControl.setSpeedSet(-1));
    }

    @Test
    void negativeSpeedLimitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cruiseControl.setSpeedLimit(-1));
    }

    @Test
    void smokeTest() {
        // Prueba de humo para verificar que el objeto se crea correctamente
        assertFalse(cruiseControl.isEnabled());
    }
}