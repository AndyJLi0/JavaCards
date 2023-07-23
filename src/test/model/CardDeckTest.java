package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test class for the CardDeck Class in the Model Package
public class CardDeckTest {
    CardDeck testDeck;
    FlashCard testFlashCard;
    @BeforeEach
    void setup() {
        testDeck = new CardDeck("Deck 1");
        testFlashCard = new FlashCard("f1", "b1", true);
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

        FlashCard testFlashCard2 = new FlashCard("f2", "b2", true);
        FlashCard testFlashCard3 = new FlashCard("f3", "b3", true);
        testDeck.addCard(testFlashCard2);
        testDeck.addCard(testFlashCard3);
        assertEquals(3, testDeck.getSizeOfDeck());
        assertEquals(testFlashCard2, testDeck.getFlashCard(1));
        assertEquals(testFlashCard3, testDeck.getFlashCard(2));
    }
    @Test
    void removeCardTest(){
        FlashCard testFlashCard2 = new FlashCard("f2", "b2", true);
        FlashCard testFlashCard3 = new FlashCard("f3", "b3", true);
        testDeck.addCard(testFlashCard);
        testDeck.addCard(testFlashCard2);
        testDeck.addCard(testFlashCard3);
        testDeck.removeCard(2);
        testDeck.removeCard(0);
        assertEquals(1,testDeck.getSizeOfDeck());
        assertEquals(testFlashCard2, testDeck.getFlashCard(0));

        testDeck.addCard(testFlashCard);
        testDeck.addCard(testFlashCard3);
        testDeck.removeCard(2);
        assertEquals(2,testDeck.getSizeOfDeck());
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

    @Test
    void shuffleAndResetTest() {
        FlashCard f1 = testFlashCard;
        FlashCard f2 = new FlashCard("front2","back2", true);
        FlashCard f3 = new FlashCard("front3","back3", true);
        FlashCard f4 = new FlashCard("front4","back4", true);
        FlashCard f5 = new FlashCard("front5","back5", true);
        FlashCard f6 = new FlashCard("front6","back6", true);
        FlashCard f7 = new FlashCard("front7","back7", true);
        testDeck.addCard(f1);
        testDeck.addCard(f2);
        testDeck.addCard(f3);
        testDeck.addCard(f4);
        testDeck.addCard(f5);
        testDeck.addCard(f6);
        testDeck.addCard(f7);
        ArrayList<FlashCard> originalOrder = new ArrayList<>(testDeck.getFlashCardList());
        assertEquals(7, testDeck.getSizeOfDeck());
        testDeck.shuffleDeck();
        assertEquals(7, testDeck.getSizeOfDeck());
        assertNotEquals(testDeck.getFlashCardList(),originalOrder);
        testDeck.shuffleDeck();
        testDeck.shuffleDeck();
        testDeck.shuffleDeck();
        testDeck.shuffleDeck();
        assertNotEquals(testDeck.getFlashCardList(),originalOrder);

        testDeck.resetDeck(originalOrder);
        assertEquals(testDeck.getFlashCardList(),originalOrder);
    }
}
