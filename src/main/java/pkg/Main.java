package pkg;

import com.oracle.truffle.api.exception.AbstractTruffleException;
import com.oracle.truffle.js.scriptengine.GraalJSEngineFactory;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false"); // silence warnings
        GraalJSEngineFactory graalJSEngineFactory = new GraalJSEngineFactory();
        GraalJSScriptEngine scriptEngine = graalJSEngineFactory.getScriptEngine();
        Integer eval = (Integer) scriptEngine.eval("2 + 2");
        if (eval != 4) throw new AssertionError("2 + 2 is not 4");
        System.out.println(eval); // prints 4
        try {
            throw new ABC("from truffle api");
        } catch (ABC e) {
            System.out.println(e.getMessage());
        }
    }

    public static class ABC extends AbstractTruffleException {
        public ABC(String message) {
            super(message);
        }
    }
}
