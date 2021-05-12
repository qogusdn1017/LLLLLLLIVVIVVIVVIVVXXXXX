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

package com.baehyeonwoo.lllllllivvivvivvivvxxxxx.debugcommands

import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.EndingScheduler
import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.HardenedEventListener
import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.HardenedPluginMain
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin
import java.io.File


class LVXCommands : CommandExecutor, TabCompleter {
    private val task = Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
    private var config = YamlConfiguration.loadConfiguration(File(getInstance().dataFolder, "config.yml"))
    private var debugmode = config.getBoolean("debug-mode")

    private fun getInstance(): Plugin {
        return HardenedPluginMain.instance
    }

    private fun getMsgTask() {
        HardenedEventListener().getMsgTask()
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (args.count() == 1) {
                if (sender.isOp) {
                    if (debugmode) {
                        args[0].let { vp ->
                            when (vp) {
                                "playerlist" -> {
                                    sender.sendMessage(config.getStringList("play-uuid").toString())
                                }
                                "checkdebug" -> {
                                    sender.sendMessage("The debug mode is properly enabled.")
                                }
                                "checkresx" -> {
                                    Bukkit.getOnlinePlayers().forEach {
                                        it.playSound(it.location, "komq.ending", SoundCategory.MASTER , 10000F, 1F)
                                        it.playSound(it.location, Sound.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.MASTER , 10000F, 1F)
                                        it.playSound(it.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.MASTER , 10000F, 1F)
                                        Bukkit.getScheduler().runTaskLater(getInstance(), Runnable {
                                            it.stopSound(SoundStop.all())
                                        }, 40)
                                        sender.sendMessage("Resx check progress is done.")
                                    }
                                }
                                "cancelallscheduler" -> {
                                    Bukkit.getScheduler().cancelTasks(getInstance())
                                    sender.sendMessage("Cancelled all scheduled tasks.")
                                }
                                "ending" -> {
                                    if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
                                        sender.sendMessage("Cancelling original task first due to prevent bugs.")
                                        Bukkit.getScheduler().cancelTask(task)
                                        Bukkit.getOnlinePlayers().forEach {
                                            it.stopSound(SoundStop.all())
                                        }
                                        sender.sendMessage("Cancelled original task. Enabling ending.")
                                        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
                                    }
                                    else {
                                        sender.sendMessage("Enabling ending.")
                                        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
                                    }
                                }
                                "stopending" -> {
                                    if (Bukkit.getScheduler().isQueued(task)) {
                                        sender.sendMessage("Cancelling ending task.\nNote that cancelTask method is not working so the plugin is using cancelTasks.")
                                        Bukkit.getScheduler().cancelTasks(getInstance())
                                        Bukkit.getOnlinePlayers().forEach {
                                            it.stopSound(SoundStop.all())
                                        }
                                    }
                                    else {
                                        sender.sendMessage(Component.text().color(TextColor.color(0xff0000)).content("BUT THERE WAS NO TASK LEFT TO STOP TO.").build())
                                    }
                                }
                                "msgtask" -> {
                                    getMsgTask()
                                    sender.sendMessage("Starting MsgTask.")
                                }
                                "event" -> {
                                        sender.sendMessage("Registering EventListener.")
                                        Bukkit.getPluginManager().registerEvents(HardenedEventListener(), getInstance())
                                }
                                "cancelevent" -> {
                                        sender.sendMessage("Stopping EventListener.")
                                        HandlerList.unregisterAll(getInstance())
                                }
                                else -> sender.sendMessage("${ChatColor.RED}/lvx checkdebug | playerlist | checkresx | cancelallscheduler | ending | stopending | msgtask | event | cancelevent")
                            }
                        }
                    }
                    sender.sendMessage("${ChatColor.RED}/lvx checkdebug | playerlist | checkresx | cancelallscheduler | ending | stopending | msgtask | event | cancelevent")
                }
                else {
                    sender.sendMessage("${ChatColor.RED}You don't have permission to perform this command.")
                }
            }
            else {
                sender.sendMessage("${ChatColor.RED}/lvx checkdebug | playerlist | checkresx | cancelallscheduler | ending | stopending | msgtask | event | cancelevent")
            }
        }
        else {
            sender.sendMessage("Please use this command in-game.")
        }
        return true
    }
    override fun onTabComplete(sender: CommandSender, cmd: Command, alias: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}