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

package com.baehyeonwoo.lvx.plugin.events

import com.baehyeonwoo.lvx.plugin.objects.LVXObject.getInstance
import com.baehyeonwoo.lvx.plugin.objects.LVXObject.server
import com.baehyeonwoo.lvx.plugin.tasks.LVXEndingTask
import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import io.github.monun.tap.effect.playFirework
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title.Times.of
import net.kyori.adventure.title.Title.title
import org.bukkit.Bukkit.getScheduler
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.GameMode
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.lang.System.currentTimeMillis
import java.time.Duration.ofSeconds
import java.util.*
import kotlin.random.Random.Default.nextInt

/***
 * @author BaeHyeonWoo
 */

class LVXEvent : Listener {

    private var UUID.lastkicked: Long
        get() {
            return timestamps[this] ?: 0
        }
        set(value) {
            timestamps[this] = value
        }

    private val timestamps = HashMap<UUID, Long>()

    @EventHandler
    fun onPaperServerListPing(e: PaperServerListPingEvent) {
        e.playerSample.clear()
        e.maxPlayers = 0
        e.numPlayers = 0
        e.motd(text("LLLLLLLIVVIVVIVVIVVXXXXX.", NamedTextColor.DARK_RED).decorate(TextDecoration.ITALIC))
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.joinMessage(text("${e.player.name}님이 멸망해가는 세계에서 생존하기 위해 참여하셨습니다.", NamedTextColor.YELLOW))
        if (getInstance().config.getBoolean("debug-mode")) {
            if (e.player.uniqueId.toString() !in getInstance().config.getString("player-uuid").toString()) {
                e.player.gameMode = GameMode.SPECTATOR
                e.joinMessage(text("${e.player.name}님이 디버깅된 월드에 관심이 생깁니다.", NamedTextColor.YELLOW))
            }
        }
        else {
            if (e.player.uniqueId.toString() in getInstance().config.getString("player-uuid").toString()) {
                if (!e.player.hasPlayedBefore()) {
                    server.onlinePlayers.forEach {
                        it.showTitle(title(text(""), text("\"멸망해가는 세계, 그곳에섯 애존하기 위해 나타난 한 사람.\""), of(ofSeconds(0), ofSeconds(8), ofSeconds(0))))
                    }
                }
            }
            else {
                e.player.gameMode = GameMode.SPECTATOR
                e.joinMessage(text("${e.player.name}님이 그의 여정에 관심이 생깁니다.", NamedTextColor.YELLOW))
            }
        }
    }

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        if (getInstance().config.getBoolean("debug-mode")) {
            if (e.player.uniqueId.toString() in getInstance().config.getStringList("player-uuid").toString()) {
                e.quitMessage(text("${e.player.name}님이 디버깅된 세계에서 떠났습니다.", NamedTextColor.YELLOW))
            } else e.quitMessage(null)
        }
        else {
            if (e.player.uniqueId.toString() in getInstance().config.getString("player-uuid").toString()) {
                getScheduler().cancelTasks(getInstance())
                e.quitMessage(text("${e.player.name}님이 멸망해가는 세계에서 떠났습니다.", NamedTextColor.YELLOW))
            }
            else {
                e.quitMessage(null)
            }
        }
    }

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        getScheduler().runTaskLater(getInstance(), Runnable {
            when (nextInt(4)) {
                0 -> {
                    e.entity.player?.kick(text("그만 포기해. 나도 포기했어.", NamedTextColor.DARK_RED))
                    e.entity.player?.uniqueId?.lastkicked = currentTimeMillis()
                }
                1 -> {
                    e.entity.player?.kick(text("진짜 그게 되겠니?", NamedTextColor.DARK_RED))
                    e.entity.player?.uniqueId?.lastkicked = currentTimeMillis()
                }
                2 -> {
                    e.entity.player?.kick(text("넌 이 고통속에서 살아갈 수 밖에 없는 존재야.", NamedTextColor.DARK_RED))
                    e.entity.player?.uniqueId?.lastkicked = currentTimeMillis()
                }
                3 -> {
                    e.entity.player?.kick(text("하하. 꺼저버려.", NamedTextColor.DARK_RED))
                    e.entity.player?.uniqueId?.lastkicked = currentTimeMillis()
                }
                else -> {
                    e.entity.player?.kick(text("넌 가망이 없어.", NamedTextColor.DARK_RED))
                    e.entity.player?.uniqueId?.lastkicked = currentTimeMillis()
                }
            }
        }, 35)
    }

    @EventHandler
    fun onAsyncPlayerPreLogin(e: AsyncPlayerPreLoginEvent) {
        if (currentTimeMillis() - e.uniqueId.lastkicked < 10000) {
            when (nextInt(4)) {
                0 -> {
                    e.disallow(Result.KICK_FULL, text("다시 하려고? 아니, 넌 글렀어.", NamedTextColor.DARK_RED))
                }
                1 -> {
                    e.disallow(Result.KICK_FULL, text("왜 계속 도전하는 건지 나는 이해를 못하겠어.", NamedTextColor.DARK_RED))
                }
                2 -> {
                    e.disallow(Result.KICK_FULL, text("다른 할일이 있지 않아?", NamedTextColor.DARK_RED))
                }
                3 -> {
                    e.disallow(Result.KICK_FULL, text("그만해. 나도 이제 지친다.", NamedTextColor.DARK_RED))
                }
                else -> {
                    e.disallow(Result.KICK_FULL, text("지금 도전 해봤자 큰 의미가 있을까?", NamedTextColor.DARK_RED))
                }
            }
        }
    }

    @EventHandler
    fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {
        val dmgr = e.damager
        val d = e.finalDamage

        fun ducheon() {
            if (dmgr is Player) {
                dmgr.damage(d)
            }

            if (dmgr is Projectile) {
                if (dmgr.shooter is Player) {
                    (dmgr.shooter as Player).damage(d)
                }
            }
        }

        if (getInstance().config.getBoolean("debug-mode")) {
            if (getInstance().config.getBoolean("jagang-ducheon")) {
                ducheon()
            } else {
                server.logger.info("JagangDucheon is disabled because of the debug config.")
            }
        } else {
            ducheon()
        }

        if (dmgr is Monster) {
            e.damage = e.damage * 3
        }
    }

    @EventHandler
    fun onPlayerAdvancementDone(e: PlayerAdvancementDoneEvent) {
        val p = e.player
        val advc = e.advancement
        val firework = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build()

        fun dragonEnd() {
            val loc = p.location.add(0.0, 0.9, 0.0)
            loc.world?.playFirework(loc, firework)
            server.onlinePlayers.forEach {
                it.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 1000000, 0, false, false))
                it.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 255, false, false))
                it.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 1000000, 255, false, false))
            }
            server.scheduler.runTaskTimer(getInstance(), LVXEndingTask(), 20, 20)
        }

        if (advc.key.toString() == "minecraft:end/kill_dragon") {
            if (p.getAdvancementProgress(advc).isDone) {
                dragonEnd()
            }
        }
    }
}