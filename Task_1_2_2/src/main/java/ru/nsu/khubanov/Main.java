package ru.nsu.khubanov;

public class Main {
    public static void main(String[] args) {
        // Создание хеш-таблицы
        HashTable<String, Number> hashTable = new HashTable<>();

        // Добавление элементов
        hashTable.put("one", 1);
        hashTable.put("two", 2);

        // Обновление элемента
        hashTable.update("one", 1.5);

        // Получение значения
        System.out.println(hashTable.get("one")); // Ожидается: 1.5
        System.out.println(hashTable.get("two")); // Ожидается: 2

        // Удаление элемента
        hashTable.remove("one");
        System.out.println(hashTable.get("one")); // Ожидается: null
    }
}

