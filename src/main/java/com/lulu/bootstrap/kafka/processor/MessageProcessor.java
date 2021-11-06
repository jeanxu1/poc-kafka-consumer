package com.lulu.bootstrap.kafka.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessageProcessor {

        String AVRO = "avro";
        String PLAIN = "plain";
        String AVRO_GENERIC = "avro_generic";

        @Input("avro")
        SubscribableChannel avro();

        @Input("avro_generic")
        SubscribableChannel avro_generic();

        @Input("plain")
        SubscribableChannel plain();
}
