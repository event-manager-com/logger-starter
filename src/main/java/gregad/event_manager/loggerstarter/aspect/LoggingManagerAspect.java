package gregad.event_manager.loggerstarter.aspect;

import gregad.event_manager.loggerstarter.cleaner.Cleaner;
import gregad.event_manager.loggerstarter.writers.interfaces.LogWriter;
import gregad.event_manager.loggerstarter.writers.properties.LogFileProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static gregad.event_manager.loggerstarter.aspect.Constants.ERROR;
import static gregad.event_manager.loggerstarter.aspect.Constants.INFO;

/**
 * @author Greg Adler
 */
@Aspect
public class LoggingManagerAspect {
    
    @Autowired
    private List<LogWriter>writers;
    @Autowired
    private LogModelFactory logModelFactory;
    @Autowired
    private Cleaner cleaner;
    @Autowired
    private LogFileProperties logFileProperties;
    
    private ConcurrentHashMap<String,Integer> rootsToClean=new ConcurrentHashMap<>();
    
    @Scheduled(cron = "#{loggingManagerAspect.getCronExpresion()}")
    public void doCleanLog(){
        rootsToClean.forEach((key, value) -> cleaner.clean(key, value));
    }
    public String getCronExpresion(){
        String exp=
               logFileProperties.getCronExpresion() ==null?
                        "0 0 3 * * *":
                        logFileProperties.getCronExpresion();
        System.out.println(exp);
        return exp;
    }
    

    @Pointcut(value = "within(@gregad.event_manager.loggerstarter.aspect.DoLogging *)")
    public void beanAnnotatedWithDoLogging() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("publicMethod() && beanAnnotatedWithDoLogging()")
    public void publicMethodInsideAClassMarkedWithDoLogging() {}
    
    @Before("publicMethodInsideAClassMarkedWithDoLogging()")
    public void sendInfo(JoinPoint point){
        DoLogging annotation = point.getThis().getClass().getSuperclass().getAnnotation(DoLogging.class);
        LogModel logModel = logModelFactory.generateLog(point,INFO,"method args:");
        writers.forEach(w->w.writeLog(logModel,annotation.rootPath(),annotation));
        rootsToClean.putIfAbsent(annotation.rootPath(),annotation.cleanLogDaysAgo());
    }

    @AfterThrowing(pointcut ="publicMethodInsideAClassMarkedWithDoLogging()",throwing = "ex")
    public void sendError(JoinPoint  point, Throwable ex){
        DoLogging annotation = point.getThis().getClass().getSuperclass().getAnnotation(DoLogging.class);
        LogModel logModel = logModelFactory.generateLog(point,ERROR,ex.getMessage());
        writers.forEach(w->w.writeLog(logModel,annotation.rootPath(),annotation));
        rootsToClean.putIfAbsent(annotation.rootPath(),annotation.cleanLogDaysAgo());
    }
}
