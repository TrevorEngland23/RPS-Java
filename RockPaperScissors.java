// Trevor England, Dan Peveto

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * RockPaperScissors
 */
public class RockPaperScissors extends JFrame {
    String player1 = "You"; // user is player1
    String player2 = "Computer"; // computer is player 2
    int humanScore = 0; // both scores start at 0
    int computerScore = 0;
    JButton rock = new JButton("Rock"); // create rock paper scissors buttons
    JButton paper = new JButton("Paper");
    JButton scissors = new JButton("Scissors");
    JMenu menu; // create a menu for the JFrame
    JPanel mainContents = new JPanel(); // add JPanel
    JLabel label = new JLabel("Welcome! Best of 5 wins the game..."); // Assuming the user knows how to play rock paper scissors, this lets the user know the rules set to win the match
    JMenu scores = new JMenu("Your Score: " + humanScore + " | Computer Score: " + computerScore); // a way for the user to keep track of current round scores
    JPanel game = new JPanel(); // game JPanel
    JButton[] buttons = {rock, paper, scissors}; // put the JButtons in an array for later so that we can add functionality to all buttons once. Technically is slower, but considering there are 3 items in the array, it won't really matter
    
    public RockPaperScissors() {
        super("Rock-Paper-Scissors"); 
        
        GridLayout gridLayout = new GridLayout(3, 1); // 3 rows, 1 column

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Game Options"); // houses the restart game option
        JMenuItem restart = new JMenuItem("Restart Game"); // restart game option for clearing score and starting a new round
        restart.addActionListener(e -> restartGame()); // when clicked, fires restartGame function
        menu.add(restart);
        menuBar.add(menu);
        menuBar.add(scores);
        setJMenuBar(menuBar);

        // Wanted to set my buttons with colored text and background color, but wasn't working on my mac... couldn't get it working with the code posted in moodle either so just commented it out.

        // rock.setForeground(Color.WHITE);
        // rock.setBackground(Color.BLACK);
        // paper.setForeground(Color.WHITE);
        // paper.setBackground(Color.BLACK);
        // scissors.setForeground(Color.WHITE);
        // scissors.setBackground(Color.BLACK);

        // loop through the buttons list to attach the action event
        for(int i = 0; i < buttons.length; i++){ 
            buttons[i].addActionListener(e -> buttonClicked(e));
            game.add(buttons[i]);
        }

        // add main contents to the JFrame, and allow visibility
        add(mainContents);
        mainContents.setLayout(new BorderLayout());
        mainContents.add(label, BorderLayout.NORTH);
        game.setLayout(gridLayout);
        mainContents.add(game, BorderLayout.CENTER);

        setSize(500,500);
        setLocation(500, 0);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void restartGame() { // clears the score, allows new match to play
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(true); // enables buttons to be clicked again
        }
        label.setText("Welcome! Best of 5 wins the game..."); // sets text back to default specified earlier in program
        humanScore = 0; // resets score
        computerScore = 0;
        updateScores(); // calls the updateScores method to keep track of this rounds scores
    }

    private void updateScores(){ // keep track of updating scores
        scores.setText("Your Score: " + humanScore + "| Computer Score: " + computerScore);
        String winner;

        if(computerScore == 3 || humanScore == 3){ // if one of these is true
            winner = humanScore == 3 ? player1 : player2; // the winner is set to whichever condition was true
            label.setText("Game over! " + winner + " won!"); // let the user know who won
            for(int i = 0; i < buttons.length; i++){ // disable the buttons so the user can't keep playing
                buttons[i].setEnabled(false);
            }
        }
    }


    private void buttonClicked(ActionEvent event){ // action event 
        JButton button = (JButton)event.getSource(); // the button that was clicked
        Choices userChoice = Choices.valueOf(button.getText().toUpperCase()); // looks in the enum choices to get the value of which button was pressed. has to converto the button text to uppercase, since the enum values are uppercase
        Choices computerChoice = Choices.computerChoice(); // generates a random choice from the enums 

        int result = compareChoices(userChoice, computerChoice); // calls compareChoices to handle the logic of comparing

        if (result == 0) { // if the choices are the same
            label.setText("It's a tie! You both chose " + userChoice + ".");
        } else if (result == 1) { // if the choices were not the same
            label.setText("You win! Your choice: " + userChoice + " | Computer Choice: " + computerChoice + ".");
            humanScore++;
        } else { // -1 is the only other thing that will be returned
            label.setText("You lose! Your choice: " + userChoice + " | Computer Choice: " + computerChoice + ".");
            computerScore++;
        }

        updateScores(); 
    }

    private int compareChoices(Choices userChoice, Choices computerChoice){
        if (userChoice == computerChoice){ // if choices are the same
            return 0; // Tie
            // game logic... state all the ways a user can win
        } else if ((userChoice == Choices.ROCK && computerChoice == Choices.SCISSORS)||
                 (userChoice == Choices.PAPER && computerChoice == Choices.ROCK) ||
                 (userChoice == Choices.SCISSORS && computerChoice == Choices.PAPER)) {
            return 1; // User wins
        } else { // if the user did not win and it was not a tie.. then the user loses
            return -1; // Computer wins
        }
    }
}
    
