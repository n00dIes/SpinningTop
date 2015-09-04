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

import java.util.List;

import it.spinningtop.lib.bus.Bus;
import it.spinningtop.lib.bus.DefaultBusImpl;
import it.spinningtop.lib.events.EventReceiver;
import it.spinningtop.lib.exception.SpinningTopNotInitialiazedException;

/**
 * Master class
 *
 * @author Paolo Brandi
 */
public class SpinningTop {

    private static final String TAG = SpinningTop.class.getName();

    private Context context;
    private TaskManager taskManager;
    private Executor exectuor;
    private Bus bus;
    private SharedObject sharedObject;

    private static SpinningTop instance;

    /**
     * Init default Spinning Top
     *
     * @return the spinning top instance
     */
    public static SpinningTop init(Context context) {
        if (instance == null) {
            instance = new Builder(context).build();
        }

        return instance;
    }

    /**
     * Return the current spinning top instance
     *
     * @return instance, or null if init() has not been called
     */
    public static SpinningTop getInstance() {
        if (instance == null) {
            throw new SpinningTopNotInitialiazedException("You should call init() or build your SpinningTop configuration before getting an instance!");
        }

        return instance;
    }

    private SpinningTop(Context context, Executor exectuor, Bus bus, SharedObject sharedObject) {
        this.context = context;
        this.taskManager = new TaskManager();
        this.exectuor = exectuor;
        this.bus = bus;
        this.sharedObject = sharedObject;
    }


    public void submitRequest(Request<?> request) {
        if (!taskManager.containsRequest(request)) {
            taskManager.addRequest(request);
            Task<?, ?> task = TaskFactory.create(context, request, bus, taskManager, sharedObject);
            exectuor.execute(task);
        } else {
            Log.i(TAG, "Task for request " + request.toString() + " is already running");
        }
    }

    protected void unscheduleRequest(Request<?> request) {
        taskManager.removeRequest(request);
    }

    public boolean isRequestRunning(Request<?> request) {
        return taskManager.containsRequest(request);
    }

    public boolean isTaskRunning(Class<?> taskClass) {
        List<Request<?>> requests = taskManager.getRequestByTask(taskClass.getName());
        return requests != null && !requests.isEmpty();
    }

    /**
     * Register a subscriber
     *
     * @param receiver
     */
    public void register(EventReceiver receiver) {
        bus.register(receiver);
    }

    /**
     * Unregister a subscriber
     *
     * @param receiver
     */
    public void unregister(EventReceiver receiver) {
        bus.unregister(receiver);
    }

    /**
     * Return the current bus instance
     *
     * @return bus
     */
    protected Bus getBus() {
        return bus;
    }

    protected Context getContext() {
        return context;
    }

    public static class Builder {

        private Context context;
        private Executor exectuor;
        private Bus bus;
        private SharedObject sharedObject;

        public Builder(Context context) {
            this.context = context;
        }

        public void setExectuor(Executor exectuor) {
            this.exectuor = exectuor;
        }

        public void setBus(Bus bus) {
            this.bus = bus;
        }

        public void setSharedObject(SharedObject sharedObject) {
            this.sharedObject = sharedObject;
        }

        public SpinningTop build() {
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }

            if (bus == null) {
                bus = DefaultBusImpl.getDefaultBus();
            }

            if (exectuor == null) {
                exectuor = new DefaultTaskExecutor();
            }

            if (sharedObject == null) {
                sharedObject = null;
            }

            instance = new SpinningTop(context, exectuor, bus, sharedObject);

            return instance;
        }
    }
}
