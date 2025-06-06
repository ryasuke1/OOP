package ru.nsu.khubanov;

/**
 *
 */
public class Card {
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    private final Suit suit;
    private final Rank rank;

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit(){
        return suit;
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue(Card card) {
        if (card.getRank()==Card.Rank.ACE){
            return 11;
        }
        if (card.getRank()== Rank.TWO){
            return 2;
        }if (card.getRank()== Rank.THREE){
            return 3;
        }if (card.getRank()== Rank.FOUR){
            return 4;
        }if (card.getRank()== Rank.FIVE){
            return 5;
        }if (card.getRank()==Card.Rank.SIX){
            return 6;
        }if (card.getRank()== Rank.SEVEN){
            return 7;
        }if (card.getRank()== Rank.EIGHT){
            return 8;
        }if (card.getRank()==Card.Rank.NINE){
            return 9;
        }
        return 10;

    }
    public String StringCard(Card card) {
        // Проверка для Туза
        if (card.getRank() == Card.Rank.ACE) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Туз Трефы (11)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновый туз (11)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Туз Пики (11)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Туз Черви (11)";
            }
        }

        // Проверка для Двойки
        if (card.getRank() == Card.Rank.TWO) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Двойка Трефы (2)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая двойка (2)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Двойка Пики (2)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Двойка Черви (2)";
            }
        }

        // Проверка для Тройки
        if (card.getRank() == Card.Rank.THREE) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Тройка Трефы (3)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая тройка (3)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Тройка Пики (3)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Тройка Черви (3)";
            }
        }

        // Проверка для Четверки
        if (card.getRank() == Card.Rank.FOUR) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Четверка Трефы (4)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая четверка (4)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Четверка Пики (4)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Четверка Черви (4)";
            }
        }

        // Проверка для Пятерки
        if (card.getRank() == Card.Rank.FIVE) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Пятерка Трефы (5)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая пятерка (5)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Пятерка Пики (5)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Пятерка Черви (5)";
            }
        }

        // Проверка для Шестерки
        if (card.getRank() == Card.Rank.SIX) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Шестерка Трефы (6)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая шестерка (6)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Шестерка Пики (6)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Шестерка Черви (6)";
            }
        }

        // Проверка для Семерки
        if (card.getRank() == Card.Rank.SEVEN) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Семерка Трефы (7)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая семерка (7)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Семерка Пики (7)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Семерка Черви (7)";
            }
        }

        // Проверка для Восьмерки
        if (card.getRank() == Card.Rank.EIGHT) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Восьмерка Трефы (8)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая восьмерка (8)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Восьмерка Пики (8)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Восьмерка Черви (8)";
            }
        }

        // Проверка для Девятки
        if (card.getRank() == Card.Rank.NINE) {
            if (card.getSuit() == Card.Suit.CLUBS) {
                return "Девятка Трефы (9)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return "Бубновая девятка (9)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return "Девятка Пики (9)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return "Девятка Черви (9)";
            }
        }

        // По умолчанию для остальных карт (десятки и выше)
        if (card.getRank() == Card.Rank.TEN ||
                card.getRank() == Card.Rank.JACK ||
                card.getRank() == Card.Rank.QUEEN ||
                card.getRank() == Card.Rank.KING) {

            String rankString = "";
            if (card.getRank() == Card.Rank.TEN) rankString = "Десятка";
            if (card.getRank() == Card.Rank.JACK) rankString = "Валет";
            if (card.getRank() == Card.Rank.QUEEN) rankString = "Дама";
            if (card.getRank() == Card.Rank.KING) rankString = "Король";

            if (card.getSuit() == Card.Suit.CLUBS) {
                return rankString + " Трефы (10)";
            } else if (card.getSuit() == Card.Suit.DIAMONDS) {
                return rankString + " Бубен (10)";
            } else if (card.getSuit() == Card.Suit.SPADES) {
                return rankString + " Пики (10)";
            } else if (card.getSuit() == Card.Suit.HEARTS) {
                return rankString + " Червей (10)";
            }
        }

        return "Ошибка";
    }

}
