/*
 * AdaptPing.kt
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