package br.com.efraimgentil.examplesundertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class SimpleServerInstance {

    public static void main(String[] args) {
        //Creating a simples server instance
        Undertow server = Undertow.builder() //Create a builder to construct the server
                .addHttpListener(8080, "localhost")//add a listener to listen to the port 8080 under the uri localhost
                .setHandler(new HttpHandler() { // Set the handler that will manage the request for the listener
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<h1>Welcome to the undertow<h1>");
                    }
                })
                .build();
        server.start();
    }
    
}
