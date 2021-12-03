package run.dn5.commandblacklist.command

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import run.dn5.commandblacklist.CommandBlackList

class CommandBlackListExecutor : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val plugin = CommandBlackList.plugin
        if (args.isEmpty()) {
            sender.sendMessage("${ChatColor.AQUA}CommandBlackList ${ChatColor.GOLD}Version : ${plugin.description.version}")
            return false
        }
        when (args.size) {
            1 -> {
                plugin._ActionManager.reload()
                plugin._BlackCommandManager.reload()
                sender.sendMessage("${ChatColor.AQUA}The plugin was successfully reloaded.")
            }
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        when (args.size) {
            1 -> {
                return mutableListOf()
            }
        }
        return null
    }
}