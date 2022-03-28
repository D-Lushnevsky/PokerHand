package tables.table1;

import java.util.ArrayList;
import java.util.List;

public class PokerHand implements Comparable {
    private String[] cards;
    private int[] valuesOfCards = new int[5];

    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<PokerHand>();
        PokerHand hand1 = new PokerHand("9S 2H QC JD AD");

        for (int k = 0; k < hand1.valuesOfCards.length; k++ ) {
            System.out.println(hand1.valuesOfCards[k]);
        }


    }

    public PokerHand(String cards) {
        this.ConversionSymbolToValue(cards);
    }

    private void ConversionSymbolToValue(String text) {                               /** нужно добавить обработку ошибки ArrayOutOfBounds*/
        for (int i = 0; i < text.length(); i += 3) {
            valuesOfCards[i/3] = ConversionStringToNumeric(text.charAt(i));
        }
    }

    private static int ConversionStringToNumeric(char ch) {
        int Value = -1;
        if (Character.isDigit(ch)) {
            Value = Character.getNumericValue(ch);
        } else {
            switch (ch) {
                case 'T':
                    Value = 10;
                    break;
                case 'J' :
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
