package it.spinningtop.app;


import it.spinningtop.lib.events.ExceptionEvent;


public class HelloException extends ExceptionEvent {

    public HelloException(Throwable throwable) {
        super(throwable);
    }
}
