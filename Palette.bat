@if "%DEBUG%"=="" echo off
setlocal enabledelayedexpansion

@rem ###############################################################
@rem Run the palette jar.
@rem ###############################################################
@rem optionally hide the window
if "%DEBUG%"=="" powershell -WindowStyle Hidden -Command "Write-Host hiding window"
pushd "%~dp0"
java "-Dpalette.props.file=%~dp0%~n0.properties" -jar Palette.jar
call :CHECK_ERROR %ERRORLEVEL%
exit /b %ERRORLEVEL%

:CHECK_ERROR
if not [%1]==[0] ( echo Palette Execution Failed. && pause )
exit /b %1