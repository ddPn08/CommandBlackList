package run.dn5.commandblacklist

import org.bukkit.Bukkit
import org.bukkit.permissions.Permission
import org.bukkit.plugin.java.JavaPlugin
import run.dn5.commandblacklist.command.CommandBlackListExecutor
import run.dn5.commandblacklist.listener.PlayerCommandPreprocessListener
import java.io.File
import java.nio.file.Files

class CommandBlackList : JavaPlugin() {

    companion object {
        lateinit var plugin: CommandBlackList
            private set

        val permissions = mapOf<String, Permission>(
            "ignore" to Permission("CommandBlackList.ignore")
        )

        fun initPermissions() {
            permissions.forEach {
                Bukkit.getPluginManager().addPermission(it.value)
            }
        }
    }

    lateinit var _ActionManager: ActionManager
    lateinit var _BlackCommandManager: BlackCommandManager

    override fun onEnable() {
        plugin = this
        initPermissions()

        this.server.getPluginCommand("commandblacklist")?.setExecutor(CommandBlackListExecutor())

        this.saveFiles()

        this._ActionManager = ActionManager()
        this._BlackCommandManager = BlackCommandManager()

        this.server.pluginManager.registerEvents(PlayerCommandPreprocessListener(), this)
    }

    private fun saveFiles() {
        if (!Files.exists(this.dataFolder.toPath())) {
            Files.createDirectory(this.dataFolder.toPath())
        }

        val config = File(this.dataFolder, "config.yml")
        if (!config.exists()) {
            Files.copy(this.getResource("config.yml"), config.toPath())
        }
    }
}