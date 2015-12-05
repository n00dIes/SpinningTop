package it.spinningtop.app;


import it.spinningtop.lib.Request;
import it.spinningtop.lib.events.ExceptionEvent;


public class HelloException extends ExceptionEvent {

    public HelloException(Request request, Throwable throwable) {
        super(request, throwable);
    }
}
