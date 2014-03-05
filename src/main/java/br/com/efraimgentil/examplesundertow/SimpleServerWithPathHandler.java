package br.com.efraimgentil.examplesundertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.util.Headers;

public class SimpleServerWithPathHandler {

    public static void main(String[] args) {

        PathHandler pathHandler = Handlers.path();
        pathHandler.addExactPath("/", new DefaultPage());

        Undertow server = Undertow
                .builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                        Handlers.path()
                                .addExactPath("/welcome", new WelcomePage())
                                .addExactPath("/", new DefaultPage())
                ).build();
        server.start();
    }

}

class DefaultPage implements HttpHandler {
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send("<h1>This is the default page</h1>");
    }
}

class WelcomePage implements HttpHandler {

    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send(
                "<h1>Welcome page</h1><p>Hello my friend</p>");
    }
}
