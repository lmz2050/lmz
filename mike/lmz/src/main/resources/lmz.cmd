@echo off&setlocal enabledelayedexpansion

set rpwd=%cd%
set RUN_PATH=%~dp0
cd /d %RUN_PATH%

set LPARAM=@LP@:%1,@LF@:%2,@LD@:%rpwd%,@LA@:%sa%

set JAVA_HOME="J:/Program Files/Java/jdk1.8.0_151"
set CLASS_PATH=%JAVA_HOME%/jre/lib/rt.jar;%RUN_PATH%/lib/*;.;%RUN_PATH%/bin
set JVM_OPTS=-Xms128m -Xmx256m
set JAVA_OPTS=-Dfile.encoding=UTF-8 -Djava.awt.headless=true -Djava.io.tmpdir=%RUN_PATH%/tmp -DAPPHOME=%RUN_PATH% -Dlog4j.configurationFile=%RUN_PATH%/etc/log4j2.xml
set LTIME=%date:~0,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%

echo "[%LTIME%] %JAVA_HOME%/bin/java %JVM_OPTS% %JAVA_OPTS% -cp %CLASS_PATH% cn.lmz.mike.lmz.sys.Lmz %LPARAM%"
%JAVA_HOME%"/bin/java" %JVM_OPTS% %JAVA_OPTS% -cp %CLASS_PATH% cn.lmz.mike.lmz.sys.Lmz %LPARAM%

pause