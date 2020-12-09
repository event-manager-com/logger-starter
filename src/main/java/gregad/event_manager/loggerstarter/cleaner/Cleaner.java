package gregad.event_manager.loggerstarter.cleaner;


import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author Greg Adler
 */
public class Cleaner {
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    

    public void clean(String rootPath, int daysAgo){
         LocalDate cleanFromDate = LocalDate.now().minusDays(daysAgo);
         File root = Paths.get(rootPath).toFile();
         findAndRemove(root,cleanFromDate,YEAR);
    }

    private void findAndRemove(File root,LocalDate date,String dirType ) {
        int actual=0;
        if (dirType.equals(YEAR)){
            actual=date.getYear();
            iterateDirectory(root,date,dirType,actual);
        }
        else if (dirType.equals(MONTH)){
            actual=date.getMonthValue();
            iterateDirectory(root,date,dirType,actual);
        }else {
            final File[] files = root.listFiles();
            for (File logFile : files) {
                int day = Integer.parseInt(logFile.getName().split("_")[0]);
                if (day<date.getDayOfMonth()){
                    logFile.deleteOnExit();
                }
            }
        }
        
    }

    @SneakyThrows
    private void iterateDirectory(File root, LocalDate date, String dirType, int actual) {
        File[] files = root.listFiles();
        for (File child : files) {
            String name = child.getName();
            if (name.matches("\\d+")){
                int num = Integer.parseInt(name);
                if (num<actual){
                    removeDirectory(child);
                    child.delete();
                }else {
                    String nextType=dirType.equals(YEAR)?MONTH:dirType.equals(MONTH)?DAY:null;
                    findAndRemove(child,date,nextType);
                }
            }
        }
    }

    @SneakyThrows
    private void removeDirectory(File child) {
         File[] files = child.listFiles();
        for (File file : files) {
            if (file.isDirectory()){
                removeDirectory(file);
                file.delete();
            }else {
                file.delete();
            }
        }
    }
}
