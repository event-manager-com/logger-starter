# logger-starter
operation manual
  1. add dependency to your pom.xml
  
         <groupId>gregad.event_manager</groupId>
         <artifactId>logger-starter</artifactId>
         <version>0.0.1-SNAPSHOT</version>
         
  2. put an annotation @DoLogging over the class whose public methods you want to log
     currently available only to log levels, info before method start and error on throwed exceptions
  3. in annotation you have some parameters:
  
     a. String rootPath()can be used to navigate to your log folder default "."(root of project)
     
     b. int cleanLogDaysAgo()can be used for configure you cleaner(remove old folder) default 30(days)
     
  4. add to your application.properties file logging.file.format property
  
      a.logging.file.format.text=boolean . if you put true to this property, will write logs to file .txt
      
      b.logging.file.format.excel=boolean . if you put true to this property, will write logs to file .xlsx
