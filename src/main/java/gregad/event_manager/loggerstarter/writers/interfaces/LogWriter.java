package gregad.event_manager.loggerstarter.writers.interfaces;

import gregad.event_manager.loggerstarter.aspect.DoLogging;
import gregad.event_manager.loggerstarter.aspect.LogModel;

/**
 * @author Greg Adler
 */
public interface LogWriter {
    void writeLog(LogModel log, String rootPath , DoLogging annotation);
}
