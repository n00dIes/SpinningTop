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

package it.spinningtop.lib.exception;

/**
 * RuntimException thrown when SpinningTop is not able to create a Task
 * Usually it can happen if the class name does not exist or the
 *
 * @author Paolo Brandi
 */
public class TaskCreationException extends RuntimeException {

    public TaskCreationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
