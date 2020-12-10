package gregad.event_manager.loggerstarter.writers.services;

import gregad.event_manager.loggerstarter.aspect.DoLogging;
import gregad.event_manager.loggerstarter.aspect.LogModel;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Greg Adler
 */
public class ExcelFileLogWriter implements LogWriter {
    
    @SneakyThrows
    @Override
    public void writeLog(LogModel log, String rootPath, DoLogging annotation) {
        String filePath=resolveFilePath(log,rootPath);
        File dir = Files.createDirectories(Paths.get(filePath)).toFile();
        File file=
                new File(dir.getAbsolutePath()+File.separatorChar+ log.getDate().getDayOfMonth()+ "_log.xlsx");

        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet( "logs from "+log.getDate());
        int lastRowNum=0;

        if (file.exists()) {
            try (FileInputStream in=new FileInputStream(file)){
                workbook = file.length()==0? new XSSFWorkbook():new XSSFWorkbook(in);
                spreadsheet = workbook.getSheet( "logs from "+log.getDate());
                lastRowNum = spreadsheet.getLastRowNum();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        XSSFRow row;
        if (lastRowNum==0){
            lastRowNum++;
            row=spreadsheet.createRow(0);
            row.createCell(0).setCellValue("date");
            row.createCell(1).setCellValue("time");
            row.createCell(2).setCellValue("type");
            row.createCell(3).setCellValue("resource");
            row.createCell(4).setCellValue("message");
        }
        row=spreadsheet.createRow(lastRowNum+1);
        row.createCell(0).setCellValue(log.getDate().format(DateTimeFormatter.ofPattern(annotation.dateFormat())));
        row.createCell(1).setCellValue(log.getTime().format(DateTimeFormatter.ofPattern(annotation.timeFormat())));
        row.createCell(2).setCellValue(log.getLogType());
        row.createCell(3).setCellValue(log.getResource());
        row.createCell(4).setCellValue(log.getMessage());

        try (FileOutputStream out = new FileOutputStream(file)){

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
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
                date.getMonthValue() ;
        return rootPath;
    }
}
