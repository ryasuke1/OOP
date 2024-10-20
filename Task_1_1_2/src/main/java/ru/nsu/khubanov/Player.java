package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;

// Класс для управления игроками (как Игрок, так и Дилер)
class Player {
    private List<Card> hand; // Карты в руке
    private int score; // Сумма очков

    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    // Добавление карты в руку и обновление счета
    public void addCard(Card card) {
        hand.add(card);
        updateScore();
    }

    // Получение списка карт в руке
    public List<Card> getHand() {
        return hand;
    }

    // Получение текущего счета
    public int getScore() {
        return score;
    }

    // Обновление счета на основе карт в руке
    private void updateScore() {
        int sum = 0;
        int aces = 0;

        for (Card card : hand) {
            int cardValue = card.getValue(card);
            sum += cardValue;
            if (card.getRank() == Card.Rank.ACE) {
                aces++;
            }
        }

        // Корректировка значения тузов, если сумма превышает 21
        while (sum > 21 && aces > 0) {
            sum -= 10; // Уменьшаем значение туза с 11 до 1
            aces--;
        }
        score = sum;
    }

    // Сброс руки игрока (для нового раунда)
    public void resetHand() {
        hand.clear();
        score = 0;
    }

    // Показать карты игрока в виде строки
    public String showHand() {
        String result = ""; // Создаем пустую строку для накопления результата
        for (Card card : hand) { // Проходим по каждой карте в руке
            result += card.StringCard(card) + ", "; // Добавляем строковое представление карты и запятую
        }
        result += "> " + score; // В конце добавляем сумму очков
        return result; // Возвращаем итоговую строку
    }


    // Проверка на перебор (сумма больше 21)
    public boolean isBusted() {
        return score > 21;
    }

    // Проверка на наличие блэкджека
    public boolean hasBlackjack() {
        return score == 21;
    }
}
