package ru.andres1m.SLogs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.andres1m.SLogs.logswriter.LogsWriter;

@Component
public class RabbitMQListener {
    private final LogsWriter logsWriter;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RabbitMQListener(LogsWriter logsWriter) {
        this.logsWriter = logsWriter;
    }

    //TODO документация
    @RabbitListener(queues = "#{@environment.getProperty('rabbitmq.SLogs_queue.name')}")
    public void receiveLogMessage(String message) {
        try {
            LoggingRequest request = objectMapper.readValue(message, LoggingRequest.class);
            logsWriter.write(request.getName(), request.getData());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}