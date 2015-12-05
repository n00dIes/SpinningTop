/*
 * Copyright (C) 2014 Paolo Brandi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.spinningtop.lib;

import android.content.Context;
import android.util.Log;

import it.spinningtop.lib.bus.Bus;
import it.spinningtop.lib.events.ExceptionEvent;
import it.spinningtop.lib.events.SuccessEvent;

/**
 * Task produces results and dispatches them to subscribers with events
 * Every background task must extend this abstract class in order to do a job.
 *
 * @author Paolo Brandi
 */
public abstract class Task<DataRequest, Result> extends AsyncTask<Void, Void, Result> implements Job<Result> {

    private static final String TAG = Task.class.getName();

    private SpinningTop spinningTop;
    private Request<DataRequest> request;
    private SharedObject<?> sharedObject;

    private Throwable throwable;

    public Task(Request<DataRequest> request, SharedObject<?> sharedObject) {
        this.request = request;
        this.sharedObject = sharedObject;
        this.spinningTop = SpinningTop.getInstance();
    }

    public abstract SuccessEvent<Result> getSuccessEvent(Result result);

    public abstract ExceptionEvent getExceptionEvent(Throwable throwable);

    public abstract void onPrePostEvent();

    protected Request<DataRequest> getRequest() {
        return this.request;
    }

    protected SharedObject<?> getSharedObject() {
        return sharedObject;
    }

    protected Bus getBus() {
        return spinningTop.getBus();
    }

    protected Context getContext() {
        return spinningTop.getContext();
    }

    @Override
    protected Result doInBackground() {
        Result result = null;
        try {
            result = doJob();
        } catch (Throwable t) {
            this.throwable = t;
        } finally {
            onPrePostEvent();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (!hasException()) {
            postSuccessEvent(getSuccessEvent(result));
        } else {
            Log.e(TAG, "Exception while task is executing for request " + request.toString(), throwable);
            postExceptionEvent(getExceptionEvent(throwable));
        }

        spinningTop.unscheduleRequest(request);
    }

    private boolean hasException() {
        return throwable != null;
    }

    private void postSuccessEvent(SuccessEvent<Result> successEvent) {
        if (successEvent != null) {
            spinningTop.getBus().post(successEvent);
        }
    }

    private void postExceptionEvent(ExceptionEvent exceptionEvent) {
        if (exceptionEvent != null) {
            spinningTop.getBus().post(exceptionEvent);
        }
    }

}

