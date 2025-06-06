package ru.nsu.khubanov.service;

import org.junit.platform.engine.discovery.*;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.File;
import java.net.*;
import java.util.Set;
import java.util.concurrent.*;

public class TestService {
    public static class TestResult { public final int passed, failed;
        public TestResult(int p,int f){passed=p;failed=f;} }

    public TestResult runTests(File dir) throws Exception {
        File cls = new File(dir,"build/classes");
        if(!cls.exists()) return new TestResult(0,0);

        URLClassLoader cl = new URLClassLoader(new URL[]{cls.toURI().toURL()},
                getClass().getClassLoader());
        LauncherDiscoveryRequest req = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClasspathRoots(Set.of(cls.toPath())))
                .build();

        Launcher l = LauncherFactory.create();
        SummaryGeneratingListener lis = new SummaryGeneratingListener();
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> f = es.submit(() -> l.execute(req, lis));
        try { f.get(60, TimeUnit.SECONDS);}
        catch (TimeoutException te){f.cancel(true);return new TestResult(0,0);}
        finally {es.shutdownNow();}

        var s = lis.getSummary();
        return new TestResult((int)s.getTestsSucceededCount(), (int)s.getTestsFailedCount());
    }
}
