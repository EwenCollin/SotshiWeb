echo off
set arg1=%1
set arg2=%2

python path\to\webscreenshot.py %arg1% --window-size %arg2% --renderer-binary path\to\phantomjs.exe -o path\to\screenshots -v --ajax-max-timeouts "7000,10000"