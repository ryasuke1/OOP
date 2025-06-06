package ru.nsu.khubanov.service;

import javax.tools.*;
import java.io.File;
import java.util.*;

public class CompileService {

    public boolean compile(File dir) {
        List<File> java = new ArrayList<>();
        collect(dir, java);
        if (java.isEmpty()) return false;

        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = jc.getStandardFileManager(null, null, null);

        File outDir = new File(dir, "build/classes");
        outDir.mkdirs();

        String cp = System.getProperty("java.class.path");

        JavaCompiler.CompilationTask task = jc.getTask(
                null, fm, null,
                List.of("-d", outDir.getAbsolutePath(),
                        "-classpath", cp),
                null, fm.getJavaFileObjectsFromFiles(java));

        boolean ok = task.call();
        try { fm.close(); } catch (Exception ignored) {}
        return ok;
    }

    private void collect(File f, List<File> out) {
        if (f.isDirectory())
            for (File c : Objects.requireNonNull(f.listFiles())) collect(c, out);
        else if (f.getName().endsWith(".java")) out.add(f);
    }




    public boolean javadoc(File dir) {
        List<File> src = new ArrayList<>();
        collect(dir, src);
        if (src.isEmpty()) return false;

        List<String> cmd = new ArrayList<>(List.of("javadoc", "-quiet",
                "-d", new File(dir, "build/docs").getAbsolutePath()));
        src.forEach(f -> cmd.add(f.getAbsolutePath()));
        try {
            return new ProcessBuilder(cmd).inheritIO().start().waitFor() == 0;
        } catch (Exception e) { return false; }
    }
}
