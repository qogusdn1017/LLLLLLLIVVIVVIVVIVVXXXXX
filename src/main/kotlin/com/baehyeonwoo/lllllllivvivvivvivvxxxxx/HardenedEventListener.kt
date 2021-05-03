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

import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import com.github.monun.tap.effect.playFirework
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.*
import org.bukkit.Bukkit.getLogger
import org.bukkit.Bukkit.getScheduler
import org.bukkit.block.data.type.Bed
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.TabCompleteEvent
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.io.File
import java.util.*
import kotlin.random.Random.Default.nextInt


/***
 * @author BaeHyeonWoo
 *
 * Special Thanks to PatrickKR(https://github.com/patrick-mc) who helped me with the code.
 */

class HardenedEventListener : Listener {

    private var debugconfig = YamlConfiguration.loadConfiguration(File(getInstance().dataFolder, "config.yml"))
    private var debugmode = debugconfig.getBoolean("debug-mode")
    private var jagangducheon = debugconfig.getBoolean("jagang-ducheon")
    private var monsterdmg = debugconfig.getInt("monster-damage")

    fun getMsgTask() {
        if (debugmode) {
            getScheduler().runTaskLater(getInstance(), Runnable {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}\"도전을 하면 할수록, 당신이 다른 차원과 점점 더 가까워 지는 것을 느낍니다...\"", 0, 150, 0)
                    it.sendMessage(Component.text().content("여러분들은 항상 어떠한 꿈을 꿔 오셨나요?").build())
                    it.sendMessage(Component.text().content("이스터에그 #1: DREAM.").build())
                }
            }, 20 * 60 * 2)
            getScheduler().runTaskLater(getInstance(), Runnable {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "\"${ChatColor.GRAY}다른 세계에서 나눠진 과거의 대화가 당신에게 들려옵니다...\"", 0, 150, 0)
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 과연 그가 여기까지, 이 자리까지 올 수 있을까?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 음, 그가 모든 시련을 이겨낸다면?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 너무한거 아니야? 이정도로 심하게 시련을 준다고?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 난 그에게 시련만 주는 것이 아니야. 아직 그에게 줄 사랑이 남아있어.\n").build())
                    it.sendMessage(Component.text().content("스포일러 #1: JZKTK4SWIVDG2Y2UNB4U6VCBHU======").build())
                }
            }, 20 * 60 * 5)
        } else {
            getScheduler().runTaskLater(getInstance(), Runnable {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}\"도전을 하면 할수록, 당신이 다른 차원과 점점 더 가까워 지는 것을 느낍니다...\"", 0, 150, 0)
                    it.sendMessage(Component.text().content("여러분들은 항상 어떠한 꿈을 꿔 오셨나요?").build())
                    it.sendMessage(Component.text().content("이스터에그 #1: DREAM.").build())
                }
            }, 20 * 60 * 60 * 2)
            getScheduler().runTaskLater(getInstance(), Runnable {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "\"${ChatColor.GRAY}다른 세계에서 나눠진 과거의 대화가 당신에게 들려옵니다...\"", 0, 150, 0)
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 과연 그가 여기까지, 이 자리까지 올 수 있을까?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 음, 그가 모든 시련을 이겨낸다면?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 너무한거 아니야? 이정도로 심하게 시련을 준다고?").build())
                    it.sendMessage(Component.text().content("${ChatColor.MAGIC}???${ChatColor.RESET}: 난 그에게 시련만 주는 것이 아니야. 아직 그에게 줄 사랑이 남아있어.\n").build())
                    it.sendMessage(Component.text().content("스포일러 #1: K5WXINCMLBYE4WSHGFLUYVCRM4======").build())
                }
            }, 20 * 60 * 60 * 4)
        }
    }

    private fun getInstance(): Plugin {
        return HardenedPluginMain.instance
    }

    private var UUID.lastkicked: Long
        get() {
            return timestamps[this] ?: 0
        }
        set(value) {
            timestamps[this] = value
        }

    private val timestamps = HashMap<UUID, Long>()

    private var deathcount = 0

    @EventHandler
    fun onPaperServerListPingEvent(e: PaperServerListPingEvent) {
        e.setHidePlayers(true)
        e.maxPlayers = 0
        e.numPlayers = 0
        e.motd(Component.text().color(TextColor.color(0x8b0000)).content("${ChatColor.BOLD}${ChatColor.ITALIC}LLLLLLLIVVIVVIVVIVVXXXXX.").build())
    }

    @EventHandler
    fun onPlayerJoinEvent(e: PlayerJoinEvent) {
        e.joinMessage(Component.text().color(TextColor.color(0xffff00)).content("${e.player.name}님이 멸망해가는 세계에서 생존하기 위해 참여하였습니다.").build())
        if (debugmode) {
            if (e.player.uniqueId.toString() == debugconfig.getString("play-uuid")) {
                getMsgTask()
            }
            else {
                e.player.gameMode = GameMode.SPECTATOR
                e.joinMessage(Component.text().color(TextColor.color(0xffff00)).content("${e.player.name}님이 디버깅된 월드에 관심이 생깁니다.").build())
            }
        }
        else {
            if (e.player.uniqueId.toString() == "389c4c9b-6342-42fc-beb3-922a7d7a72f9" || e.player.uniqueId.toString() == "5082c832-7f7c-4b04-b0c7-2825062b7638") {
                if (!e.player.hasPlayedBefore()) {
                    Bukkit.getOnlinePlayers().forEach {
                        it.sendTitle("", "\"멸망해가는 세계, 그곳에서 생존하기 위해 나타난 한 사람.\"", 0, 150, 0)
                    }
                }
                getMsgTask()
            } else {
                e.player.gameMode = GameMode.SPECTATOR
                e.joinMessage(
                    Component.text().color(TextColor.color(0xffff00)).content("${e.player.name}님이 그의 여정에 관심이 생깁니다.").build())
            }
        }
    }

    @EventHandler
    fun onPlayerQuitEvent(e: PlayerQuitEvent) {
        if (debugmode) {
            if (e.player.uniqueId.toString() == debugconfig.getString("play-uuid")) {
                getScheduler().cancelTasks(getInstance())
            }
            else {
                e.quitMessage(Component.text().color(TextColor.color(0xffff00)).content("${e.player.name}은 이 디버깅된 세계에서 떠났다.").build())
            }
        }
        else {
            e.quitMessage(Component.text().color(TextColor.color(0xffff00)).content("${e.player.name}은 이 멸망해가는 세계에서 떠났다.").build())
            if (e.player.uniqueId.toString() == "389c4c9b-6342-42fc-beb3-922a7d7a72f9" || e.player.uniqueId.toString() == "5082c832-7f7c-4b04-b0c7-2825062b7638") {
                getScheduler().cancelTasks(getInstance())
            }
        }
    }

    @EventHandler
    fun onPlayerDeathEvent(e: PlayerDeathEvent) {

        deathcount++

        if (deathcount == 100) {
            Bukkit.getOnlinePlayers().forEach {
                it.sendTitle("", "${ChatColor.RED}\"끝없는 도전, 끝없는 용기 만큼은 인정한다.", 0, 150, 0)
                it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("방금 전 그들의 말은 당신에게 큰 시련을 계속 주는 것을 포기하였다. 앞으로의 시련이 줄기를 빈다.").build())
                it.sendMessage(Component.text().content("스포일러 #2: 포기는 누가 먼저 했지? 내 앞에는 영원히 빛나는 빛이 있어."))
            }
        }

        getScheduler().runTaskLater(getInstance(), Runnable {
            when {
                nextInt(4) == 0 -> {
                    e.entity.player?.kick(Component.text().color(TextColor.color(0x8b0000)).content("그만 포기해. 나도 포기했어.").build())
                    e.entity.player?.uniqueId?.lastkicked = System.currentTimeMillis()
                }
                nextInt(4) == 1 -> {
                    e.entity.player?.kick(Component.text().color(TextColor.color(0x8b0000)).content("진짜 그게 되겠니?").build())
                    e.entity.player?.uniqueId?.lastkicked = System.currentTimeMillis()
                }
                nextInt(4) == 2 -> {
                    e.entity.player?.kick(Component.text().color(TextColor.color(0x8b0000)).content("넌 이 고통속에서 살아갈 수 밖에 없는 존재야.").build())
                    e.entity.player?.uniqueId?.lastkicked = System.currentTimeMillis()
                }
                nextInt(4) == 3 -> {
                    e.entity.player?.kick(Component.text().color(TextColor.color(0x8b0000)).content("하하. 꺼저버려.").build())
                    e.entity.player?.uniqueId?.lastkicked = System.currentTimeMillis()
                }
                else -> {
                    e.entity.player?.kick(Component.text().color(TextColor.color(0x8b0000)).content("넌 가망이 없어.").build())
                    e.entity.player?.uniqueId?.lastkicked = System.currentTimeMillis()
                }
            }
        }, 35)
    }

    @EventHandler
    fun onLoginEvent(e: AsyncPlayerPreLoginEvent) {
        if (System.currentTimeMillis() - e.uniqueId.lastkicked < 10000) {
            when {
                nextInt(4) == 0 -> {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Component.text().color(TextColor.color(0x8b0000)).content("다시 하려고? 아니, 넌 글렀어.").build())
                }
                nextInt(4) == 1 -> {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Component.text().color(TextColor.color(0x8b0000)).content("왜 계속 도전하는 건지 나는 이해를 못하겠어.").build())
                }
                nextInt(4) == 2 -> {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Component.text().color(TextColor.color(0x8b0000)).content("다른 할일이 있지 않아?").build())
                }
                nextInt(4) == 3 -> {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Component.text().color(TextColor.color(0x8b0000)).content("그만해. 나도 이제 지친다.").build())
                }
                else -> {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, Component.text().color(TextColor.color(0x8b0000)).content("지금 도전 해봤자 큰 의미가 있을까?").build())
                }
            }
        }
    }

    @EventHandler
    fun onTabComplete(e: TabCompleteEvent) {
        if (!e.sender.isOp) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerCommandPreprocessEvent(e: PlayerCommandPreprocessEvent) {
        if (!e.player.isOp) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerChatEvent(e: AsyncChatEvent) {
        if (!e.player.isOp) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDamageByEntityandJagangDucheonEvent(e: EntityDamageByEntityEvent) {
        val dmgr = e.damager
        val d = e.finalDamage

        fun ducheon() {
            if (dmgr is Player) {
                dmgr.damage(d)
            }

            if (dmgr is Arrow) {
                val arrowsrc = dmgr.shooter as? Player ?: return
                arrowsrc.damage(d)
            }

            if (dmgr is Trident) {
                val tridentsrc = dmgr.shooter as? Player ?: return
                tridentsrc.damage(d)
            }
        }

        if (debugmode) {
            if (jagangducheon) {
                ducheon()
            }
            else {
                getLogger().info("Currently JagangDucheon is disabled because of debug config.")
            }
        }
        else {
            ducheon()
        }

        if (debugmode) {
            if (dmgr is Monster) {
                e.damage = e.damage * monsterdmg
            }
        }
        else {
                if (dmgr is Monster) {
                e.damage = e.damage * 3
            }
        }
    }

    @EventHandler
    fun onEntityDeathEvent(e: EntityDeathEvent) {
        val entity: LivingEntity = e.entity
        val firework = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build()

        fun dragonEnd() {
            if (entity.killer is Player || entity.killer is Bed || entity.killer is LivingEntity) {
                val loc = entity.killer?.location?.add(0.0, 0.9, 0.0)
                loc?.world?.playFirework(loc, firework)
                loc?.world?.playFirework(loc, firework)
                loc?.world?.playFirework(loc, firework)
                Bukkit.getOnlinePlayers().forEach {
                    it.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 1000000, 0, false, false))
                    it.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 255, false, false))
                    it.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 1000000, 255, false, false))
                }
            }
            getScheduler().scheduleSyncRepeatingTask(getInstance(), EndingScheduler(), 20, 20)
        }
        if (entity.type == EntityType.ENDER_DRAGON) {
                dragonEnd()
        }
    }
}