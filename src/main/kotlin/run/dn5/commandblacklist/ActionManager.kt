package run.dn5.commandblacklist

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ActionManager {
    private val plugin: CommandBlackList = CommandBlackList.plugin
    private val actions: ArrayList<Action> = ArrayList()

    init {
        this.registerActions()
    }

    private fun registerActions() {
        val config = YamlConfiguration.loadConfiguration(File(this.plugin.dataFolder, "config.yml"))
        val actions: List<Map<String, String>> = config.getList("actions") as List<Map<String, String>>
        for (a in actions) {
            val action = Action(a["name"]!!, a["type"]!!, a["data"]!!)
            this.actions.add(action)
        }
        Bukkit.getLogger().info("Total Actions ${this.actions.size}")
    }

    fun reload() {
        this.actions.clear()
        this.registerActions()
    }

    fun getAction(name: String): Action {
        return this.actions.first { it.name == name }
    }
}