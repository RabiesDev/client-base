package dev.rabies.client.events.interfaces;

import dev.rabies.client.events.Priorities;
import dev.rabies.client.events.Timings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Linker {
    Priorities priority() default Priorities.MEDIUM;

    Timings[] filter() default {
            Timings.PRE,
            Timings.POST
    };
}
