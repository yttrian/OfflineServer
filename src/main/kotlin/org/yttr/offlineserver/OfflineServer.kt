package org.yttr.offlineserver

import com.comphenix.protocol.ProtocolLibrary
import org.bukkit.plugin.java.JavaPlugin
import org.yttr.offlineserver.adapters.*

class OfflineServer : JavaPlugin() {
    override fun onEnable() {
        // Register ProtocolLib listeners
        val protocolManager = ProtocolLibrary.getProtocolManager()
        val packetListeners = listOf(
            AdaptHandshake(this), AdaptLegacyHandshake(this),
            AdaptPing(this), AdaptPong(this), AdaptLogin(this))

        packetListeners.forEach { listener ->
            protocolManager.addPacketListener(listener)
        }

        // Report completion of initialization
        logger.info("This server is now cloaked!")
    }

    override fun onDisable() {
        logger.info("This server is no longer cloaked!")
    }
}