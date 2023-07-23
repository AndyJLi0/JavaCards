package persistence;

import model.Decks;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.*;


//Represents a writer that writes JSON representation of Decks to file
public class JsonWriter {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS:
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer, throws exception if file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of decks to file
    public void write(Decks decks) {
        JSONObject json = decks.toJson();
        saveToFile(json.toString(INDENT));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
