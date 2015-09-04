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

package it.spinningtop.app;


import it.spinningtop.lib.events.ExceptionEvent;
import it.spinningtop.lib.events.SuccessEvent;
import it.spinningtop.lib.Task;

/**
 * Created by Paolo Brandi on 24/02/14.
 */
public class LongHelloSpinningTopTask extends Task<Void, String> {


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
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello Long Spinning Top!";
    }
}
