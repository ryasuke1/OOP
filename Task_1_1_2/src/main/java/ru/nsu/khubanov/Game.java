package ru.nsu.khubanov;

import java.util.Scanner;

// Главный класс игры, управляющий процессом
public class Game {
    private Player player;
    private Dealer dealer;
    private GetRandomCards deck;
    private int playerWins;
    private int dealerWins;
    private Scanner scanner;

    public Game() {
        player = new Player();
        dealer = new Dealer();
        deck = new GetRandomCards();
        playerWins = 0;
        dealerWins = 0;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в Блэкджек!");
        playGame();
        scanner.close();
    }

    // Основной игровой цикл
    private void playGame() {
        int round = 1;
        while (true) {
            System.out.println("\nРаунд " + round);
            player.resetHand();
            dealer.resetHand();
            dealCards();
            showCards();

            // Проверка на блэкджек
            if (checkBlackjack()) {
                round++;
                continue;
            }

            // Ход игрока
            playerTurn();

            // Ход дилера, если игрок не проиграл
            if (!player.isBusted()) {
                dealerTurn();
            }

            // Итоговый счет после раунда
            printScore();

            // Запрос на продолжение игры
            if (!askToPlayAgain()) {
                System.out.println("Спасибо за игру!");
                break;
            }

            round++;
        }
    }

    // Метод для раздачи карт
    private void dealCards() {
        player.addCard(deck.getcard());
        player.addCard(deck.getcard());
        dealer.addCard(deck.getcard());
        dealer.addCard(deck.getcard());
    }

    // Метод для показа карт
    private void showCards() {
        System.out.println("Ваши карты: " + player.showHand());
        System.out.println("Карты дилера: " + dealer.showHandfirsttime());
    }

    // Метод для проверки блэкджека
    private boolean checkBlackjack() {
        if (player.hasBlackjack()) {
            if (dealer.hasBlackjack()) {
                System.out.println("У обоих блэкджек! Ничья.");
            } else {
                System.out.println("У вас блэкджек! Вы выиграли раунд!");
                playerWins++;
            }
            return true;
        } else if (dealer.hasBlackjack()) {
            System.out.println("У дилера блэкджек! Вы проиграли раунд.");
            dealerWins++;
            return true;
        }
        return false;
    }

    // Метод для хода игрока
    private void playerTurn() {
        boolean playerTurn = true;
        while (playerTurn) {
            System.out.println("Введите '1', чтобы взять карту, и '0', чтобы остановиться.");
            int choice = scanner.nextInt();
            if (choice == 1) {
                player.addCard(deck.getcard());
                System.out.println("Ваши карты: " + player.showHand());
                if (player.hasBlackjack()) {
                    System.out.println("У вас блэкджек! Вы выиграли раунд!");
                    playerWins++;
                    return; // Переход к следующему раунду
                }
                if (player.isBusted()) {
                    System.out.println("У вас перебор! Вы проиграли раунд.");
                    dealerWins++;
                    playerTurn = false;
                }
            } else {
                playerTurn = false;
            }
        }
    }

    // Метод для хода дилера
    private void dealerTurn() {
        System.out.println("Ход дилера...");
        while (dealer.getScore() < 17) {
            dealer.addCard(deck.getcard());
            System.out.println("Дилер берет карту. Карты дилера: " + dealer.showHand());
        }
        if (dealer.isBusted()) {
            System.out.println("У дилера перебор! Вы выиграли раунд!");
            playerWins++;
        } else {
            compareScores();
        }
    }

    // Метод для сравнения очков
    private void compareScores() {
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

    // Метод для вывода счета
    private void printScore() {
        System.out.println("Счет: Игрок " + playerWins + " - " + dealerWins + " Дилер");
    }

    // Метод для запроса продолжения игры
    private boolean askToPlayAgain() {
        System.out.println("Введите '1' для начала следующего раунда или '0' для выхода.");
        int nextRound = scanner.nextInt();
        return nextRound == 1;
    }

    public static void main(String[] args) {
        Game game = new Game(); // Создаем новый объект игры
        game.start(); // Запускаем игру
    }
}
