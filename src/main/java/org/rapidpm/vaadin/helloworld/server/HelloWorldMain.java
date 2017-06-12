package org.rapidpm.vaadin.helloworld.server;

import io.undertow.Undertow;
import io.undertow.util.Headers;

/**
 *
 */
public class HelloWorldMain {
  public static void main(String[] args) {
    Undertow server = Undertow
        .builder()
        .addHttpListener(8080, "localhost")
        .setHandler(exchange -> {
          exchange.getResponseHeaders()
                  .put(Headers.CONTENT_TYPE, "text/plain");
          exchange.getResponseSender()
                  .send("Hello World");
        }).build();
    server.start();
    server.getListenerInfo().forEach(System.out::println);
  }
}
