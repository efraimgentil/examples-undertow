package br.com.efraimgentil.examplesundertow.view;

import br.com.efraimgentil.examplesundertow.util.Page;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

@Page( { "/" , "/home" } ) 
public class HomePage implements HttpHandler {
	
	public HomePage() { 
	}
	
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send("<h1>Welcome to the undertow<h1>");
		
	}

}
