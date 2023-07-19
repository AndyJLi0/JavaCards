package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Test class for the FlashCard Class in the Model Package
public class FlashCardTest {
    FlashCard testCard;
    @BeforeEach
    void setup() {
        testCard = new FlashCard("Where is UBC?", "Vancouver");

    }
    @Test
    void flashCardTest() {
        assertEquals("Where is UBC?", testCard.getFrontSide());
        assertEquals("Vancouver",testCard.getBackSide());
        assertTrue(testCard.getIsFrontShowing());
    }
    @Test
    void setFrontSideTest() {
        testCard.setFrontSide("Where is Calgary?");
        assertEquals("Where is Calgary?", testCard.getFrontSide());
        assertEquals("Vancouver",testCard.getBackSide());
        assertTrue(testCard.getIsFrontShowing());
    }
    @Test
    void setBackSideTest() {
        testCard.setBackSide("British Columbia");
        assertEquals("British Columbia", testCard.getBackSide());
        assertEquals("Where is UBC?",testCard.getFrontSide());
        assertTrue(testCard.getIsFrontShowing());
    }
    @Test
    void setFrontShowingTest() {
        testCard.setIsFrontShowing(false);
        assertFalse(testCard.getIsFrontShowing());
        testCard.setIsFrontShowing(true);
        assertTrue(testCard.getIsFrontShowing());
        testCard.setIsFrontShowing(false);
        testCard.setIsFrontShowing(true);
        assertTrue(testCard.getIsFrontShowing());
    }
    @Test
    void setSidesTest() {
        testCard.setFrontSide("Is my name Andy?");
        testCard.setBackSide("Yes");
        assertEquals("Is my name Andy?", testCard.getFrontSide());
        assertEquals("Yes" , testCard.getBackSide());
    }
}
