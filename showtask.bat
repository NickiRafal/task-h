@echo off
rem Uruchom skrypt runcrud.bat
call runcrud.bat

rem Sprawdź kod wyjścia skryptu runcrud.bat
if %errorlevel% neq 0 (
    echo Błąd podczas uruchamiania skryptu runcrud.bat. Zakończono z kodem błędu: %errorlevel%
    pause
    exit /b %errorlevel%
)

rem Uruchom przeglądarkę Google Chrome
start chrome "http://localhost:8080/crud/v1/tasks"
