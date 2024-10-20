package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

        @Test
        void testPlayerAddCardAndScore() {
            Dealer dealer = new Dealer();

            // Добавляем карту Король Пики (10)
            Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.KING);
            dealer.addCard(aceOfSpades);
            assertEquals(10, dealer.getScore(), "После добавления короля счет должен быть 10");

            // Добавляем карту Пятерка Черви (5)
            Card fiveOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.FIVE);
            dealer.addCard(fiveOfHearts);
            assertEquals(15, dealer.getScore(), "Сумма карт должна быть 15");

            // Добавляем карту Король Трефы (10)
            Card kingOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.KING);
            dealer.addCard(kingOfClubs);
            assertTrue(dealer.isBusted(), "Игрок должен иметь перебор");
        }

        @Test
        void testAceAdjustment() {
            Player player = new Player();

            // Добавляем два Туза (каждый по 11 очков, потом один станет 1)
            Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Rank.ACE);
            Card aceOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
            Card aceOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
            Card aceOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Rank.ACE);
            player.addCard(aceOfSpades);
            player.addCard(aceOfHearts);
            player.addCard(aceOfClubs);
            player.addCard(aceOfDiamonds);
            assertEquals(14, player.getScore(), "Сумма четырех тузов должна быть 14 (11+1+1+1)");

            // Добавляем карту Семерка Пики (7)
            Card sevenOfSpades = new Card(Card.Suit.SPADES, Card.Rank.SEVEN);
            player.addCard(sevenOfSpades);
            assertEquals(21, player.getScore(), "Сумма карт должна быть 21 (4 туза, 7, 11, 1, 1, 1)");
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
            Dealer dealer = new Dealer();

            // Добавляем карты, чтобы вызвать перебор
            Card tenOfClubs = new Card(Card.Suit.CLUBS, Card.Rank.TEN);
            Card queenOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN);
            Card nineOfHearts = new Card(Card.Suit.HEARTS, Card.Rank.NINE);
            dealer.addCard(tenOfClubs);
            dealer.addCard(queenOfDiamonds);
            dealer.addCard(nineOfHearts);

            assertTrue(dealer.isBusted(), "Игрок должен иметь перебор");
        }
    }

