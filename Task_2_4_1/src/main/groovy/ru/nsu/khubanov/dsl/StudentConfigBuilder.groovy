package ru.nsu.khubanov.dsl

class StudentConfigBuilder {
    String nick, fullName, repo

    StudentConfigBuilder(String nick) { this.nick = nick }

    void fullName(String fn) { this.fullName = fn }
    void repo(String r)      { this.repo = r }

    StudentConfig build() {
        return new StudentConfig(nick, fullName, repo)
    }
}
