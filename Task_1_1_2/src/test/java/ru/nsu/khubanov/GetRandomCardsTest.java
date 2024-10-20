package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetRandomCardsTest {

    @Test
    void testDeckSize() {
        GetRandomCards deck = new GetRandomCards();
        assertEquals(52, deck.cardsRemaining(), "В колоде должно быть 52 карты после создания");
    }

    @Test
    void testGetCard() {
        GetRandomCards deck = new GetRandomCards();
        Card card = deck.getcard();
        assertNotNull(card, "Карта не должна быть null");
        assertEquals(51, deck.cardsRemaining(), "После вытягивания карты в колоде должно остаться 51 карта");
    }

    @Test
    void testDeckEmpty() {
        GetRandomCards deck = new GetRandomCards();

        // Вытягиваем все карты
        for (int i = 0; i < 52; i++) {
            deck.getcard();
        }

        // Проверка, что колода пуста
        assertEquals(0, deck.cardsRemaining(), "В колоде не должно оставаться карт");
        assertThrows(RuntimeException.class, deck::getcard, "Должно выбрасываться исключение, когда карт больше нет");
    }
}
