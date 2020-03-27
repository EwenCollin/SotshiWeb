#!D:\Ewen\Projets\Coding\python\sotshiwebdisplay\venv\Scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'webscreenshot==2.9','console_scripts','webscreenshot'
__requires__ = 'webscreenshot==2.9'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('webscreenshot==2.9', 'console_scripts', 'webscreenshot')()
    )
