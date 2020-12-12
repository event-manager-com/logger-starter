package gregad.event_manager.loggerstarter.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

/**
 * @author Greg Adler
 */
public class LogModelFactory {
    @Value("${spring.application.name}")
    private String serviceName;
    
    public LogModel generateLog(JoinPoint point, String logType, String messagePrefix){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String resource=serviceName+"->"+getResource(point);
        String message=logType.equals(Constants.INFO)?getMessage(point,messagePrefix):messagePrefix;
        return new LogModel(date,time,logType,resource,message);
    }


    private String getMessage(JoinPoint point, String messagePrefix) {
        CodeSignature codeSignature = (CodeSignature) point.getSignature();
        Class[] parameterTypes = codeSignature.getParameterTypes();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = point.getArgs();
        StringBuilder res=new StringBuilder(messagePrefix);
        for (int i = 0; i < args.length; i++) {
            res.append(parameterTypes[i]+" "+parameterNames[i]+" "+args[i]+" ");
        }
        return res.toString();
    }

    private String getResource(JoinPoint point) {
        String className = point.getThis().getClass().getSimpleName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getMethod().getName();
        return className+"."+methodName+"()";
    }
}
