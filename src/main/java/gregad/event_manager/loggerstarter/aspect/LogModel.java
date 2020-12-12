package gregad.event_manager.loggerstarter.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Greg Adler
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private LocalDate date;
    private LocalTime time;
    private String logType;
    private String resource;
    private String message;
    
    public String asString(String dateFormat, String timeFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat))+"-"+time.format(DateTimeFormatter.ofPattern(timeFormat))+"-type:["+logType+"]-resource:["+resource+"]-message:["+message+"]\n";
    }
}
