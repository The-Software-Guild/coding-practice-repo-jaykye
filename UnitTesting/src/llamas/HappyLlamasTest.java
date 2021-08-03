package llamas;

import org.junit.jupiter.api.Test;

import static llamas.HappyLlamas.areTheLlamasHappy;
import static org.junit.jupiter.api.Assertions.*;

class HappyLlamasTest {

    @Test
    void areTheLlamasHappyTest() {
        // ARRANGE - for simple methods, this means setting up the parameters
        boolean isNasaFabric = false;
        int numTrampolines = 10;

        // ACT - for simple methods, this generally means calling the method under test
        // and then capturing its return to assert on
        boolean result = areTheLlamasHappy(isNasaFabric, numTrampolines);

        // ASSERT - basically just a conditional that proves the result is what
        // you expect it to be, plus an extra message to display if it doesn't match.
        //
        // There are a wide variety of assert types, here we
        // just want to assert that it returned false. But we could have also used
        // assertEquals and passed in a false value.

        assertFalse( result , "10 Llamas w/ Normal Trampolines should be Unhappy!" );
    }
}