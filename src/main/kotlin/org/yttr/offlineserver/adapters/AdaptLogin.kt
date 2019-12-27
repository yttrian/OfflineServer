/*
 * AdaptLogin.kt
 *
 * OfflineServer, pretend your Minecraft server is offline
 *
 * Copyright (C) 2019-2019 by Ian Moore
 *
 * This file is part of OfflineServer.
 *
 * OfflineServer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.yttr.offlineserver.adapters

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * Despite the server appearing offline in the server listing, it's not actually completely ignoring the client.
 * That said, we need a way to close the connection of unwanted players in a convincing manner that won't raise
 * suspicion. In order to do so, we need to intercept the login as early as possible before Encryption starts
 * and the client sees a message related to that.
 *
 * The earliest packet the client sends that contains their username is Login Start, which is sent after a Handshake
 * that switches the current state to 2 (Login). Here we can dirtily check if they are whitelisted via the deprecated
 * blocking getOfflinePlayer(username) method.
 *
 * This allows us to timeout the connection, providing a reasonable failure.
 *
 * https://wiki.vg/Protocol#Login_Start
 */
class AdaptLogin(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Login.Client.START)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        val username = event.packet.gameProfiles.read(0).name
        val isWhitelistEnabled = plugin.server.hasWhitelist()

        // For now, we will ignore the deprecation warning. Ideally, we should be checking users by UUID,
        // but that is not provided in PacketLoginInStart (https://wiki.vg/Protocol#Login_Start) so we need to check
        // via username. It is possible that someone could spoof their username or something,
        // but this usage of an OfflinePlayer is solely for early blocking. The server will still do proper whitelist
        // validation and user login checking if they make it past this.
        @SuppressWarnings("deprecation")
        val offlinePlayer = plugin.server.getOfflinePlayer(username)
        val isPlayerWhitelisted = offlinePlayer.isWhitelisted

        if (isWhitelistEnabled && !isPlayerWhitelisted) {
            plugin.logger.info("Blocked login attempt by $username")
            event.isCancelled = true
        }
    }
}