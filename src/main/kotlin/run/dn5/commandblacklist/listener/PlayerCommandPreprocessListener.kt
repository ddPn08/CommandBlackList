package run.dn5.commandblacklist.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import run.dn5.commandblacklist.CommandBlackList

class PlayerCommandPreprocessListener : Listener {
    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val plugin = CommandBlackList.plugin
        val player = event.player
        if (player.hasPermission(CommandBlackList.permissions["ignore"]!!)) return

        val command = plugin._BlackCommandManager.getBlackCommand(event.message)
        if (command === null) return

        for (action in command.actions) {
            action.execute(player)
        }
    }
}