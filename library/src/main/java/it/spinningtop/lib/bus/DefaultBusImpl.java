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

package it.spinningtop.lib.bus;


import de.greenrobot.event.EventBus;
import it.spinningtop.lib.events.Event;
import it.spinningtop.lib.events.EventReceiver;

public class DefaultBusImpl implements Bus {

    private static DefaultBusImpl defaultBus;

    private EventBus eventBus;

    private DefaultBusImpl() {
        this.eventBus = new EventBus();
    }

    public static Bus getDefaultBus() {
        if(defaultBus == null) {
            defaultBus = new DefaultBusImpl();
        }

        return defaultBus;
    }

    @Override
    public void register(EventReceiver receiver) {
        eventBus.register(receiver);
    }

    @Override
    public void unregister(EventReceiver receiver) {
        eventBus.unregister(receiver);
    }

    @Override
    public void post(Event event) {
        eventBus.post(event);
    }
}
