package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.PacketType.Protocol
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin
import org.yttr.offlineserver.forgery.ForgeConnectionError

class AdaptHandshake(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Handshake.Client.SET_PROTOCOL)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        val packet = event.packet

        // Next State field
        event.isCancelled = when (packet.protocols.read(0)) {
            Protocol.STATUS -> true
            Protocol.LOGIN -> !isValidLogin(packet)
            else -> false
        }
    }

    private fun isValidLogin(packet: PacketContainer): Boolean {
        val protocolVersion = packet.integers.read(0)
        val errorMessage = ForgeConnectionError.getForProtocolVersion(protocolVersion)

        plugin.logger.info(errorMessage.message)

        return true
    }
}