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

package it.spinningtop.lib.events;

import it.spinningtop.lib.Request;

/**
 * Base class for events which wrap a response
 *
 * @author Paolo Brandi
 *
 */
public class SuccessEvent<Result> implements Event {

    private Request request;
    private Result result;

    public SuccessEvent(Request request, Result result) {
        this.request = request;
        this.result = result;
    }

    @Override
    public Request getRequest() {
        return request;
    }

    public Result getResult() {
        return result;
    }

}
