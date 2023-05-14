import java.util.*;

public class GameRules {
    
    int rollsLeft;
    Player p;
    Opponent o;

    int ones;
    int twos;
    int threes;
    int fours;
    int fives;
    int sixes;
    int threeOfAKind;
    int fourOfAKind;
    int fullHouse;
    int smallStraight;
    int largeStraight;
    int yahtzee;
    int chance;
    int barbie;

    GameRules(){
        
    }

    public String[] getPossibleScores(int[] diceValues){

        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        int threeOfAKind = 0;
        int fourOfAKind = 0;
        int fullHouse = 0;
        int smallStraight = 0;
        int largeStraight = 0;
        int barbie = 0;
        int chance = 0;
        int yahtzee = 0;
        
        boolean pair = false;
        boolean triplet = false;
        int consecutive = 0;

        int[] valueInstances = {0, 0, 0, 0, 0, 0};

        //get instances of each dice vlaue
        for ( int i = 0; i < diceValues.length; i++){
            valueInstances[diceValues[i]-1] += 1;
        }

        //get singles values
        ones = (valueInstances[0] == 6) ? 5 :valueInstances[0];
        twos = (valueInstances[1] == 6) ? 5*2 :valueInstances[1]*2;
        threes = (valueInstances[2] == 6) ? 5*3 :valueInstances[2]*3;
        fours = (valueInstances[3] == 6) ? 5*4 :valueInstances[3]*4;
        fives = (valueInstances[4] == 6) ? 5*5 :valueInstances[4]*5;
        sixes = (valueInstances[5] == 6) ? 5*6 :valueInstances[5]*6;      
        
        threeOfAKind = getCombination(3, diceValues, valueInstances);
        fourOfAKind = getCombination(4, diceValues, valueInstances);
        fullHouse = getFullHouse(diceValues, valueInstances);
        chance = getCombination(1, diceValues, valueInstances);
        yahtzee = getCombination(5, diceValues, valueInstances);

        for( int i = 0; i < valueInstances.length; i++){
            if (valueInstances[i] == 0){
                consecutive = 0;
            } else {
                consecutive++;
            }
            smallStraight += (consecutive == 4) ? 30 : 0;
            largeStraight += (consecutive == 5) ? 40 : 0;
        }

        String[] possibleScores = {
            Integer.toString(ones),
            Integer.toString(twos),
            Integer.toString(threes),
            Integer.toString(fours),
            Integer.toString(fives),
            Integer.toString(sixes),
            "",
            Integer.toString(threeOfAKind),
            Integer.toString(fourOfAKind),
            Integer.toString(fullHouse),
            Integer.toString(smallStraight),
            Integer.toString(largeStraight),
            Integer.toString(barbie),
            Integer.toString(chance),
            Integer.toString(yahtzee),
            ""
        };

        return possibleScores;
    }

    public void setScore(int score, int combination){

    }

    public int getCombination(int combination, int[] values, int[] instances){

        Arrays.sort(values);
        int score = 0;
        int combinationDice = 0;

        for (int i = 5; i >= 0; i--){
            if (instances[i] >= combination){
                combinationDice = i+1;
                break;
            }
        }

        if(combinationDice == 0){
            return 0;
        }
        
        score += combinationDice*combination;

        int removedDice = 0;
        for (int i = 0; i<values.length; i++){
            if (values[i] == combinationDice && removedDice < combination){
                values[i] = 0;
                removedDice++;
            }
        }

        Arrays.sort(values);

        for (int i = 0; i < 5 - combination; i++){
            score += values[5-i];
        }
        return score;
    }

    public int getFullHouse(int[] values, int[] instances){
        
        Arrays.sort(values);
        boolean triplet = false;
        boolean pair = false;

        for (int i = 5; i >= 0; i--){
            if (instances[i] >= 3){
                triplet = true;
                instances[i]=0;
                break;
            }
        }
        
        for (int i = 5; i >= 0; i--){
            if (instances[i] >= 2 ){
                pair = true;
                break;
            }
        }

        return (pair && triplet) ? 25 : 0;
    }
}
