package gregad.event_manager.loggerstarter.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Greg Adler
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @DateTimeFormat(pattern = "YYYY/MM/DD")
    private LocalDate date;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime time;
    private String logType;
    private String resource;
    private String message;

    @Override
    public String toString() {
        return date+"-"+time+"-type:["+logType+"]-resource:["+resource+"]-message:["+message+"]\n";
    }
}
