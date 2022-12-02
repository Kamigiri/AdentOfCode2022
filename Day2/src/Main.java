import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Match>> matchList = readMatches("input.txt");
        Integer[] maxScore = {0, 0};
        assert matchList != null;
        final int[] index = {0};

        matchList.forEach(matches -> {

            for (Match match : matches) {
                maxScore[index[0]] += match.getPoints();
            }
            index[0]++;
        });
        System.out.println(Arrays.toString(maxScore));
    }

    private static ArrayList<ArrayList<Match>> readMatches(String filename) {
        ArrayList<Match> matches = new ArrayList<Match>();
        ArrayList<Match> desiredMatches = new ArrayList<Match>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                matches.add(new Match(data.charAt(0), data.charAt(2), false));
                desiredMatches.add(new Match(data.charAt(0), data.charAt(2), true));
            }
            myReader.close();
            return new ArrayList<ArrayList<Match>>() {{
                add(matches);
                add(desiredMatches);
            }};
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}




