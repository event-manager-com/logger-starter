package gregad.event_manager.loggerstarter.writers.services;

import gregad.event_manager.loggerstarter.aspect.DoLogging;
import gregad.event_manager.loggerstarter.aspect.LogModel;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author Greg Adler
 */
public class TextFileLogWriter implements LogWriter {
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log,String rootPath, DoLogging annotation) {
        String filePath=resolveFilePath(log,rootPath);
        File dir = Files.createDirectories(Paths.get(filePath)).toFile();
        File file= 
                new File(dir.getAbsolutePath()+File.separatorChar+ log.getDate().getDayOfMonth()+ "_log.txt");
        try(FileWriter writer =new FileWriter(file,true)) {
            writer.append(log.asString(annotation.dateFormat(),annotation.timeFormat()));
        }catch (IOException e){
            //todo ask Evgeny 
        }
    }

    private String resolveFilePath(LogModel log ,String rootPath) {
        LocalDate date = log.getDate();
        rootPath=rootPath+ 
                File.separatorChar+
                "logs"+
                File.separatorChar+
                date.getYear()+
                File.separatorChar+
                date.getMonthValue();
//        if (System.getProperty("os.name").toLowerCase().contains("win")){
//            path=path.replace('/','\\');
//        }
        return rootPath;
    }
}
