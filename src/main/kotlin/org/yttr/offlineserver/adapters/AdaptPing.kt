package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * For additional safety, we block all client status packets from coming in, in addition to ignoring the Handshake.
 *
 * https://wiki.vg/Server_List_Ping
 */
class AdaptPing(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Status.Client.START, PacketType.Status.Client.PING)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        event.isCancelled = true
    }
}