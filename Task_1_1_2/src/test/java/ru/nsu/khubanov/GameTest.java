package ru.nsu.khubanov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(); // Инициализация новой игры перед каждым тестом
    }

    @Test
    void testPlayerWinsAfterBlackjack() {
        // Симулируем ситуацию, когда у игрока блэкджек
        Player player = new Player();
        Dealer dealer = new Dealer();
        GetRandomCards deck = new GetRandomCards();

        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // Туз
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.KING)); // Король

        dealer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN)); // Дама
        dealer.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT)); // Восемь

        // Проверяем, что игрок выиграл раунд
        assertTrue(player.hasBlackjack(), "Игрок должен иметь блэкджек.");
        assertFalse(dealer.hasBlackjack(), "Дилер не должен иметь блэкджек.");
    }

    @Test
    void testDealerBusted() {
        // Симулируем ситуацию, когда у дилера перебор
        Dealer dealer = new Dealer();
        GetRandomCards deck = new GetRandomCards();

        // Заполняем руки дилера
        dealer.addCard(new Card(Card.Suit.SPADES, Card.Rank.KING)); // Король (10)
        dealer.addCard(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN)); // Дама (10)
        dealer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.FIVE)); // Пятерка (5)

        assertTrue(dealer.isBusted(), "Дилер должен иметь перебор.");
    }

    @Test
    void testScoreComparison() {
        // Тестирование сравнения очков между игроком и дилером
        Player player = new Player();
        Dealer dealer = new Dealer();
        player.addCard(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // 9
        player.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE)); // Туз (11)

        dealer.addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT)); // 8
        dealer.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO)); // 2

        // Проверяем, что у игрока больше очков
        assertTrue(player.getScore() > dealer.getScore(), "Игрок должен иметь больше очков, чем дилер.");
    }
}
