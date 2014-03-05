package br.com.efraimgentil.examplesundertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class TwoServerInstances {
    
    public static void main(String[] args) {
            
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<h1>Server 1<h1>");
                    }
                })
                .build();
        server.start();
        
        Undertow server2 = Undertow.builder()
                .addHttpListener(9999, "localhost")
                .setHandler(new HttpHandler() {
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                        exchange.getResponseSender().send("<h1>Server 2<h1>");
                    }
                }).build();
        server2.start();
        
    }
    
}
