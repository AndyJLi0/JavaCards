package model;

// Class for representing a single flash card
public class FlashCard {
    private String frontSide;
    private String backSide;
    private Boolean isFrontShowing;

    //REQUIRES: front and back side are non-empty strings,
    //EFFECTS: creates a flashcard with a front and back side
    public FlashCard(String initFront, String initBack) {
        this.frontSide = initFront;
        this.backSide = initBack;
        isFrontShowing = true;
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets the front side of the flashcard
    public void setFrontSide(String frontText) {
        this.frontSide = frontText;
    }

    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets the back side of the flashcard
    public void setBackSide(String backText) {
        this.backSide = backText;
    }

    //MODIFIES: this
    //EFFECTS: flips the flash card
    public void setIsFrontShowing(boolean choice) {
        isFrontShowing = choice;
    }

    //EFFECTS: returns the front side of the flashcard
    public String getFrontSide() {
        return frontSide;
    }

    //EFFECTS: returns the back side of the flashcard
    public String getBackSide() {
        return backSide;
    }

    //EFFECTS: returns true if card is front side is showing
    public Boolean getIsFrontShowing() {
        return isFrontShowing;
    }
}
