package org.yttr.offlineserver

import com.comphenix.protocol.ProtocolLibrary
import org.bukkit.plugin.java.JavaPlugin
import org.yttr.offlineserver.adapters.AdaptPing
import org.yttr.offlineserver.adapters.AdaptServerInfo

class OfflineServer : JavaPlugin() {
    override fun onEnable() {
        logger.info("OfflineServer enabled!")

        val protocolManager = ProtocolLibrary.getProtocolManager()
        protocolManager.addPacketListener(AdaptPing(this))
        protocolManager.addPacketListener(AdaptServerInfo(this))
    }

    override fun onDisable() {
        logger.info("OfflineServer disabled!")
    }
}