package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

class AdaptPing(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Status.Client.START, PacketType.Status.Client.PING)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        event.isCancelled = true
    }
}