/*
 * AdaptLegacyHandshake.kt
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