package run.dn5.commandblacklist

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class BlackCommandManager {
    private val plugin = CommandBlackList.plugin
    private val blackCommands = ArrayList<BlackCommand>()

    init {
        this.registerBlackCommand()
    }

    private fun registerBlackCommand() {
        val config = YamlConfiguration.loadConfiguration(File(this.plugin.dataFolder, "config.yml"))
        val commands = config.getList("commands") as List<Map<String, Object>>

        for (map in commands) {
            val actions = ArrayList<Action>()
            for (a in map["actions"] as List<String>) {
                actions.add(this.plugin._ActionManager.getAction(a)!!)
            }
            val command = BlackCommand(map["command"] as String, actions)
            this.blackCommands.add(command)
        }
    }

    fun reload() {
        this.blackCommands.clear()
        this.registerBlackCommand()
    }

    fun getBlackCommand(command: String): BlackCommand? {
        return this.blackCommands.find { it.command == command }
    }
}