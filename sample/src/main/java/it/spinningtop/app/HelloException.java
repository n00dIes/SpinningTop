package it.spinningtop.app;


import it.spinningtop.lib.events.ExceptionEvent;

/**
 * Created by Paolo Brandi on 24/02/14.
 */
public class HelloException extends ExceptionEvent {

    public HelloException(Throwable throwable) {
        super(throwable);
    }
}
