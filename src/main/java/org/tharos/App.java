package org.tharos;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.logging.Logger;

import org.tharos.httplient.verbs.Get;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger LOG = Logger.getLogger("main");

    public static void main(String[] args) {
        LOG.info("GET Call");
        try {
            AbstractMap.SimpleEntry<String, String> qp = new AbstractMap.SimpleEntry<String, String>("qp", "test");
            new Get().simpleGetCall("https://api.publicapis.org/entries", qp);
        } catch (IOException e) {
            LOG.severe("GET Call gone wrong " + e.getMessage());
        }

        try {
            AbstractMap.SimpleEntry<String, String> qp = new AbstractMap.SimpleEntry<String, String>("qp", "test");
            new Get().simpleGetCallTimeoutAware("https://api.publicapis.org/entries", qp, 1500);
        } catch (IOException e) {
            LOG.severe("GET Call gone wrong " + e.getMessage());
        }

        new Get().simpleAsyncGetCall("https://api.publicapis.org/entries");
    }
}
