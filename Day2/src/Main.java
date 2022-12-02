import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        ArrayList<Match> matches = readMatches("input.txt");
        AtomicReference<Integer> maxScore = new AtomicReference<>(0);
        AtomicReference<Integer> maxScorePartTwo = new AtomicReference<>(0);
        assert matches != null;
        matches.forEach(match -> maxScore.updateAndGet(v -> v + match.getPoints()));
        matches.forEach(match -> maxScorePartTwo.updateAndGet(v -> v + match.getPointsTwo()));
        System.out.println(maxScore);
        System.out.println(maxScorePartTwo);
    }

    private static ArrayList<Match> readMatches(String filename) {
        ArrayList<Match> matches =new ArrayList<Match>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                matches.add(new Match(data.charAt(0), data.charAt(2)));
         }
            myReader.close();
            return matches;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  null;
    }
}




