package br.com.efraimgentil.examplesundertow.view;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import br.com.efraimgentil.examplesundertow.util.Page;

@Page("saynothing")
public class SayNothingPage implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        exchange.getResponseSender().send("<h1>xiu mother fucker<h1>");
	}

}
