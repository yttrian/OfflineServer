package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

class AdaptLogin(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Login.Client.START)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        val username = event.packet.gameProfiles.read(0).name

        // For now, we will ignore the deprecation warning. Ideally, we should be checking users by UUID,
        // but that is not provided in the PacketLoginInStart (https://wiki.vg/Protocol#Login_Start) so
        // we need to check via username. It is possible that someone could spoof their username or something,
        // but this usage of an OfflinePlayer is solely for early blocking. The server will still do proper whitelist
        // validation if they make it past this.
        @SuppressWarnings("deprecation")
        val offlinePlayer = plugin.server.getOfflinePlayer(username)

        if (!offlinePlayer.isWhitelisted) {
            plugin.logger.info("Early blocked login attempt by $username")
            event.isCancelled = true
        }
    }
}