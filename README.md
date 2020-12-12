# logger-starter
operation manual
  1. Add dependency to your pom.xml
  
         <groupId>gregad.event_manager</groupId>
         <artifactId>logger-starter</artifactId>
         <version>0.0.1-SNAPSHOT</version>
         
  2. Put an annotation @DoLogging over the class whose public methods you want to log
     currently available only two log levels, info before method start and error on throwed exceptions.
     
  3. annotation contains some parameters:
  
     a. String rootPath()-can be used to navigate to your log folder default "."(root of project)
     
     b. int cleanLogDaysAgo()-can be used for configure you cleaner(remove old folder) default 30(days)
     
     c. String dateFormat() default "YYYY/MM/DD"
     
     d. String timeFormat() default "hh:mm:ss"
     
  4. Add to your application.properties file logging.file.format property
  
      a.logging.file.format.text=boolean . if you put true to this property, will write logs to file .txt  
      
      b.logging.file.format.excel=boolean . if you put true to this property, will write logs to file .xlsx
      
      c.logging.file.format.kafka=boolean . if you put true to this property, will send logs to cloud karafka topic, you need also to add some properties(see later in 5)
      
      d.logging.file.format.cron-expresion=String if you want to configure time of scheduled clean old logs mechanism. default "0 0 3 * * *"
      
  5. If you want to send loggs with cloud karafka? add to your application.properties file     
     
      a.spring.kafka.bootstrap-servers=list of your rockets. for example{rocket-01.srvs.cloudkafka.com:9094,rocket-02.srvs.cloudkafka.com:9094,rocket-      03.srvs.cloudkafka.com:9094}
      
      b.spring.kafka.properties.security.protocol=for example{SASL_SSL}
      
      c.spring.kafka.properties.sasl.mechanism=for example{SCRAM-SHA-256}
      
      d.spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="{your user name}" password="{your password}";
      
      e.spring.cloud.stream.bindings.exceptionlog.destination=your destination topic. for example{flqd4b-exceptionlogdatastream}
