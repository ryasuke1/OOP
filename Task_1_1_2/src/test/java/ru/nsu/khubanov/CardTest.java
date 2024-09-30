package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardValues() {
        Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        assertEquals(11, aceOfSpades.getValue(aceOfSpades), "Туз должен иметь значение 11");

        Card kingOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.KING);
        assertEquals(10, kingOfHearts.getValue(kingOfHearts), "Король должен иметь значение 10");

        Card fiveOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE);
        assertEquals(5, fiveOfDiamonds.getValue(fiveOfDiamonds), "Пятерка должна иметь значение 5");
    }

    @Test
    void testCardStringRepresentation() {
        Card queenOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.QUEEN);
        assertEquals("Дама Трефы (10)", queenOfClubs.StringCard(queenOfClubs), "Неверное отображение карты");

        Card twoOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.TWO);
        assertEquals("Двойка Черви (2)", twoOfHearts.StringCard(twoOfHearts), "Неверное отображение карты");

        Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        assertEquals("Туз Пики (11)", aceOfSpades.StringCard(aceOfSpades), "Неверное отображение карты");
    }
}
