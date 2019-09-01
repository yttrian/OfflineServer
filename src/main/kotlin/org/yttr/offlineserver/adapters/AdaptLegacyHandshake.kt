package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.PacketType.Sender
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

// TODO: Fix failing to properly adapt legacy handshake packets
/**
 * Legacy clients (1.6 and below) use a different method of retrieving server status which we also need to account for
 * to make sure the server is also hidden to them as well.
 *
 * The packet is identified by FE, which we need to intercept to prevent the server from responding.
 *
 * https://wiki.vg/Server_List_Ping
 */
class AdaptLegacyHandshake(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    // https://wiki.vg/Server_List_Ping#1.6
    PacketType.newLegacy(Sender.CLIENT, 0xFE)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        event.isCancelled = true
    }
}