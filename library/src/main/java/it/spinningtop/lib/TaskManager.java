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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class takes care of managing tasks.
 * Provides methods to add, remove and search for running tasks
 *
 * @author Paolo Brandi
 */
class TaskManager {

    private Map<String, List<Request<?>>> map;

    public TaskManager() {
        this.map = new HashMap<String, List<Request<?>>>();
    }

    public boolean addRequest(Request<?> request) {
        boolean added = false;
        if (request != null) {
            String key = request.getTaskName();
            List<Request<?>> requests = map.get(key);
            if (requests == null) {
                requests = new ArrayList<Request<?>>();
                map.put(key, requests);
            }
            added = requests.add(request);
        }

        return added;
    }

    public boolean removeRequest(Request<?> request) {
        boolean removed = false;
        if (request != null) {
            String key = request.getTaskName();
            List<Request<?>> requests = map.get(key);
            if (requests != null) {
                removed = requests.remove(request);
                if (requests.size() == 0) {
                    map.remove(key);
                }
            }
        }

        return removed;
    }

    public boolean containsRequest(Request<?> request) {
        boolean found = false;
        if (request != null) {
            String key = request.getTaskName();
            List<Request<?>> requests = map.get(key);
            if (requests != null) {
                for (Request<?> r : requests) {
                    if (request.equals(r)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        return found;
    }

    public Request<?> getRequest(Request<?> request) {
        Request<?> requestFound = null;
        if (request != null) {
            String key = request.getTaskName();
            List<Request<?>> requests = map.get(key);
            if (requests != null) {
                for (Request<?> r : requests) {
                    if (request.equals(r)) {
                        requestFound = r;
                        break;
                    }
                }
            }
        }

        return requestFound;
    }


    public List<Request<?>> getRequestByTask(String taskName) {
        List<Request<?>> list = null;
        if (taskName != null) {
            list = map.get(taskName);
        }

        return list != null ? list : new ArrayList<Request<?>>();
    }

}
