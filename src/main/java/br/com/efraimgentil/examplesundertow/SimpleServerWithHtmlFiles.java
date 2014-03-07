package br.com.efraimgentil.examplesundertow;

import br.com.efraimgentil.examplesundertow.util.HtmlToString;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * 
 * @author Efraim Gentil (efraim.gentil@gmail.com)
 */
public class SimpleServerWithHtmlFiles {
    
    public static void main(String[] args) {
        SimpleServerWithHtmlFiles server = new SimpleServerWithHtmlFiles();
        server.startServer();
    }
    
    public void startServer(){
        Undertow server = Undertow
                .builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                        Handlers.path()
                                .addExactPath("/", new IndexPage())
                ).build();
        server.start();
    }
    
}

class IndexPage implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        String page = new HtmlToString().getHtmlPage("index.html");
        exchange.getResponseSender().send( page );
    }
    
}