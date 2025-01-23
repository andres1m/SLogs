package ru.andres1m.SLogs.logswriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class LogFileManager {

    @Value("${slogs.file_logs_writer.path}")
    private String logsPath;

    public void createLogFile(String fileName){
        if(isFileExist(getFilePath(fileName))){
            throw new IllegalStateException("File already exists");
        }

        File file = new File(getFilePath(fileName));
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLogInFile(String fileName, String data){
        if (!isFileExist(fileName)) {
            createLogFile(fileName);
        }

        File file = new File(getFilePath(fileName));

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing log to file: " + file.getPath(), e);
        }
    }

    public boolean isFileExist(String fileName){
        return new File(getFilePath(fileName)).exists();
    }

    private String getFilePath(String fileName){
        return logsPath + "/" + fileName + ".log";
    }
}