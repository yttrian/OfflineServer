package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

class AdaptJoin(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    PacketType.Login.Client.START
) {
    override fun onPacketSending(event: PacketEvent) {
        val server = getPlugin().server
        val player = event.player
        val whitelistEnabled = server.hasWhitelist()
        val playerWhitelisted = server.whitelistedPlayers.contains(player)

        event.isCancelled = whitelistEnabled && !playerWhitelisted
    }
}