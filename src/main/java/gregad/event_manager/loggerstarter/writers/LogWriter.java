package gregad.event_manager.loggerstarter.writers;

import gregad.event_manager.loggerstarter.aspect.LogModel;

/**
 * @author Greg Adler
 */
public interface LogWriter {
    void writeLog(LogModel log,String rootPath);
}
