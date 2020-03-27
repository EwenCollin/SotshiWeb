# SotshiWeb
A web display in Minecraft using a plugin on a server.

## Installation

This is not a beginners installation, I am not precise enough and it may not work.

(Works on windows, needs a bit of changes for Linux, Ubuntu or Mac)
First you need a spigot server running on 1.15.2 (latest version if possible). You have a basic setup of it in the "server_setup" directory, just download it.
Then you have to install python running on the latest version (I recommand 3.8 as it works fine with this version).
From now, you have to use a VirutalEnv, I recommand venv. I used PyCharm Community Edition VirtualEnvironment and it works perfectly.
Then you have to run on your virtual env "pip install webscreenshot" (https://pypi.org/project/webscreenshot/).
You can now add the complete path to "webscreenshot.py" in the "webscreenshot.bat" file of your server (copied from server_setup). In PyCharm, you will find it in the "site-packages" directory of your VirtualEnvironment.

Then you have to download "phantomjs.exe" (https://phantomjs.org/download.html) and put the .exe file somewhere on your computer. Then you can add its complete path in the "webscreenshot.bat" file of your server (copied from server_setup).

And then that's it.

## Usage

Command is : /web save \<name\> \<url\>
Then put item frames on a wall designed on X axis of minecraft (16x9) and place a sign on a corner but one block bellow and type on it ".\<name\>".
It should work, check the console if nothing happens.
