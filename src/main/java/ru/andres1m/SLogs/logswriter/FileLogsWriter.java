package ru.andres1m.SLogs.logswriter;

public class FileLogsWriter implements LogsWriter{
    private final String name;
    private final LogFileManager fileManager;

    public FileLogsWriter(String name) {
        this.name = name;
        this.fileManager = new LogFileManager("andres1m/logspath"); //TODO из конфигурации
    }

    @Override
    public void write(String data) {
        fileManager.writeLogInFile(name, data);
    }
}