package ru.nsu.khubanov;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetRandomCards {
    private final List<Card> cards;

    // Конструктор для создания и перемешивания колоды
    public GetRandomCards() {
        cards = new ArrayList<>();

        // Заполняем колоду всеми возможными картами
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }

        // Перемешиваем колоду
        Collections.shuffle(cards);
    }

    // Метод для "вытягивания" карты
    public Card getcard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck!");
        }
        return cards.remove(cards.size() - 1);  // Убираем и возвращаем последнюю карту
    }

    // Метод для проверки оставшихся карт в колоде
    public int cardsRemaining() {
        return cards.size();
    }
}
