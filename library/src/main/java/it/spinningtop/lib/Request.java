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

/**
 * This class represents a request.
 * A request describes uniquely an task instance with his own parameters.
 *
 * Two request starts two different task if one of the conditions below are true:
 * - the task name is different
 * - the task name is the same but with different parameters
 * - the task name is the same with same parameters but the task is not running yet.
 *
 * It is possible to compare two request in order to know if they are equal or not. It can be useful
 * when receiving different responses and we want to know if that response is that one
 * we are actually waiting for.
 *
 * @param <DataInput> It is the type of the data we provide to the task.
 *
 * @author Paolo Brandi
 */
public class Request<DataInput> {

    private DataInput dataInput;
    private Class<? extends Task> taskClass;

    public Request(Class<? extends Task> taskClass) {
        if (taskClass == null) {
            throw new IllegalArgumentException("taskClass argument cannot be null. Task class must be provided!");
        }

        this.taskClass = taskClass;
    }

    public Request(Class<? extends Task> taskClass, DataInput dataInput) {
        this(taskClass);
        this.dataInput = dataInput;
    }

    public DataInput getData() {
        return dataInput;
    }

    public String getTaskName() {
        return taskClass.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request<?> request = (Request<?>) o;

        if (dataInput != null ? !dataInput.equals(request.dataInput) : request.dataInput != null)
            return false;
        return !(taskClass != null ? !taskClass.equals(request.taskClass) : request.taskClass != null);

    }

    @Override
    public int hashCode() {
        int result = dataInput != null ? dataInput.hashCode() : 0;
        result = 31 * result + (taskClass != null ? taskClass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "dataInput=" + dataInput +
                ", taskClass=" + taskClass +
                '}';
    }
}
