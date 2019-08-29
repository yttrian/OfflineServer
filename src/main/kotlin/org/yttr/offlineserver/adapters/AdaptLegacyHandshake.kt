package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.PacketType.Sender
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

// TODO: Fix failing to properly adapt legacy handshake packets
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