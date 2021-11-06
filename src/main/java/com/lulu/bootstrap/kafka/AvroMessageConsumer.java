package com.lulu.bootstrap.kafka;

import com.lulu.bootstrap.kafka.avro.schema.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import static com.lulu.bootstrap.kafka.processor.MessageProcessor.*;


@Service
@Slf4j
public class AvroMessageConsumer {

    @StreamListener(AVRO)
    public void consumeProductAvro(Product product) {
        log.info("Received AVRO message: {}", product);
    }

    @StreamListener(AVRO_GENERIC)
    public void consumeProductAvroGeneric(GenericRecord productGeneric) {
        log.info("Received Generic AVRO message: {}", productGeneric);
    }

    @StreamListener(PLAIN)
    public void consumeProductPlain(String product) {
        log.info("Received plain message: {}", product);
    }

}
