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

package com.baehyeonwoo.lvx.plugin

import com.baehyeonwoo.lvx.plugin.config.LVXConfig.load
import com.baehyeonwoo.lvx.plugin.events.LVXEvent
import com.baehyeonwoo.lvx.plugin.tasks.LVXConfigReloadTask
import org.bukkit.Difficulty
import org.bukkit.GameRule
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/***
 * @author BaeHyeonWoo
 */

class LVXPluginMain : JavaPlugin() {

    companion object {
        lateinit var instance: LVXPluginMain
            private set
    }

    override fun onEnable() {
        instance = this

        // CONFIG
        val configFile = File(dataFolder, "config.yml")
        load(configFile)

        logger.info("Plugin has successfully enabled.")
        if (config.getBoolean("debug-mode")) {
            logger.info("Debug mode has been enabled.")
        }

        server.pluginManager.registerEvents(LVXEvent(), this)
        server.scheduler.runTaskTimer(this, LVXConfigReloadTask(), 0L, 20L)
    }
}