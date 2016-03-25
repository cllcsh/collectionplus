@echo off

title Building the ictcommon...

cls

@echo.
@echo Building the Spring Framework...
@echo.

"%JAVA_HOME%/bin/java" -cp ../common/lib/ant/ant.jar;../common/lib/ant/ant-launcher.jar;../common/lib/ant/ant-trax.jar;../common/lib/ant/ant-junit.jar;../common/lib/junit/junit-3.8.2.jar;../common/lib/clover/clover.jar;"%JAVA_HOME%/lib/tools.jar" org.apache.tools.ant.Main %1

@echo.
pause