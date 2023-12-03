// Trevor England, Dan Peveto
public enum Choices { // enum because the tools for rock paper scissors doesn't change
    ROCK, PAPER, SCISSORS;

    public static Choices computerChoice(){ // randomly generate computers choice
        return values()[(int) (Math.random() * values().length)];
    }
}
