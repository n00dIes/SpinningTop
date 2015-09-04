package it.spinningtop.app;

import it.spinningtop.lib.events.SuccessEvent;

/**
 * Created by Paolo Brandi on 24/02/14.
 */
public class HelloSuccess extends SuccessEvent<String> {

    public HelloSuccess(String s) {
        super(s);
    }
}
