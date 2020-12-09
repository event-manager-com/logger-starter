package gregad.event_manager.loggerstarter.writers;

import gregad.event_manager.loggerstarter.aspect.LogModel;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author Greg Adler
 */
public class TextFileLogWriter implements LogWriter {
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log,String rootPath) {
        String filePath=resolveFilePath(log,rootPath);
        File file = Files.createDirectories(Paths.get(filePath)).toFile();
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(log);
        }catch (IOException e){
            //todo ask Evgeny 
        }
    }

    private String resolveFilePath(LogModel log ,String rootPath) {
        LocalDate date = log.getDate();
        rootPath=rootPath+ 
                File.separatorChar+
                date.getYear()+
                File.separatorChar+
                date.getMonthValue() +
                File.separatorChar+
                date.getDayOfMonth()+ "_log.txt";
//        if (System.getProperty("os.name").toLowerCase().contains("win")){
//            path=path.replace('/','\\');
//        }
        return rootPath;
    }
}
