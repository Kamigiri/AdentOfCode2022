import java.lang.constant.Constable;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Result, Integer> resultPointMapper = new HashMap<>() {{
        put(Result.WON, 6);
        put(Result.DRAW, 3);
        put(Result.LOST, 0);
    }};

    private Map<Action, Integer> actionPointMapper = new HashMap<>() {{
        put(Action.PAPER, 2);
        put(Action.ROCK, 1);
        put(Action.SCISSOR, 3);
    }};

    public Match(Character actionOpponent, Character actionMyself, boolean isPartTwo) {
        this.actionOpponent = this.convertAction(actionOpponent);
        this.actionMyself = isPartTwo ? this.convertAction(actionMyself, this.actionOpponent) : this.convertAction(actionMyself);
    }


    private Action convertAction(Character action) {
        return switch (action) {
            case 'A', 'X' -> Action.ROCK;
            case 'B', 'Y' -> Action.PAPER;
            case 'C', 'Z' -> Action.SCISSOR;
            default -> null;
        };

    }

    private Action convertAction(Character action, Action enemyAction) {
        Action result = null;
        switch (enemyAction) {
            case ROCK -> {
                if (action.equals('X')) {
                    result = Action.SCISSOR;
                } else if (action.equals('Y')) {
                    result = Action.ROCK;
                } else {
                    result = Action.PAPER;
                }
            }
            case PAPER -> {
                if (action.equals('X')) {
                    result = Action.ROCK;
                } else if (action.equals('Y')) {
                    result = Action.PAPER;
                } else {
                    result = Action.SCISSOR;
                }
            }
            case SCISSOR -> {
                if (action.equals('X')) {
                    result = Action.PAPER;
                } else if (action.equals('Y')) {
                    result = Action.SCISSOR;
                } else {
                    result = Action.ROCK;
                }
            }
        }
        ;
        return result;
    }


    public Integer getPoints() {
        Integer points = 0;

        points += this.actionPointMapper.get(this.actionMyself);
        points += this.resultPointMapper.get(this.evaluateMatch());

        return points;
    }


    private Result evaluateMatch() {
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
            case PAPER -> {
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
        }
        ;
        return result;
    }


}
