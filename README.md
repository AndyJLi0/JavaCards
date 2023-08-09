# JavaCards: A Flashcard Application

>**JavaCards** is a java based desktop app designed to make and use flashcards. There's no limit
to how many card decks you can make and how many cards in a deck itself. There's a practice feature shuffles the deck,
and you can flip cards as many times as you like. My app is intended to be used by university or college students that 
want an organized, and self-tailored flashcard app, but because of its simplicity, **anyone can use it**!

### Why I Made This Project
>I made this project because I wanted to make a free, easy to use, and simple flashcard app. Many 
of today's popular flashcard apps are either online and require paid subscriptions, or have 
complicated interfaces and are tough for new users to use. Learning and memorizing doesn't have to
be hard, and I hope my app helps with it.

### Instructions for Grader
> - You can generate the first required action related to adding Xs to a Y by adding a deck to your decks by pressing the 
`New Deck` button in the JavaCard Decks page.
> - You can generate the second required action related to adding Xs to a Y by renaming or removing a deck from your decks
by pressing the `Rename Deck` or `Remove Deck` button in the JavaCard Decks page respectively.
> - You can locate my visual component by pressing any `New/Create/Rename/Edit/Remove` button, and a pop-up with a
flashCard Icon will display. The Main menu page also has a display of a flashcard with the java logo inside as well.
> - You can save the state of my application by pressing the top right exit button anytime the application is running. 
You will be prompted to save.
> - You can reload the state of my application by selecting `yes` on the pop-up that displays the moment you run the 
application.
> - **Additional Note**: A card needs to be selected for the practice button to work, which prevent practicing with
an empty deck. `right arrow` shows the next card, `left arrow`  shows the previous card, and `space` flips the card.  

### User Stories
> - As a user, I want to be able to add card decks to my set of decks
> - As a user, I want to be able to add flashcards to a card deck
> - As a user, I want to be able to edit card decks
> - As a user, I want to be able to edit flashcards
> - As a user, I want to be able to remove card decks from my set of decks
> - As a user, I want to be able to remove flashcards from my card deck
> - As a user, I want to be able to practice and review through all cards in a deck
> - As a user, I want to have the option to save my decks and flashcards when I quit if I so choose
> - As a user, I want to have the option to load my decks and flashcards when I open the app again

### Phase 4: Task 2

>Tue Aug 08 18:54:38 PDT 2023
Added Deck1 to decks  
Tue Aug 08 18:54:40 PDT 2023  
Added Deck2 to decks  
Tue Aug 08 18:54:49 PDT 2023  
Added FlashCard to Deck1  
Tue Aug 08 18:54:53 PDT 2023  
Added FlashCard to Deck1  
Tue Aug 08 18:55:01 PDT 2023  
Renamed Flashcard back from NewBack1 to Back1  
Tue Aug 08 18:55:01 PDT 2023  
Renamed Flashcard front from NewFront1 to Front1  
Tue Aug 08 18:55:05 PDT 2023  
Removed Deck2 from decks

### Phase 4: Task 3
>If I had more time to work on this project before submission, I would first aim to improve code robustness. 
I have no exceptions thrown and many of the times I want to prevent something from happening, 
I explicitly check using multiple if statements. I could instead add exceptions to both my UI and model package
to improve code clarity, robustness, and scalability.  

>I would also introduce design principles such as the iterator design approach if I had more time. 
My Decks and CardDeck classes both have 2 fields. Instead of calling getter methods for each list when I want to iterate
through the collection, I could have instead made both classes implement an iterator<[respective class]> which would 
result in less coupling and prevent potential bugs.  

>Lastly, by looking at my UML diagram, I would also try to refactor my code so that there would be less coupling with my 
Model package and the PracticeGUI. I currently have 1 field for the cardDeck, 1 for the shuffled flashcard, and 1 for
the un-shuffled flashcard list. I could potentially remove the 2 flashcard lists altogether since I can access the 
flashcards through the card deck, and have a method to un-shuffle the card deck back to its original state.


### Acknowledgements
> - Data Persistence in this program is based off UBC's CPSC 210 edX phase 2 Workroom Example
> - All Icons made by FreePik from www.flaticon.com
> - ToggleButton design from DJ-Raven java-swing-switch-button repo
