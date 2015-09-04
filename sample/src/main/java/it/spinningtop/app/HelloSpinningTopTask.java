package it.spinningtop.app;


import it.spinningtop.lib.events.ExceptionEvent;
import it.spinningtop.lib.events.SuccessEvent;
import it.spinningtop.lib.Task;

/**
 * Created by Paolo Brandi on 24/02/14.
 */
public class HelloSpinningTopTask extends Task<Void, String> {

    @Override
    public SuccessEvent<String> getSuccessEvent(String s) {
        return new HelloSuccess(s);
    }

    @Override
    public ExceptionEvent getExceptionEvent(Throwable throwable) {
        return new HelloException(throwable);
    }

    @Override
    public void onPrePostEvent() {

    }

    @Override
    public String doJob() {
        return "Hello Spinning Top!";
    }
}
