package whm.core.base.java.thread;

import javassist.bytecode.analysis.Executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thinkpad on 21/3/2016.
 */
public class ThreadTest {
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


}
