/**
 * Created by user on 11/14/2019.
 */

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class expressionParser {
    public static void main(String[] args) throws IOException, ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "(-(4+2)/2-21)";
        System.out.println(engine.eval(foo));

    }
}
