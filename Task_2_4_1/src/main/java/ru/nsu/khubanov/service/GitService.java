package ru.nsu.khubanov.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashSet;
import java.util.Set;

public class GitService {

    public void cloneOrUpdate(String url, File dir)
            throws IOException, InterruptedException {
        File parent = dir.getParentFile(); parent.mkdirs();
        if (dir.exists())
            run(parent, "git", "-C", dir.getAbsolutePath(), "pull");
        else
            run(parent, "git", "clone", url, dir.getName());
    }

    public void checkoutBranch(File repoDir, String branch)
            throws IOException, InterruptedException {
        run(repoDir, "git", "checkout", branch);
    }

    /** Сколько учебных недель студент был активен (≥1 commit). */
    public int countActiveWeeks(File repoDir, LocalDate semesterStart)
            throws IOException, InterruptedException {

        String since = semesterStart.toString();
        Process p = new ProcessBuilder("git", "log",
                "--since=" + since, "--pretty=%cd", "--date=short")
                .directory(repoDir).start();

        Set<String> weeks = new HashSet<>();
        WeekFields wf = WeekFields.ISO;

        try (java.util.Scanner sc = new java.util.Scanner(p.getInputStream())) {
            while (sc.hasNextLine()) {
                String d = sc.nextLine().trim();
                if (d.isEmpty()) continue;
                LocalDate date = LocalDate.parse(d);
                String key = date.getYear() + "-" + date.get(wf.weekOfWeekBasedYear());
                weeks.add(key);
            }
        }
        p.waitFor();
        return weeks.size();
    }


    private void run(File wd, String... cmd)
            throws IOException, InterruptedException {
        Process p = new ProcessBuilder(cmd).directory(wd)
                .inheritIO().start();
        if (p.waitFor() != 0)
            throw new IOException("git error: " + String.join(" ", cmd));
    }
}
