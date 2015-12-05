package it.spinningtop.app;

import it.spinningtop.lib.events.SuccessEvent;


public class HelloSuccess extends SuccessEvent<String> {

    public HelloSuccess(String s) {
        super(s);
    }
}
