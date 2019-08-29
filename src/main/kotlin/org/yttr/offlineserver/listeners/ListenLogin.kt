package org.yttr.offlineserver.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.plugin.java.JavaPlugin
import org.yttr.offlineserver.forgery.ForgeConnectionError

class ListenLogin(private val plugin: JavaPlugin) : Listener {
    @EventHandler
    fun onWhitelistKick(event: PlayerLoginEvent) {
        val player = event.player
        val isWhitelistEnabled = plugin.server.hasWhitelist()
        val isPlayerWhitelisted = player.isWhitelisted

        if (isWhitelistEnabled && !isPlayerWhitelisted) {
            val kickMessage = ForgeConnectionError.latest.message
            plugin.logger.info("Late blocked login attempt by ${player.name}")
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, kickMessage)
        }
    }
}