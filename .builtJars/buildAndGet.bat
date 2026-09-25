@echo off

cd /D %~dp0
if exist *.jar del /q *.jar >nul

echo Building...
cd /D ../
powershell -c "./gradlew :fabric:build"
powershell -c "./gradlew :forge:build"
powershell -c "./gradlew :neoforge:build"

cd /D %~dp0
echo Moving files...
move ..\fabric\build\libs\*.jar . >nul
move ..\forge\build\libs\*.jar . >nul
move ..\neoforge\build\libs\*.jar . >nul

echo Deleting files...
if exist *dev-shadow.jar del /q *dev-shadow.jar >nul
if exist *slim.jar del /q *slim.jar >nul
if exist *sources.jar del /q *sources.jar >nul
if exist *fat.jar del /q *fat.jar >nul

echo Done.
pause >nul