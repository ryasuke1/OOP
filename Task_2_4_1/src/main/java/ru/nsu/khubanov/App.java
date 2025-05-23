package ru.nsu.khubanov;

import groovy.lang.GroovyShell;
import ru.nsu.khubanov.dsl.*;
import ru.nsu.khubanov.service.*;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {

        /* ---- загрузка конфигурации ---- */
        Config cfg = (Config) new GroovyShell()
                .evaluate(new File("build.oop.groovy"));

        GitService   git = new GitService();
        CompileService cmp = new CompileService();
        StyleService   sty = new StyleService();
        TestService    tst = new TestService();
        ScoreService   scr = new ScoreService();
        ReportGenerator rep = new ReportGenerator();

        double bonusPerWeek = ((Number) cfg.getSettings()
                .getOrDefault("activityBonus", 1)).doubleValue();
        LocalDate semesterStart = LocalDate.parse(
                (String) cfg.getSettings()
                        .getOrDefault("semesterStart", "2025-02-01"));

        List<ReportGenerator.Result> results = new ArrayList<>();
        File reposRoot = new File("repos"); reposRoot.mkdirs();

        /* ---- обход групп/студентов ---- */
        for (GroupConfig g : cfg.getGroups())
            for (StudentConfig s : g.getStudents()) {

                String nick = s.getNick();
                File repo = new File(reposRoot, nick);

                System.out.println("Cloning/updating \"" + nick + "\" …");
                git.cloneOrUpdate(s.getRepo(), repo);
                git.checkoutBranch(repo, "main");

                /* считаем активные недели 1 раз на репозиторий */
                int weeks = git.countActiveWeeks(repo, semesterStart);

                List<AssignmentConfig> myAsg = cfg.getAssignments().stream()
                        .filter(a -> a.getStudentNick().equals(nick))
                        .collect(Collectors.toList());

                for (AssignmentConfig asg : myAsg) {
                    String taskId = asg.getTaskId();

                    /* ---- поиск папки Task_x_x_x ---- */
                    String prefix = "Task_" + taskId.replace('.', '_');
                    File[] cand = repo.listFiles(f -> f.isDirectory()
                            && f.getName().startsWith(prefix));
                    File work = (cand != null && cand.length > 0)
                            ? Arrays.stream(cand)
                            .max(Comparator.comparing(File::getName))
                            .orElse(repo)
                            : repo;

                    System.out.printf("=== %s — %s (%s)%n",
                            nick, taskId, work.getName());

                    /* ---- pipeline ---- */
                    boolean compiled = cmp.compile(work);
                    cmp.javadoc(work);
                    boolean styleOk  = compiled && sty.checkStyle(work);
                    TestService.TestResult tr = compiled
                            ? tst.runTests(work)
                            : new TestService.TestResult(0, 0);

                    TaskConfig tc = cfg.getTasks().stream()
                            .filter(t -> t.getId().equals(taskId))
                            .findFirst().orElseThrow();

                    int base = scr.calculate(tc, compiled, styleOk, tr);
                    int score = (int) Math.round(base + weeks * bonusPerWeek);

                    results.add(new ReportGenerator.Result(
                            nick, taskId, score, weeks));
                }
            }
        /* ---- HTML отчёт ---- */
        rep.generate(cfg, results);
    }
}
