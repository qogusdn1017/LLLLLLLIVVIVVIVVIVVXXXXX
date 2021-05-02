package com.baehyeonwoo.lllllllivvivvivvivvxxxxx.debugcommands

import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.EndingScheduler
import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.HardenedEventListener
import com.baehyeonwoo.lllllllivvivvivvivvxxxxx.HardenedPluginMain
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
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

    private var debugconfig = YamlConfiguration.loadConfiguration(File(getInstance().dataFolder, "debug.yml"))
    private var debugmode = debugconfig.getBoolean("debug-mode")

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
                                "checkdebug" -> {
                                    sender.sendMessage("The debug mode is properly enabled.")
                                }
                                "checkresx" -> {
                                    Bukkit.getOnlinePlayers().forEach {
                                        it.playSound(it.location, "komq.ending", SoundCategory.MASTER , 10000F, 1F)
                                        it.playSound(it.location, Sound.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.MASTER , 10000F, 1F)
                                        it.playSound(it.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.MASTER , 10000F, 1F)
                                        it.stopSound("komq.ending", SoundCategory.MASTER)
                                        it.stopSound(Sound.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.MASTER)
                                        it.stopSound(Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.MASTER)

                                        sender.sendMessage("Resx check progress is done.")
                                    }
                                }
                                "cancelallscheduler" -> {
                                    sender.sendMessage("Cancelled all scheduled tasks.")
                                    Bukkit.getScheduler().cancelTasks(getInstance())
                                }
                                "ending" -> {
                                    val task = Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)

                                    if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
                                        sender.sendMessage("Cancelling original task first due to prevent bugs.")
                                        Bukkit.getScheduler().cancelTask(task)
                                        sender.sendMessage("Cancelled original task. Enabling ending.")
                                        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
                                    }
                                    else {
                                        sender.sendMessage("Enabling ending.")
                                        Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
                                    }
                                }
                                "stopending" -> {
                                    val task = Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)

                                    if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
                                        sender.sendMessage("Cancelling ending task.")
                                        Bukkit.getScheduler().cancelTask(task)
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
                                    val event = HandlerList.getRegisteredListeners(getInstance()).toArray().contains(HardenedEventListener())
                                    if (event) {
                                        sender.sendMessage(Component.text().color(TextColor.color(0xff0000)).content("BUT THE EVENTLISTENER WAS ALREADY ENABLED.").build())
                                    }
                                    else {
                                        sender.sendMessage("Registering EventListener.")
                                        Bukkit.getPluginManager().registerEvents(HardenedEventListener(), getInstance())
                                    }
                                }
                                "cancelevent" -> {
                                    val eventlists = HandlerList.getRegisteredListeners(getInstance())
                                    if (!eventlists.toArray().contains(HardenedEventListener())) {
                                        sender.sendMessage(Component.text().color(TextColor.color(0xff0000)).content("BUT THERE WAS NO EVENTLISTENER TO STOP TO.").build())
                                    }
                                    else {
                                        sender.sendMessage("Stopping EventListener.")
                                        HandlerList.unregisterAll(getInstance())
                                    }
                                }
                                else -> sender.sendMessage(Component.text().color(TextColor.color(0xFF0000)).content("/lvx checkdebug | checkresx | cancelallscheduler | ending | stopending | msgtask | event | cancelevent"))
                            }
                        }
                    }
                    else {
                        sender.sendMessage(Component.text().color(TextColor.color(0xff0000)).content("You cannot perform this action unless debug mode is enabled. Please enable debug-mode in \"debug.yml.\"").build())
                    }
                }
                else {
                    sender.sendMessage(Component.text().color(TextColor.color(0xff0000)).content("You don't have permission to perform this command.").build())
                }
            }
            else {
                sender.sendMessage(Component.text().color(TextColor.color(0xFF0000)).content("/lvx checkdebug | checkresx | cancelallscheduler | ending | stopending | msgtask | event | cancelevent"))
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