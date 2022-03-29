import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPokerHand {
    int[] array = new int[4];

    @Test
    public void testConvertSymbolToValue()  {
        array[0] = 4;
        array[1] = 11;
        array[2] = 14;
        array[3] = 11;

        PokerHand pokerHand1 = new PokerHand("9H JD 9H AC JS");
        int [] arrayCheck = pokerHand1.getStrengthHand();
        Assertions.assertArrayEquals(array, arrayCheck);

        PokerHand pokerHand2 = new PokerHand("JH 7C JC AC JH");
        array[0] = 6;
        array[1] = 110;
        array[2] = 14;
        array[3] = 11;
        arrayCheck = pokerHand2.getStrengthHand();
        Assertions.assertArrayEquals(array, arrayCheck);
    }

    @Test
    public void testCompareTo() {
        PokerHand pokerHand1 = new PokerHand("9H JD 9H AC JS");
        PokerHand pokerHand2 = new PokerHand("JH 7C JC AC JH");
        int compare = pokerHand2.compareTo(pokerHand1);
        Assertions.assertEquals(2, compare);

        PokerHand pokerHand3 = new PokerHand("5H AD 5H TC TS");
        PokerHand pokerHand4 = new PokerHand("5H TC TC 5C JH");
        compare = pokerHand3.compareTo(pokerHand4);
        Assertions.assertEquals(3, compare);
    }

}
