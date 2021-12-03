package run.dn5.commandblacklist

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class Action(
    val name: String,
    private val type: String,
    private val data: String
) {
    fun execute(sender: Player) {
        when (type) {
            "command" -> {
                val command = this.data
                    .replace("\${sender}", sender.name)
                    .replaceFirst("/", "")
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command)
            }
        }
    }
}