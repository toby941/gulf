package com.gulf.util;

import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MarkdownUtils {
    private final static Logger log = Logger.getLogger(MarkdownUtils.class);

    public static Invocable getInvocable(String path) throws ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("javascript");

        String root = System.getProperty("user.dir");
        String jsFileName = root + path;
        FileReader reader;
        reader = new FileReader(jsFileName);
        engine.eval(reader);
        reader.close();
        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable) engine;
            return invoke;
        }
        else {
            return null;
        }
    }

    public static Invocable getInvocableFromHttp(String url) throws ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("javascript");
        String content = HttpUtils.doGetRequest(url);
        engine.eval(content);
        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable) engine;
            return invoke;
        }
        else {
            return null;
        }
    }

    public static String makeHtml(String source) {
        String result = StringUtils.EMPTY;
        try {
            String http = "http://weimp.sinaapp.com/js/markdown/Markdown.Converter.js";
            Invocable invoke = getInvocableFromHttp(http);
            result = invoke.invokeFunction("makeHtml", source).toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("makeHtml error", e);
        }
        return result;
    }

    public static void main(String[] args) {
        String source = "**I am bold!**";
        System.out.println(makeHtml(source));
    }

}
