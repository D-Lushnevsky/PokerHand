package tables.table1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokerHand implements Comparable {
    private String cards;
    private int[] strengthHand = new int[4];


    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<PokerHand>();
        PokerHand hand1 = new PokerHand("QS 9H QC QD 9D");

        for (int k = 0; k < hand1.strengthHand.length; k++) {
            System.out.println(hand1.strengthHand[k]);
        }


    }

    public PokerHand(String allCards) {
        this.cards = allCards;
        this.convertSymbolToValue(cards);
    }

    private void convertSymbolToValue(String text) {                        /** нужно добавить обработку ошибки ArrayOutOfBounds*/
        int[] values = new int[5];
        for (int i = 0; i < text.length(); i += 3) {
            values[i / 3] = convertStringToNumeric(text.charAt(i));
        }
        Arrays.sort(values);
        strengthHand = convertCardValuesToStrengthOfHand(values);
    }

    private int convertStringToNumeric(char ch) {
        int Value = -1;
        if (Character.isDigit(ch)) {
            Value = Character.getNumericValue(ch);
        } else {
            switch (ch) {
                case 'T':
                    Value = 10;
                    break;
                case 'J':
                    Value = 11;
                    break;
                case 'Q':
                    Value = 12;
                    break;
                case 'K':
                    Value = 13;
                    break;
                case 'A':
                    Value = 14;
                    break;
            }
        }
        return Value;
    }

    private int[] convertCardValuesToStrengthOfHand(int[] cardsValues) {
        int combination = 0;
        int highInCombination = 0;
        int[] strength = new int[4];
        for (int i = 0; i < cardsValues.length; i++) {
            if (cardsValues[i] == cardsValues[i + 1]) {                             /** check Combination */
                combination = +2;
                highInCombination = checkHighInCombination(cardsValues, i);
            }
            for (int j = i + 1; j < cardsValues.length; j++) {
                if (cardsValues[j] == cardsValues[j + 1]) {
                    combination = +2;
                    highInCombination = checkHighInCombination(cardsValues, j);
                }
            }
        }

        if (combination == 0) {
            combination = checkStraight(cardsValues);
        }
        strength[0] = combination;
        strength[1] = highInCombination;
        strength[2] = cardsValues[4];
        strength[3] = checkSecondHigh(cardsValues);

        return strength;
    }

    private int checkHighInCombination(int[] cardsValues, int k) {
        int highInCombination = 0;
        if (cardsValues[k] == highInCombination) {
            highInCombination = cardsValues[k] * 10;
        } else if (cardsValues[k] > highInCombination) {
            highInCombination = cardsValues[k];
        }
        return highInCombination;
    }

    private int checkStraight(int[] cardsValues) {
        boolean straight = true;
        for (int i = 0; i < cardsValues.length - 1; i++) {
            if (cardsValues[i + 1] - cardsValues[i] != 1) {
                straight = false;
            }
        }
        if (straight) {
            return 5;
        } else {
            return 0;
        }
    }

    private int checkSecondHigh(int[] cardsValues) {
        int secondHigh = 0;
        for (int i = cardsValues.length - 1; i >= 0; i--) {
            if (cardsValues[i] != cardsValues[i - 1]) {
                secondHigh = cardsValues[i - 1];
                break;
            }
        }
        return secondHigh;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
