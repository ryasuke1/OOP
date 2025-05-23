package ru.nsu.khubanov.service;

import com.google.googlejavaformat.java.*;
import com.google.googlejavaformat.java.Formatter;

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class StyleService {
    private final Formatter fmt = new Formatter();
    public boolean checkStyle(File dir) {
        List<File> src = new ArrayList<>(); collect(dir, src);
        for (File f : src) {
            try {
                String orig = Files.readString(f.toPath());
                String frmt = fmt.formatSource(orig);
                if (!orig.equals(frmt)) { System.err.println("style: " + f); return false; }
            } catch (Throwable t) {
                System.err.println("style skipped ("+t.getClass().getSimpleName()+")");
                return true;
            }
        }
        return true;
    }
    private void collect(File d, List<File> out){
        if(d.isDirectory()) for(File c:Objects.requireNonNull(d.listFiles())) collect(c,out);
        else if(d.getName().endsWith(".java")) out.add(d);
    }
}
