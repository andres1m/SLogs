package ru.andres1m.SLogs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoggingRequest {
    private String name;
    private String data;
}
