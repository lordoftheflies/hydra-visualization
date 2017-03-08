package hu.cherubits.hydra;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Main;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Created by lordoftheflies on 2017.03.06..
 */
@Component
public class RinoTemplate implements AutoCloseable , InitializingBean {

    private final Logger LOG = Logger.getLogger(RinoTemplate.class.getCanonicalName());

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public class WorkerThread implements Runnable, AutoCloseable {

        private Object result = null;

        public WorkerThread(Map<String, Object> arguments) {
            LOG.info("New worker thread created.");
            arguments.forEach((key, value) -> globalScope.put(key, globalScope, value));
        }

        public void run() {
            LOG.info("Run application.");
            Main.main(new String[]{});
        }

        @Override
        public void close() throws Exception {
            result= globalScope.get("graph", globalScope);
        }

        public Object build() {
            return result;
        }
    }

    private static final String ABSOLUTE_PATH_TO_SOME_JAVASCRIPT_FILE = "";

    private Context rhinoContext;

    private Scriptable globalScope;

    public RhinoTemplate() {
        rhinoContext = Context.enter();
        globalScope = rhinoContext.initStandardObjects();
        rhinoContext.setOptimizationLevel(9);
        rhinoContext.setLanguageVersion(Context.VERSION_1_8);
        
    }

    public void visualize(Supplier<Stream> supplier, Map<String, Object> arguments) {
        try (WorkerThread thread = new  WorkerThread()) {
            thread.run();
            Object result = thread.build();
        }


    }

    public void afterPropertiesSet() throws Exception {
        // EnvJS support.
        Main.processFile(rhinoContext, globalScope, ABSOLUTE_PATH_TO_SOME_JAVASCRIPT_FILE);
        // D3 support.
        Main.processFile(rhinoContext, globalScope, ABSOLUTE_PATH_TO_SOME_JAVASCRIPT_FILE);
    }

    public void close() throws Exception {
        executor.shutdown();
        Context.exit();
    }
}
