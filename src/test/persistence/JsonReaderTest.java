package persistence;

import model.CardDeck;
import model.Decks;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistent.json");
        try {
            Decks testDeck = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDecks() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDecks.json");
        try {
            Decks testDecks = reader.read();
            assertEquals("myDecks", testDecks.getDecksTitle());
            assertEquals(0, testDecks.getDeckList().size());
        } catch (IOException e) {
            fail("Should have been able to read file");
        }
    }

    @Test
    void testReaderGeneralDecks() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDecks.json");
        try {
            Decks testDecks = reader.read();
            assertEquals("myDecks", testDecks.getDecksTitle());
            List<CardDeck> cardDeck = testDecks.getDeckList();
            assertEquals(2, cardDeck.size());
            assertEquals("deck1", cardDeck.get(0).getCardDeckName());
            assertEquals("deck2", cardDeck.get(1).getCardDeckName());

            CardDeck expectedDeck1 = cardDeck.get(0);
            CardDeck expectedDeck2 = cardDeck.get(1);

            assertEquals(2, expectedDeck1.getSizeOfDeck());
            assertEquals(2, expectedDeck2.getSizeOfDeck());

            assertEquals("front1", expectedDeck1.getFlashCard(0).getFrontSide());
            assertEquals("front2", expectedDeck1.getFlashCard(1).getFrontSide());
            assertEquals("front3", expectedDeck2.getFlashCard(0).getFrontSide());
            assertEquals("front4", expectedDeck2.getFlashCard(1).getFrontSide());


        } catch (IOException e) {
            fail("Should have been able to read file");
        }
    }


}
