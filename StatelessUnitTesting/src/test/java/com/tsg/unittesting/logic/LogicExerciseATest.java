package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static com.tsg.unittesting.logic.LogicExerciseA.friendlyGreeting;
import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseATest {

    /* Test Plan:
    * Known Person:
    * friendlyGreeting("Gallant", true) -> "Hello, Gallant!"
    * friendlyGreeting(null, true) -> "..."
    *
    * Unknown Person:
    * friendlyGreeting("Gallant", false) -> "hi"
    * friendlyGreeting(null, false) -> "..."
    *
     */

    @Test
    public void greetKnownNamed(){
        // Arrange
        String name = "Gallant";
        boolean known = true;

        // Act
        String greeting = friendlyGreeting(name, known);

        // Assert
        assertEquals(greeting, "Hello, " + name + "!" );
    }

    @Test
    public void greetKnownUnnamed(){
        String name = null;
        boolean known = true;
        String greeting = friendlyGreeting(name, known);
        assertEquals(greeting, "..." );
    }

    @Test
    public void greetUnknownNamed(){
        String name = "Gallant";
        boolean known = false;
        String greeting = friendlyGreeting(name, known);
        assertEquals(greeting, "hi" );
    }
    @Test
    public void greetUnknownUnnamed(){
        String name = null;
        boolean known = false;
        String greeting = friendlyGreeting(name, known);
        assertEquals(greeting, "..." );
    }

}