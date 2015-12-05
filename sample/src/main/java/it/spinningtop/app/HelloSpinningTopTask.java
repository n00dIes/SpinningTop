package it.spinningtop.app;


import it.spinningtop.lib.Request;
import it.spinningtop.lib.SharedObject;
import it.spinningtop.lib.events.ExceptionEvent;
import it.spinningtop.lib.events.SuccessEvent;
import it.spinningtop.lib.Task;


public class HelloSpinningTopTask extends Task<Void, String> {

    public HelloSpinningTopTask(Request<Void> request, SharedObject<?> sharedObject) {
        super(request, sharedObject);
    }

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
