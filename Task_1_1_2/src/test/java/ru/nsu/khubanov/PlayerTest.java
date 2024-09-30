package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerAddCardAndScore() {
        Player player = new Player();

        // Добавляем карту Король Пики (10)
        Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.KING);
        player.addCard(aceOfSpades);
        assertEquals(10, player.getScore(), "После добавления короля счет должен быть 10");

        // Добавляем карту Пятерка Черви (5)
        Card fiveOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.FIVE);
        player.addCard(fiveOfHearts);
        assertEquals(15, player.getScore(), "Сумма карт должна быть 15");

        // Добавляем карту Король Трефы (10)
        Card kingOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.KING);
        player.addCard(kingOfClubs);
        assertTrue(player.isBusted(), "Игрок должен иметь перебор");
    }

    @Test
    void testAceAdjustment() {
        Player player = new Player();

        // Добавляем два Туза (каждый по 11 очков, потом один станет 1)
        Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        Card aceOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        player.addCard(aceOfSpades);
        player.addCard(aceOfHearts);
        assertEquals(12, player.getScore(), "Сумма двух тузов должна быть 12 (11+1)");

        // Добавляем карту Семерка Пики (7)
        Card sevenOfSpades = new Card(Card.Suit.SPADES, Card.Rank.SEVEN);
        player.addCard(sevenOfSpades);
        assertEquals(19, player.getScore(), "Сумма карт должна быть 19 (1 туза, 7, 11)");
    }

    @Test
    void testBlackjack() {
        Player player = new Player();

        // Добавляем карты на блэкджек
        Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.ACE);
        Card tenOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        player.addCard(aceOfSpades);
        player.addCard(tenOfHearts);
        assertTrue(player.hasBlackjack(), "Игрок должен иметь блэкджек");
    }

    @Test
    void testBusted() {
        Player player = new Player();

        // Добавляем карты, чтобы вызвать перебор
        Card tenOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
        Card queenOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN);
        Card nineOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.NINE);
        player.addCard(tenOfClubs);
        player.addCard(queenOfDiamonds);
        player.addCard(nineOfHearts);

        assertTrue(player.isBusted(), "Игрок должен иметь перебор");
    }
}
