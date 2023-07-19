package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Test class for the CardDeck Class in the Model Package
public class CardDeckTest {
    CardDeck testDeck;
    FlashCard testFlashCard;
    @BeforeEach
    void setup() {
        testDeck = new CardDeck("Deck 1");
        testFlashCard = new FlashCard("f1", "b1");
    }
    @Test
    void cardDeckTest() {
        assertEquals("Deck 1",testDeck.getCardDeckName());
        assertEquals(0, testDeck.getSizeOfDeck());
    }
    @Test
    void AddCardTest() {
        testDeck.addCard(testFlashCard);

        assertEquals(1, testDeck.getSizeOfDeck());
        assertEquals(testFlashCard, testDeck.getFlashCard(0));

        FlashCard testFlashCard2 = new FlashCard("f2", "b2");
        FlashCard testFlashCard3 = new FlashCard("f3", "b3");
        testDeck.addCard(testFlashCard2);
        testDeck.addCard(testFlashCard3);
        assertEquals(3, testDeck.getSizeOfDeck());
        assertEquals(testFlashCard2, testDeck.getFlashCard(1));
        assertEquals(testFlashCard3, testDeck.getFlashCard(2));
    }
    @Test
    void removeCardTest(){
        FlashCard testFlashCard2 = new FlashCard("f2", "b2");
        FlashCard testFlashCard3 = new FlashCard("f3", "b3");
        testDeck.addCard(testFlashCard);
        testDeck.addCard(testFlashCard2);
        testDeck.addCard(testFlashCard3);
        testDeck.removeCard(2);
        testDeck.removeCard(0);
        assertEquals(1,testDeck.getSizeOfDeck());
        assertEquals(testFlashCard2, testDeck.getFlashCard(0));

        testDeck.addCard(testFlashCard);
        testDeck.addCard(testFlashCard3);
        testDeck.removeCard(3);
        assertEquals(3,testDeck.getSizeOfDeck());
    }
    @Test
    void renameCardDeckTest(){
        testDeck.renameCardDeck("Deck 1 Renamed");
        assertEquals("Deck 1 Renamed", testDeck.getCardDeckName());
    }
    @Test
    void getFlashCardListTest() {
        testDeck.addCard(testFlashCard);
        ArrayList<FlashCard> expected = new ArrayList<>();
        expected.add(testFlashCard);
        assertEquals(expected, testDeck.getFlashCardList());
    }
}
