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

import it.spinningtop.lib.bus.Bus;
import it.spinningtop.lib.exception.TaskCreationException;

/**
 * This class takes care of the creation of task instances using reflection.
 *
 * @author Paolo Brandi
 */
public class TaskFactory {

    public static Task<?,?> create(Request<?> request, SharedObject sharedObject) {
        try {
            return (Task) Class.forName(request.getTaskName())
                    .getConstructor(new Class[]{Request.class, Bus.class, TaskManager.class, SharedObject.class})
                    .newInstance(request, sharedObject);
        } catch (Exception e) {
            throw new TaskCreationException("Cannot create the task instance for this request " + request.toString() + ". " +
                    "Please check if the Task class exists or the request is valid.", e);
        }
    }
}
