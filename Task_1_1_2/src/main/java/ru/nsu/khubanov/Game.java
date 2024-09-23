package ru.nsu.khubanov;

import java.util.Scanner;

// Главный класс игры, управляющий процессом
public class Game {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Блэкджек!");

        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Player dealer = new Player();
        GetRandomCards deck = new GetRandomCards();

        int playerWins = 0;
        int dealerWins = 0;
        int round = 1;

        // Основной игровой цикл
        while (true) {
            System.out.println("\nРаунд " + round);
            player.resetHand();
            dealer.resetHand();

            // Раздача карт игроку и дилеру
            player.addCard(deck.getcard());
            player.addCard(deck.getcard());

            dealer.addCard(deck.getcard());
            dealer.addCard(deck.getcard());

            // Показ карт игрока и одной карты дилера
            System.out.println("Ваши карты: " + player.showHand());
            System.out.println("Карты дилера: " + dealer.getHand().get(0).StringCard(dealer.getHand().get(0)) + " и <закрытая карта>");

            // Проверка на блэкджек у игрока и дилера
            if (player.hasBlackjack()) {
                if (dealer.hasBlackjack()) {
                    System.out.println("У обоих блэкджек! Ничья.");
                } else {
                    System.out.println("У вас блэкджек! Вы выиграли раунд!");
                    playerWins++;
                }
                round++;
                continue; // Переход к следующему раунду
            } else if (dealer.hasBlackjack()) {
                System.out.println("У дилера блэкджек! Вы проиграли раунд.");
                dealerWins++;
                round++;
                continue; // Переход к следующему раунду
            }

            // Ход игрока
            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Введите '1', чтобы взять карту, и '0', чтобы остановиться.");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    player.addCard(deck.getcard());
                    System.out.println("Ваши карты: " + player.showHand());

                    if (player.isBusted()) {
                        System.out.println("У вас перебор! Вы проиграли раунд.");
                        dealerWins++;
                        playerTurn = false;
                    }
                } else {
                    playerTurn = false;
                }
            }

            // Ход дилера, если игрок не проиграл
            if (!player.isBusted()) {
                System.out.println("Ход дилера...");
                System.out.println("Карты дилера: " + dealer.showHand());
                while (dealer.getScore() < 17) {
                    dealer.addCard(deck.getcard());
                    System.out.println("Дилер берет карту. Карты дилера: " + dealer.showHand());
                }

                if (dealer.isBusted()) {
                    System.out.println("У дилера перебор! Вы выиграли раунд!");
                    playerWins++;
                } else {
                    // Сравнение очков
                    System.out.println("Ваши очки: " + player.getScore());
                    System.out.println("Очки дилера: " + dealer.getScore());

                    if (player.getScore() > dealer.getScore()) {
                        System.out.println("Вы выиграли раунд!");
                        playerWins++;
                    } else if (player.getScore() < dealer.getScore()) {
                        System.out.println("Вы проиграли раунд.");
                        dealerWins++;
                    } else {
                        System.out.println("Ничья.");
                    }
                }
            }

            // Итоговый счет после раунда
            System.out.println("Счет: Игрок " + playerWins + " - " + dealerWins + " Дилер");

            // Предложение сыграть еще один раунд или выйти
            System.out.println("Введите '1' для начала следующего раунда или '0' для выхода.");
            int nextRound = scanner.nextInt();
            if (nextRound == 0) {
                System.out.println("Спасибо за игру!");
                break;
            }

            round++;
        }

        scanner.close();
    }
}
