package ru.andres1m.SLogs.logswriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileLogsWriter implements LogsWriter{
    private final LogFileManager fileManager;

    @Autowired
    public FileLogsWriter(LogFileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void write(String serviceName, String data) {
        if(!fileManager.isFileExist(serviceName)){
            fileManager.createLogFile(serviceName);
        }

        fileManager.writeLogInFile(serviceName, data);
    }
}