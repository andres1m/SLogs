package ru.andres1m.SLogs.logswriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileManager {
    private final String logsPath;

    public LogFileManager(String logsPath){
        this.logsPath = logsPath;
    }

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
        if(!isFileExist(getFilePath(fileName))){
            throw new IllegalStateException("File not exists");
        }

        File file = new File(getFilePath(fileName));
        try(FileWriter writer = new FileWriter(file, true)){
            writer.write(data);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isFileExist(String fileName){
        return new File(getFilePath(fileName)).exists();
    }

    private String getFilePath(String fileName){
        return logsPath + "/" + fileName + ".log";
    }
}