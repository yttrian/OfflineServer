![GitHub](https://img.shields.io/github/license/yttrian/OfflineServer)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/yttrian/OfflineServer/Java%20CI/master)
[![Download](https://img.shields.io/badge/CurseForge-download-purple)](https://www.curseforge.com/minecraft/bukkit-plugins/offlineserver)

# OfflineServer

Makes your server appear offline in server listings,
and act like it's offline if you have a whitelist and someone not on it tries to join.
  
Currently works against clients running 1.6 and up. 
Older clients will still detect the server as online, but this is being worked on.

## How does it work?

Using ProtocolLib, OfflineServer intercepts server list info packets and makes sure 
they are never processed by the server. This way the server appears to be offline,
but in reality it's actually online.

Furthermore, if you have a whitelist enabled, the whitelist kick message will be replaced
with the appropriate error that would normally be shown for a server that is offline.

This also applies to outdated client/server warning messages.

## Credits

This application uses open source components. 
You can find the source code of their open source projects along with license information below.

Project: Kotlin https://github.com/JetBrains/kotlin  
Copyright (c) 2019, JetBrains  
License (Apache License v2.0) https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt

Project: Spigot-API https://hub.spigotmc.org/stash/projects/SPIGOT  
Copyright (c) 2019, md_5  
License (GNU General Public License v3.0) https://github.com/SpigotMC/Spigot-API/blob/master/LICENCE.txt

Project: ProtocolLib https://github.com/dmulloy2/ProtocolLib/  
Copyright (c) 2019, dmulloy2  
License (GNU General Public License v2.0) https://github.com/dmulloy2/ProtocolLib/blob/gradle/License.txt
