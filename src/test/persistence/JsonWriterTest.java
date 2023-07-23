package persistence;

import model.CardDeck;
import model.Decks;
import model.FlashCard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Decks decks = new Decks("My Decks");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDecks() {
        try {
            Decks decks = new Decks("myDecks");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDecks.json");
            writer.open();
            writer.write(decks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDecks.json");
            decks = reader.read();
            assertEquals("myDecks", decks.getDecksTitle());
            assertEquals(0, decks.getDeckList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDecks() {
        try {
            Decks decks = new Decks("myDecks");
            CardDeck deck1 = new CardDeck("deck1");
            CardDeck deck2 = new CardDeck("deck2");
            FlashCard card1 = new FlashCard("front1", "back1", true);
            FlashCard card2 = new FlashCard("front2", "back2", true);
            FlashCard card3 = new FlashCard("front3", "back3", true);

            decks.addDeckToDecks(deck1);
            decks.addDeckToDecks(deck2);
            deck1.addCard(card1);
            deck1.addCard(card2);
            deck2.addCard(card3);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDecks.json");
            writer.open();
            writer.write(decks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDecks.json");
            decks = reader.read();
            assertEquals("myDecks", decks.getDecksTitle());
            List<CardDeck> cardDeck = decks.getDeckList();
            assertEquals(2, cardDeck.size());
            assertEquals("deck1", cardDeck.get(0).getCardDeckName());
            assertEquals("deck2", cardDeck.get(1).getCardDeckName());

            CardDeck expectedDeck1 = cardDeck.get(0);
            CardDeck expectedDeck2 = cardDeck.get(1);

            assertEquals("front1", expectedDeck1.getFlashCard(0).getFrontSide());
            assertEquals("front2", expectedDeck1.getFlashCard(1).getFrontSide());
            assertEquals("front3", expectedDeck2.getFlashCard(0).getFrontSide());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
