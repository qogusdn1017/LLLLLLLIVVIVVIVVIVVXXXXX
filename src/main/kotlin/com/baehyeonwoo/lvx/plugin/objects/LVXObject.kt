/*
 * Copyright (c) 2022 BaeHyeonWoo
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

package com.baehyeonwoo.lvx.plugin.objects

import com.baehyeonwoo.lvx.plugin.LVXPluginMain
import com.baehyeonwoo.lvx.plugin.tasks.LVXConfigReloadTask
import org.bukkit.Difficulty
import org.bukkit.GameRule
import org.bukkit.Location
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin

/***
 * @author BaeHyeonWoo
 */

object LVXObject {
    fun getInstance(): Plugin {
        return LVXPluginMain.instance
    }

    val server = getInstance().server

    private val world = requireNotNull(server.getWorld("world"))
    private val worldNether = requireNotNull(server.getWorld("world_nether"))
    private val worldTheEnd = requireNotNull(server.getWorld("world_the_end"))

    fun startGame() {
        getInstance().config.set("original-spawn", world.spawnLocation)
        getInstance().config.set("original-nether-spawn", worldNether.spawnLocation)
        getInstance().config.set("original-end-spawn", worldTheEnd.spawnLocation)

        getInstance().config.set("original-worldborder-center", world.worldBorder.center)
        getInstance().config.set("original-worldnetherborder-center", worldNether.worldBorder.center)
        getInstance().config.set("original-worldtheendborder-center", worldTheEnd.worldBorder.center)

        getInstance().config.set("original-worldborder-size", world.worldBorder.size)
        getInstance().config.set("original-worldnetherborder-size", worldNether.worldBorder.size)
        getInstance().config.set("original-worldtheendborder-size", worldTheEnd.worldBorder.size)

        for (worlds in server.worlds) {
            val spawnLoc = Location(worlds, 0.0, 0.0, 0.0)
            val zeroSpawn = spawnLoc.toHighestLocation()

            val border = worlds.worldBorder
            border.center = Location(worlds, 0.0, 0.0, 0.0)
            border.size = 16384.0
            worlds.spawnLocation = zeroSpawn

            worlds.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
            worlds.setGameRule(GameRule.SHOW_DEATH_MESSAGES, true)
            worlds.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false)
            worlds.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false)
            worlds.setGameRule(GameRule.REDUCED_DEBUG_INFO, true)
            worlds.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
            worlds.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false)
            worlds.setGameRule(GameRule.SPAWN_RADIUS, 0)
            worlds.difficulty = Difficulty.HARD
        }
    }

    fun stopGame() {
        world.spawnLocation = requireNotNull(getInstance().config.getLocation("original-spawn"))
        worldNether.spawnLocation = requireNotNull(getInstance().config.getLocation("original-nether-spawn"))
        worldTheEnd.spawnLocation = requireNotNull(getInstance().config.getLocation("original-end-spawn"))

        world.worldBorder.center = requireNotNull(getInstance().config.getLocation("original-worldborder-center"))
        worldNether.worldBorder.center = requireNotNull(getInstance().config.getLocation("original-worldnetherborder-center"))
        worldTheEnd.worldBorder.center = requireNotNull(getInstance().config.getLocation("original-worldtheendborder-center"))

        world.worldBorder.size = requireNotNull(getInstance().config.getDouble("original-worldborder-size"))
        worldNether.worldBorder.size = requireNotNull(getInstance().config.getDouble("original-worldnetherborder-size"))
        worldTheEnd.worldBorder.size = requireNotNull(getInstance().config.getDouble("original-worldtheendborder-size"))

        for (worlds in server.worlds) {
            worlds.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, true)
            worlds.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, true)
            worlds.setGameRule(GameRule.LOG_ADMIN_COMMANDS, true)
            worlds.setGameRule(GameRule.REDUCED_DEBUG_INFO, false)
            worlds.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false)
            worlds.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, true)
            worlds.setGameRule(GameRule.SPAWN_RADIUS, 10)
            worlds.difficulty = Difficulty.EASY
        }

        server.scheduler.cancelTasks(getInstance())
        HandlerList.unregisterAll(getInstance())
        server.scheduler.runTaskTimer(getInstance(), LVXConfigReloadTask(), 0L, 20L)
    }
}