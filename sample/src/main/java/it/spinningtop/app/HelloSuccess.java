package it.spinningtop.app;

import it.spinningtop.lib.Request;
import it.spinningtop.lib.events.SuccessEvent;


public class HelloSuccess extends SuccessEvent<String> {

    public HelloSuccess(Request request, String s) {
        super(request, s);
    }
}
