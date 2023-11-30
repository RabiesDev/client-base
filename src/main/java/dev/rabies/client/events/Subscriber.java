package dev.rabies.client.events;

import dev.rabies.client.events.interfaces.Handler;
import dev.rabies.client.events.interfaces.Linker;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class Subscriber {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final Map<Type, List<CallSite<Event>>> callSiteMap = new HashMap<>();
    private static final Map<Type, List<Pair<Timings[], Handler<Event>>>> listenerCache = new HashMap<>();

    public static void register(Object subscriber) {
        try {
            for (Field field : subscriber.getClass().getDeclaredFields()) {
                Linker annotation = field.getAnnotation(Linker.class);
                if (annotation != null) {
                    Type eventType = ((ParameterizedType) (field.getGenericType())).getActualTypeArguments()[0];
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }

                    try {
                        Handler<Event> handler = (Handler<Event>) LOOKUP.unreflectGetter(field).invokeWithArguments(subscriber);
                        CallSite<Event> callSite = new CallSite<>(subscriber, handler, annotation.priority(), annotation.filter());

                        if (callSiteMap.containsKey(eventType)) {
                            callSiteMap.get(eventType).add(callSite);
                            callSiteMap.get(eventType).sort(Comparator.comparingInt(event -> event.priority.ordinal()));
                        } else {
                            List<CallSite<Event>> callSites = new ArrayList<>(1);
                            callSites.add(callSite);
                            callSiteMap.put(eventType, callSites);
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }

            populateListenerCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregister(Object subscriber) {
        for (List<CallSite<Event>> callSites : callSiteMap.values()) {
            callSites.removeIf(eventCallSite -> eventCallSite.owner.equals(subscriber));
        }
        populateListenerCache();
    }

    private static void populateListenerCache() {
        for (Type type : callSiteMap.keySet()) {
            List<CallSite<Event>> callSites = callSiteMap.get(type);
            List<Pair<Timings[], Handler<Event>>> listeners = new ArrayList<>(callSites.size());
            for (CallSite<Event> callSite : callSites) {
                listeners.add(new ImmutablePair<>(callSite.filter, callSite.handler));
            }
            listenerCache.put(type, listeners);
        }
    }

    public static void dispatchEvent(Event event) {
        for (Pair<Timings[], Handler<Event>> listener : listenerCache.getOrDefault(event.getClass(), Collections.emptyList())) {
            if (Arrays.asList(listener.getKey()).contains(event.timing)) {
                listener.getValue().invoke(event);
            }
        }
    }

    private static class CallSite<Event> {
        private final Object owner;
        private final Handler<Event> handler;
        private final Priorities priority;
        private final Timings[] filter;

        public CallSite(Object owner, Handler<Event> handler, Priorities priority, Timings[] filter) {
            this.owner = owner;
            this.handler = handler;
            this.priority = priority;
            this.filter = filter;
        }
    }
}
