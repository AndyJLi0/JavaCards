package persistence;

import org.json.JSONObject;

//an interface that handles the writing to file methods for the model package
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}