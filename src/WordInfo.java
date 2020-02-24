
public class WordInfo {
    int score;
    int letters;
    int length;
    int bonus;
    int hash_value;
    String input_word;
    int count;

    //overriding the equals method so we can use our generic hash table just to compare on the words
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordInfo ) {
            if (this.input_word.equals(((WordInfo) obj).input_word)) {
                return true;
            }

            else return false;
        }
        return false;
    }

    public WordInfo() {
        score = 0;
        letters= 0;
        length = 0;
        bonus= 0;
        count = 0;
    }

    public WordInfo( String word) {
        input_word = word;
        score = 0;
        letters= findLetters();
        length = findLength();
        bonus= findBonus();

    }

    //making a pretty toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ");
        sb.append(input_word);
        sb.append("\n");
        sb.append("Timed used before: ");
        sb.append(count);

        return sb.toString();
    }

    //helper method to find letter score for each letter
    public int findLetters() {
        int tempscore = 0;
        for (int i = 0; i < input_word.length(); i++) {
            tempscore += letter_Score(input_word.charAt(i));
        }

        return tempscore;
    }

    //helper method to find letter score
    private int letter_Score(char a) {
        int tempscore = 0;
        switch(a) {
            case 'a': case 'e': case 'i': case 'u':
            case 'n': case 'r': case 'o': case 's':
            case 'l': case 't':
                tempscore = 1; break;
            case 'm': case 'b': case 'c': case 'p': tempscore = 3; break;
            case 'q': case 'z': tempscore = 10; break;
            case 'y': case 'f': case 'v': case 'w':
            case 'h':
                tempscore = 4; break;
            case 'j': case 'x': tempscore = 8; break;
            case 'g': case 'd': tempscore = 2; break;
            case 'k': tempscore = 5; break;
        }

        return tempscore;
    }

    private int findLength() {
        int tempscore = 0;
        switch (input_word.length()) {
            case 1: case 2: tempscore = 0; break;
            case 3: tempscore = 1; break;
            case 4: tempscore = 2; break;
            case 5: tempscore = 3; break;
            case 6: tempscore = 4; break;
            case 7: tempscore = 5; break;
            default: tempscore = 6; break;
        }

        return tempscore;
    }

    //helper method to find bonus value
    private int findBonus() {
        int temp_bonus = 0;
        switch (count) {
            case 0: temp_bonus = 5; break;
            case 1: case 2: case 3: case 4: case 5: temp_bonus = 4; break;
            case 6: case 7: case 8: case 9: case 10: temp_bonus = 3; break;
            case 11: case 12: case 13: case 14: case 15: temp_bonus = 2; break;
            default: temp_bonus = 1; break;
        }

        return temp_bonus;

    }

    //helper method to find overall score while updating bonus
    public void findScore() {
        bonus = this.findBonus();
        this.score = bonus*length*letters;

    }

    //overiding the hash code function so it only hashes on the input word
    public int hashCode() {
        int hashVal = 5;
        for (int i = 0; i < this.input_word.length(); i++) {
            hashVal = hashVal<< 1 ^ this.input_word.charAt(i) ^ hashVal;
        }

        return hashVal;
    }
}
