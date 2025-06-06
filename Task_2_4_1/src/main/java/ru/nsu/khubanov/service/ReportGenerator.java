package ru.nsu.khubanov.service;

import ru.nsu.khubanov.dsl.Config;

import java.io.PrintWriter;
import java.util.List;

public class ReportGenerator {

    public static class Result {
        public final String studentNick;
        public final String taskId;
        public final int    score;
        public final int    activeWeeks;
        public Result(String n, String t, int s, int w){
            studentNick=n; taskId=t; score=s; activeWeeks=w;
        }
    }

    /** HTML-таблица → stdout */
    public void generate(Config cfg, List<Result> rows){
        try(PrintWriter out = new PrintWriter(System.out)){
            out.println("<html><head><meta charset='UTF-8'><title>OOP Report</title></head><body>");
            out.println("<h1>Отчёт по курсу ООП</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Студент</th><th>Задача</th><th>Баллы</th><th>Активные недели</th></tr>");

            for(Result r: rows){
                out.printf("<tr><td>%s</td><td>%s</td><td>%d</td><td>%d</td></tr>%n",
                        r.studentNick, r.taskId, r.score, r.activeWeeks);
            }
            out.println("</table></body></html>");
        }
    }
}
