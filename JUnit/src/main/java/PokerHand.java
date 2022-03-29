

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {
    private String cards;
    private int[] strengthHand = new int[4];


    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<PokerHand>();
        hands.add(new PokerHand("2S 2H 3C 3D 3D"));
        hands.add(new PokerHand("4S 5C 6D 7S 8C"));
        hands.add(new PokerHand("2S JC 2C 2D JS"));
        hands.add(new PokerHand("2S TC 2C 2D TS"));
        hands.add(new PokerHand("2S JC 2C 2D AS"));

        Collections.sort(hands);
        Collections.reverse(hands);

    }

    public PokerHand(String allCards) {
        this.cards = allCards;
        this.convertSymbolToValue(cards);
    }

    public int[] getStrengthHand() {
        return strengthHand;
    }

    public String getCards() {
        return cards;
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
        for (int i = 0; i < cardsValues.length - 1; i++) {
            for (int j = i + 1; j < cardsValues.length; j++) {
                if (cardsValues[i] == cardsValues[j]) {
                    combination += 2;
                    highInCombination = checkHighInCombination(cardsValues[j], highInCombination);
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

    private int checkHighInCombination(int value, int high) {
        if (value == high) {
            high = value * 10;
        } else if (value > high) {
            high = value;
        }
        return high;
    }

    private int checkStraight(int[] cardsValues) {
        boolean straight = true;
        for (int i = 0; i < cardsValues.length - 1; i++) {
            if (cardsValues[i + 1] - cardsValues[i] != 1) {
                straight = false;
            }
        }
        if (straight) {
            return 7;
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
    public int compareTo(PokerHand o) {
        if (this.strengthHand[0] == o.strengthHand[0]) {
            if (this.strengthHand[1] == o.strengthHand[1]) {
                if (this.strengthHand[2] == o.strengthHand[2]) {
                    if (this.strengthHand[3] == o.strengthHand[3]) {
                        return 0;
                    } else {
                        return this.strengthHand[3] - o.strengthHand[3];
                    }
                } else {
                    return this.strengthHand[2] - o.strengthHand[2];
                }
            } else {
                return this.strengthHand[1] - o.strengthHand[1];
            }
        } else {
            return this.strengthHand[0] - o.strengthHand[0];
        }
    }
}
