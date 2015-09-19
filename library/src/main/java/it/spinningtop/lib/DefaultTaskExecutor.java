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

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Default task executor for Spinning Top.
 * It is possible to create your custom executor just implementing the {@link Executor} interface
 * and providing your thread pool configuration
 *
 * @author Paolo Brandi
 */
public class DefaultTaskExecutor implements Executor {

    /**
     * the number of threads to keep in the pool, even if they are idle
     **/
    static final int CORE_POOL_SIZE = 5;
    /**
     * the maximum number of threads to allow in the pool
     **/
    static final int MAX_POOL_SIZE = 10;
    /**
     * when the number of threads is greater than the core, this is the maximum time (in seconds) that excess idle threads will wait for new tasks before terminating
     **/
    static final int KEEP_ALIVE_TIME = 60;
    /**
     * the maximum number of tasks to allow in the queue
     **/
    static final int MAX_QUEUE_SIZE = 300;

    private ThreadPoolExecutor thread_pool_executor;

    public DefaultTaskExecutor() {
        thread_pool_executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(MAX_QUEUE_SIZE),
                Executors.defaultThreadFactory());
    }


    @Override
    public void execute(Task<?, ?> task) {
        task.executeOnExecutor(thread_pool_executor);
    }

}
