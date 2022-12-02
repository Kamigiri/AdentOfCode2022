import java.lang.constant.Constable;

public class Match {
    enum Action {
        ROCK,
        PAPER,
        SCISSOR
    }

    enum Result {
        WON,
        LOST,
        DRAW
    }

    private Action actionOpponent;
    private Action actionMyself;
    private Character actionMyselfPartTwo;
    private int points;

    public Match(Character actionOpponent, Character actionMyself) {
        this.actionOpponent = this.convertAction(actionOpponent);
        this.actionMyself = this.convertAction(actionMyself);
        this.actionMyselfPartTwo = actionMyself;
    }


    private Action convertAction(Character action) {
        return switch (action) {
            case 'A', 'X' -> Action.ROCK;
            case 'B', 'Y' -> Action.PAPER;
            case 'C', 'Z' -> Action.SCISSOR;
            default -> null;
        };

    }

    public Integer getPoints () {
        Integer points = 0;
         switch (this.actionMyself) {
            case ROCK -> points += 1;
            case PAPER -> points += 2;
            case SCISSOR -> points += 3;
        };
        switch (this.evaluateMatch()) {
            case DRAW -> points += 3;
            case WON -> points += 6;
            case LOST -> points += 0;
        };
        return points;
    }
    
    public Integer getPointsTwo() 
    {
        Integer points = 0;
        switch (this.actionMyselfPartTwo) {
            case 'X' -> points += 0;
            case 'Y' -> points += 3;
            case 'Z' -> points += 6;
        };
        switch (this.getAction()) {
            case ROCK -> points += 1;
            case PAPER -> points += 2;
            case SCISSOR -> points += 3;
        };
        return points;
    }

    private Result evaluateMatch(){
        Result result = null;
        switch (this.actionOpponent) {
            case ROCK -> {
                if (this.actionMyself.equals(Action.ROCK)) {
                    result = Result.DRAW;
                } else if (this.actionMyself.equals(Action.PAPER)) {
                    result = Result.WON;
                } else {
                    result = Result.LOST;
                }
            }
            case PAPER ->{
                if (this.actionMyself.equals(Action.ROCK)) {
                    result = Result.LOST;
                } else if (this.actionMyself.equals(Action.PAPER)) {
                    result = Result.DRAW;
                } else {
                    result = Result.WON;
                }
            }
            case SCISSOR -> {
                if (this.actionMyself.equals(Action.ROCK)) {
                    result = Result.WON;
                } else if (this.actionMyself.equals(Action.PAPER)) {
                    result = Result.LOST;
                } else {
                    result = Result.DRAW;
                }
            }
        };
        return result;
    }
    private Action getAction() {
        Action result = null;
        switch (this.actionOpponent) {
            case ROCK -> {
                if (this.actionMyselfPartTwo.equals('X')) {
                    result = Action.SCISSOR;
                } else if  (this.actionMyselfPartTwo.equals('Y')) {
                    result =Action.ROCK;
                } else {
                    result = Action.PAPER;
                }
            }
            case PAPER ->{
                if  (this.actionMyselfPartTwo.equals('X')) {
                    result = Action.ROCK;
                } else if  (this.actionMyselfPartTwo.equals('Y')) {
                    result = Action.PAPER;
                } else {
                    result = Action.SCISSOR;
                }
            }
            case SCISSOR -> {
                if  (this.actionMyselfPartTwo.equals('X')) {
                    result =Action.PAPER;
                } else if  (this.actionMyselfPartTwo.equals('Y')) {
                    result =Action.SCISSOR;
                } else {
                    result = Action.ROCK;
                }
            }
        };
        return result;
    }
}
