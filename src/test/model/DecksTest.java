package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Test class for the Decks Class in the Model Package
public class DecksTest {
    Decks decksTest;
    CardDeck deck1;
    CardDeck deck2;
    CardDeck deck3;
    @BeforeEach
    void setup(){
        decksTest = new Decks("Test Name");
        deck1 = new CardDeck("A");
        deck2 = new CardDeck("B");
        deck3 = new CardDeck("B");
    }
    @Test
    void testConstructor() {
        assertEquals("Test Name", decksTest.getDecksTitle());
        assertEquals(0, decksTest.getDeckList().size());
    }
    @Test
    void addDeckToDecksTest() {
        decksTest.addDeckToDecks(deck1);
        decksTest.addDeckToDecks(deck2);
        decksTest.addDeckToDecks(deck3);
        assertEquals(3,decksTest.getDeckList().size());

    }
    @Test
    void removeDeckFromDecksTest() {
        decksTest.addDeckToDecks(deck1);
        decksTest.addDeckToDecks(deck2);
        decksTest.addDeckToDecks(deck3);
        decksTest.removeDeckFromDecks(2);
        decksTest.removeDeckFromDecks(0);
        assertEquals(1, decksTest.getDeckList().size());
        assertEquals(deck2, decksTest.getDeckList().get(0));

        decksTest.removeDeckFromDecks(deck2);
        assertEquals(0, decksTest.getDeckList().size());
    }

    @Test
    void fetchDeckFromDecksTest() {
        decksTest.addDeckToDecks(deck1);
        decksTest.addDeckToDecks(deck2);
        decksTest.addDeckToDecks(deck3);
        assertEquals(deck1, decksTest.fetchDeckFromDecks(0));
        assertEquals(deck2, decksTest.fetchDeckFromDecks(1));
        assertEquals(deck3, decksTest.fetchDeckFromDecks(2));
        assertEquals(deck1, decksTest.getCardDeckFromName("A"));
        assertEquals(deck2, decksTest.getCardDeckFromName("B"));
        assertNull(decksTest.getCardDeckFromName("C"));
    }

    @Test
    void getDeckNamesFromDecksTest() {
        decksTest.addDeckToDecks(deck1);
        decksTest.addDeckToDecks(deck2);
        decksTest.addDeckToDecks(deck3);
        assertEquals("A", decksTest.getDeckNamesFromDecks().get(0));
        assertEquals("B", decksTest.getDeckNamesFromDecks().get(1));
        assertEquals("B", decksTest.getDeckNamesFromDecks().get(2));


    }

}
