
import java.io.*;
import java.util.Scanner;

public class Game {
    private QuadraticProbingHashTable<WordInfo> Hash_game = new QuadraticProbingHashTable<>();
    int game_score;
    int finds;
    public Game(){
        game_score = 0;
        finds = 0;
    } ;

    public void playGame(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            //reading in our file, given there is a word on each line
            while (scanner.hasNextLine()) {
                //creating an instance of wordinfo for the thing we just read
                WordInfo temp = new WordInfo(scanner.nextLine());
                //seeing if temp is in the has table, if so creating an instance of wordinfo for it
                WordInfo w = Hash_game.find(temp);
                //counting how many finds we have had to do on the hash table, basicall
                //basically every time we try to insert something
                finds++;
                //if we actually found temp in the hash table
                if (w != null) {
                    //we found this one more time!
                    w.count++;
                    //adding the w count onto temp
                    temp.count += w.count;

                }
                //inserting temp into our hash table
                Hash_game.insert(temp);
                //finding temp's score, with the new updated number of times which we have seen it before
                temp.findScore();
                //adding temp score to the game score
                game_score += temp.score;

            }
            //closing our file
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //printing out our pretty stats
        System.out.println("Game Score: " + game_score) ;
        System.out.println("Number of probes: " + Hash_game.probecount);
        System.out.println("Number of finds: " + finds);
        System.out.println("Number of things in hash table:  " + Hash_game.size());
        System.out.println("Capacity of Hash table:  " + Hash_game.capacity());
        System.out.println("First 20 non-null entries");
        Hash_game.findNth();

    }

    public String toString() {
        int LIMIT = 20;
        return name+ "\n"+ Hash_game.toString(LIMIT);
    }


    private String name;

    public static void main( String [ ] args ) {
        //doing playgame for all of the input files which we were given
            Game g0 = new Game( );
        System.out.println("Game 0");
            g0.playGame("game0.txt" );
            Game g1 = new Game( );
        System.out.println("Game 1");
            g1.playGame("game1.txt" );
            Game g2 = new Game( );
        System.out.println("Game 2");
            g2.playGame("game2.txt" );
            Game g4 = new Game();
        System.out.println("Game 3");
            g4.playGame("game3.txt");
            Game g3 = new Game( );
        System.out.println("Game 4");
            g3.playGame("game4.txt" );
        }


}

