/*
 * OfflineServer.kt
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

package org.yttr.offlineserver

import com.comphenix.protocol.ProtocolLibrary
import org.bukkit.plugin.java.JavaPlugin
import org.yttr.offlineserver.adapters.*

class OfflineServer : JavaPlugin() {
    override fun onEnable() {
        // Register ProtocolLib listeners
        val protocolManager = ProtocolLibrary.getProtocolManager()
        val packetListeners = listOf(
            AdaptHandshake(this), AdaptLegacyHandshake(this),
            AdaptPing(this), AdaptPong(this), AdaptLogin(this))

        packetListeners.forEach { listener ->
            protocolManager.addPacketListener(listener)
        }

        // Report completion of initialization
        logger.info("This server is now cloaked!")
    }

    override fun onDisable() {
        logger.info("This server is no longer cloaked!")
    }
}