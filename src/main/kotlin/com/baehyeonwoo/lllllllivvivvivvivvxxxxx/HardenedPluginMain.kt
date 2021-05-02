/*
 * Copyright (c) 2021 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baehyeonwoo.lllllllivvivvivvivvxxxxx

import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.debugcommands.LVXCommands
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.GameRule
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/***
 * @author BaeHyeonWoo
 */

class HardenedPluginMain : JavaPlugin() {

    companion object {
        lateinit var instance: HardenedPluginMain
            private set
    }

    override fun onEnable() {

        // DEBUG CONFIG

        saveDefaultConfig()
        val config = YamlConfiguration.loadConfiguration(File(dataFolder, "debug.yml"))
        val debugmode = config.getBoolean("debug-mode")

        for (world in Bukkit.getWorlds()) {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, true)
            world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false)
            world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false)
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true)
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
            world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false)
            world.setGameRule(GameRule.SPAWN_RADIUS, 0)
            world.difficulty = Difficulty.HARD
            instance = this
        }

        Bukkit.getWorlds().first().apply {
            val border = worldBorder
            border.center = Location(this, 0.0, 0.0, 0.0)
            border.size = 16384.0
            spawnLocation = getHighestBlockAt(0, 0).location
        }

        // DEBUG COMMANDS

        if (debugmode) {
            getCommand("lvx")?.setExecutor(LVXCommands())
            getCommand("lvx")?.tabCompleter = LVXCommands()
        }

        logger.info("Plugin has successfully enabled.")
        logger.warning("This plugin has the strict level of changing world. If you didn't meant to activate this plugin please stop the server and remove the plugin immediately.")
        if (debugmode) {
            logger.warning("Debug mode has been enabled. The creator is not responsible for ANY DAMAGE TO YOUR WORKAROUNDS.")
        }
        server.pluginManager.registerEvents(HardenedEventListener(), this)
    }
    override fun onDisable() {
        logger.info("Was it too hard for you? lol") // Bassline yatteru? lol
    }
}