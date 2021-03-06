package io.benwilcock.productcommand;

import io.benwilcock.productcommand.commands.AddProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by benwilcock on 30/09/2016.
 */

@RestController
public class PostOneController {

    Logger logger = LoggerFactory.getLogger(PostOneController.class);

    @Autowired
    CommandGateway commandGateway;

    @Transactional
    @RequestMapping("/postone")
    void injectCreateEvent() {
        String guid = UUID.randomUUID().toString();
        logger.info("Creating test AddProductCommand: {}", guid);
        AddProductCommand command = new AddProductCommand(guid, "Dummy Product " + guid);
        commandGateway.sendAndWait(command);
        logger.info("Sent AddProductCommand: {}", command);
    }
}
