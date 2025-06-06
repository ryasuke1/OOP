package ru.nsu.khubanov.dsl;

public class StudentConfig {
    private final String nick;
    private final String fullName;
    private final String repo;

    public StudentConfig(String nick, String fullName, String repo) {
        this.nick = nick;
        this.fullName = fullName;
        this.repo = repo;
    }

    // ======= Геттеры =======
    public String getNick() {
        return nick;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRepo() {
        return repo;
    }

    @Override
    public String toString() {
        return nick + "(" + fullName + ")";
    }
}
