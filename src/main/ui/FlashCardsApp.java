package ui;

import model.*;

import java.util.*;
import java.util.List;


//Console based version of application
public class FlashCardsApp {
    private Decks myDecks;
    private static final String[] MENU_COMMANDS = {"Decks", "Learn More", "Quit"};
    private static final String[] DECKS_COMMANDS = {"View Deck", "Main Menu", "New Deck", "Remove Deck", "Rename Deck"};
    private static final String[] DECK_COMMANDS = {"Decks", "Add Card", "Remove Card", "Edit Card", "Practice"};

    //EFFECTS: Initializes and starts the application
    public FlashCardsApp() {
        start();
    }

    //EFFECTS: Begins to run the application
    private void start() {
        myDecks = new Decks("myDecks");
        System.out.println("Welcome to JavaCards! Start by typing a command below!");
        menu();
    }

    //EFFECTS: controls the main menu (go to deck or help)
    @SuppressWarnings({"checkstyle:SuppressWarnings", "checkstyle:MethodLength"})
    private void menu() {
        Scanner menuScanner = new Scanner(System.in);
        String input = "";
        List<String> validCommands = new ArrayList<>(Arrays.asList(MENU_COMMANDS));
        while (!validCommands.contains(input)) {
            System.out.print("Welcome to the Main Menu! Type \"Help\" for commands.");
            input = menuScanner.nextLine();
            switch (input) {
                case "Help":
                    printCommands(MENU_COMMANDS);
                    break;
                case "Decks":
                    decksPage();
                    break;
                case "Learn More":
                    printReadMe();
                    break;
                case "Quit":
                    System.out.println("Bye Bye!");
                    break;
                default:
                    System.out.println("Invalid command. Type \"Help\" for available commands.");
                    break;
            }
        }
        menuScanner.close();
    }

    //EFFECTS:controls the decks menu and handles decks options
    @SuppressWarnings({"checkstyle:SuppressWarnings", "checkstyle:MethodLength"})
    private void decksPage() {
        Scanner decksScanner = new Scanner(System.in);
        String input;
        List<String> validCommands = new ArrayList<>(Arrays.asList(DECKS_COMMANDS));
        do {
            System.out.println("Welcome to your Decks Page. Type \"Help\" for commands.");
            input = decksScanner.nextLine();
            switch (input) {
                case "Help":
                    printCommands(DECKS_COMMANDS);
                    break;
                case "Main Menu":
                    menu();
                    break;
                case "New Deck":
                    createNewDeck();
                    break;
                case "View Deck":
                case "Remove Deck":
                case "Rename Deck":
                    if (myDecks.getDeckList().size() == 0) {
                        System.out.println("You have no decks! Type \" New Deck \" first!");
                        decksPage();
                    } else if (input.equals("View Deck")) {
                        deckOptions("View Deck");
                    } else if (input.equals("Remove Deck")) {
                        deckOptions("Remove Deck");
                    } else {
                        deckOptions("Rename Deck");
                    }
                    break;
                default:
                    System.out.println("Invalid command. Type \"Help\" for available commands.");
                    break;
            }
        } while (!validCommands.contains(input));
        decksScanner.close();
    }

    //MODIFIES: this
    //EFFECTS: removes, renames, or views a deck, returning back to decks page.
    private void deckOptions(String action) {
        Scanner deckOptionsScanner = new Scanner(System.in);
        System.out.println("Type the number corresponding to each deck to " + action);
        for (int i = 0; i < myDecks.getDeckList().size(); i++) {
            System.out.println(i + 1 + ". " + myDecks.getDeckList().get(i).getCardDeckName());
        }
        int input = deckOptionsScanner.nextInt();
        if (input < 1 || input > myDecks.getDeckList().size()) {
            System.out.println("This deck doesn't exist! Try Again.");
            deckOptions(action);
        }
        if (input > 0 && input <= myDecks.getDeckList().size()) {
            if (action.equals("View Deck")) {
                deckPage(myDecks.fetchDeckFromDecks(input - 1));
            } else if (action.equals("Remove Deck")) {
                myDecks.removeDeckFromDecks(input - 1);
                System.out.println("Deck no. " + input + " has been removed successfully!");
                decksPage();
            } else {
                renameDeck(input - 1);
            }
        }
        deckOptionsScanner.close();
    }

    //MODIFIES: this
    //EFFECTS: renames the deck
    public void renameDeck(Integer index) {
        Scanner nameScanner = new Scanner(System.in);
        System.out.println("Enter new deck name: ");
        String newName = nameScanner.nextLine();
        CardDeck changeDeckName = myDecks.fetchDeckFromDecks(index);
        changeDeckName.renameCardDeck(newName);
        System.out.println("Renamed!");
        decksPage();
        nameScanner.close();
    }


    //MODIFIES: this
    //EFFECTS: creates a new deck
    private void createNewDeck() {
        Scanner newDeckScanner = new Scanner(System.in);
        System.out.println("What will the Name of your new deck be? ");
        String title = newDeckScanner.nextLine();
        myDecks.addDeckToDecks(new CardDeck(title));
        System.out.println("Your deck \"" + title + "\" has been added to your decks!");
        decksPage();
    }

    //MODIFIES: this, current
    //EFFECTS: controls the inside menu of a deck
    @SuppressWarnings({"checkstyle:SuppressWarnings", "checkstyle:MethodLength"})
    private void deckPage(CardDeck currentDeck) {
        Scanner deckPageScanner = new Scanner(System.in);
        String input;
        System.out.println(currentDeck.getCardDeckName() + " has been opened! What do you want to do?");
        List<String> validCommands = new ArrayList<>(Arrays.asList(DECK_COMMANDS));
        do {
            System.out.println("Welcome to your " + currentDeck.getCardDeckName() + ". Type \"Help\" for commands.");
            input = deckPageScanner.nextLine();
            switch (input) {
                case "Help":
                    printCommands(DECK_COMMANDS);
                    break;
                case "Add Card":
                    createNewCard(currentDeck);
                    break;
                case "Edit Card":
                case "Remove Card":
                    if (currentDeck.getSizeOfDeck() == 0) {
                        System.out.println("You have no cards! Type \"Add Card\" first!");
                        deckPage(currentDeck);
                    } else if (input.equals("Edit Card")) {
                        flashCardOptions("edit", currentDeck);
                    } else {
                        flashCardOptions("remove", currentDeck);
                    }
                    break;
                case "Practice":
                    practice(currentDeck);
                    break;
                case "Edit Name":
                    System.out.println("Input new deck name: ");
                    String newTitle = deckPageScanner.nextLine();
                    currentDeck.renameCardDeck(newTitle);
                    System.out.println("done!");
                    deckPage(currentDeck);
                case "Decks":
                    decksPage();
                default:
                    System.out.println("Invalid command. Type \"Help\" for available commands.");
                    break;
            }
        } while (!validCommands.contains(input));
    }

    //MODIFIES: this, current
    //EFFECTS: removes or edits a flashcard and checks for empty deck
    private void flashCardOptions(String action, CardDeck current) {
        Scanner flashCardOptionsScanner = new Scanner(System.in);
        System.out.println("Type the number corresponding to each flashcard to " + action);
        for (int i = 0; i < current.getSizeOfDeck(); i++) {
            System.out.println(i + 1 + ". " + current.getFlashCardList().get(i).getFrontSide());
        }
        int input = flashCardOptionsScanner.nextInt();
        if (input < 1 || input > current.getSizeOfDeck()) {
            System.out.println("This card doesn't exist! Try Again.");
            flashCardOptions(action, current);
        }
        if (input > 0 && input < current.getSizeOfDeck()) {
            if (action.equals("edit")) {
                flashCardEdit(input - 1, current);
            } else {
                current.removeCard(input - 1);
                System.out.println("card no. " + input + " has been removed successfully!");
                deckPage(current);
            }
        }
    }

    //MODIFIES: this, current
    //EFFECTS: uses user input to create a new card and add to deck.
    private void createNewCard(CardDeck current) {
        Scanner flashCardPageScanner = new Scanner(System.in);
        System.out.println("Set the front side of the card: ");
        String front = flashCardPageScanner.nextLine();
        System.out.println("Set the back side of the card:");
        String back = flashCardPageScanner.nextLine();
        current.addCard(new FlashCard(front, back));
        System.out.println("all done! Add another one? (Y/N) ");
        String choice = flashCardPageScanner.nextLine();
        if (choice.equals("Y")) {
            createNewCard(current);
        } else {
            deckPage(current);
        }
    }

    //MODIFIES: this, current
    //EFFECTS: Edits an existing flashcard page user input
    private void flashCardEdit(Integer index, CardDeck current) {
        FlashCard currentCard = current.getFlashCard(index);
        Scanner flashCardPageScanner = new Scanner(System.in);
        System.out.println("What will the new front-side be?");
        String newFront = flashCardPageScanner.nextLine();
        currentCard.setFrontSide(newFront);
        System.out.println("What will the new back-side be?");
        String newBack = flashCardPageScanner.nextLine();
        currentCard.setBackSide(newBack);
        System.out.println("all done! Bringing you back to the deck page!");
        deckPage(current);
    }

    //EFFECTS: starts the practice feature for a card deck
    private void practice(CardDeck deck) {
        Scanner practiceScanner = new Scanner(System.in);
        System.out.println("Type \"Back\" to show the backside of the card first, or \"Front\" for the front.");
        String choice = practiceScanner.nextLine();
        boolean setCard = choice.equals("Front");
        deck.shuffleDeck();
        for (FlashCard flashCard : deck.getFlashCardList()) {
            flashCard.setIsFrontShowing(setCard);
            if (flashCard.getIsFrontShowing()) {  //front side first
                System.out.println("Front Side: " + flashCard.getFrontSide());
                System.out.println("Type anything to flip");
                practiceScanner.nextLine();
                System.out.println("BackSide: " + flashCard.getBackSide());
                System.out.println("________________________________________________");
            } else {                              //back side first
                System.out.println("Back Side: " + flashCard.getBackSide());
                System.out.println("Type anything to flip");
                practiceScanner.nextLine();
                System.out.println("Front Side: " + flashCard.getFrontSide());
                System.out.println("________________________________________________");
            }
        }
        System.out.println("Good job studying! Lets go back to the decks page.");
        decksPage();
    }

    //EFFECTS: returns a display of all the available commands at current page into the console
    private void printCommands(String[] commands) {
        System.out.println("Available commands are:");
        for (String command : commands) {
            System.out.println("- " + command);
        }
    }

    //EFFECTS: prints short description of the application, returns back to menu page after
    private void printReadMe() {
        System.out.println("JavaCards is a Java based FlashCard app.");
        System.out.println("It can do everything a standard flashCard app can do,");
        System.out.println("so just mess around! Good luck studying!");
        menu();
    }
}