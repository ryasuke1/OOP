package ru.nsu.khubanov;

// Класс для управления дилером, наследующий Player
public class Dealer extends Player {

    // Новый метод для отображения карт на руке в первый раз
    public String showHandfirsttime() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHand().get(0).StringCard(getHand().get(0))).append(" и <закрытая карта> "); // Первая карта
        return sb.toString();
    }
}
