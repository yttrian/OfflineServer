/*
 * AdaptHandshake.kt
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
import com.comphenix.protocol.PacketType.Protocol
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * When a client wants to get the status of a server in the server list, it sends a Handshake packet with the NextState
 * field set to 1 (Status) and follows up with a Request packet. The server then responds with a Response packet
 * containing its status.
 *
 * However, if we intercept the Handshake a prevent the server from ever receiving it, it will never respond with a
 * Response to the client, thus making the client think the server is offline. It is not enough to just intercept the
 * server Response, because if things get that far, the client will send a Ping to the server (which we also ignore),
 * and it takes a suspicious amount of time to timeout compared to servers that are actually offline.
 *
 * https://wiki.vg/Server_List_Ping
 */
class AdaptHandshake(plugin: JavaPlugin) : PacketAdapter(
    plugin,
    ListenerPriority.HIGHEST,
    listOf(PacketType.Handshake.Client.SET_PROTOCOL)
) {
    override fun onPacketReceiving(event: PacketEvent) {
        val packet = event.packet
        val nextState = packet.protocols.read(0)

        if (nextState == Protocol.STATUS) {
            event.isCancelled = true
        }
    }
}