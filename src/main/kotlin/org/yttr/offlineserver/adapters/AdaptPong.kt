package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * For additional safety, we block all server status packets from going out, in addition to ignoring the Handshake.
 *
 * https://wiki.vg/Server_List_Ping
 */
class AdaptPong(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Status.Server.SERVER_INFO, PacketType.Status.Server.PONG)
) {
    override fun onPacketSending(event: PacketEvent) {
        event.isCancelled = true
    }
}