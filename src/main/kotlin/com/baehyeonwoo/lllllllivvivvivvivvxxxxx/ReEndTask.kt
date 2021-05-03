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

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/***
 * @author BaeHyeonWoo
 */

class ReEndTask : Runnable {
    private var count = 0

    private fun getInstance(): Plugin {
        return HardenedPluginMain.instance
    }

    private var soundcount = 0

    override fun run() {
        when (count++) {
            5 -> {
                Bukkit.getOnlinePlayers().forEach {
                    it.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 1000000, 5, false, false))
                    it.playSound(it.location, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.MASTER, 1000f, 0.7f)
                    it.playSound(it.location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.MASTER, 1000f, 1f)
                    while (soundcount < 12) {
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.MASTER, 1000F, 1F)
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.MASTER, 1000F, 1F)
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.MASTER, 1000F, 1F)
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.MASTER, 1000F, 1F)
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, SoundCategory.MASTER, 1000F, 1F)
                        soundcount++
                    }
                }
            }
            12 -> {
                Bukkit.getOnlinePlayers().forEach {

                    it.sendTitle("", "${ChatColor.GRAY}모든 시련을 이겼음에도 다시 한번 도전하는 당신.", 5, 100, 5)
                }
            }
            17 -> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}무엇 때문이었을까?", 5, 100, 5)
               }
            }
            22-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}행복? 희망? 슬픔? 고통?", 5, 100, 5)
                }
            }
            27-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}...", 5, 100, 5)
                }
            }
            32-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}그래,", 5, 100, 5)
                }
            }
            35-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}넌 절대로 이런거에 만족 할 수 없는걸 알아.", 5, 100, 5)
                }
            }
            40-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}다음에 보자고.", 5, 100, 5)
                }
            }
            45-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}\"이제 이 첫 여정은 완전히 끝나게 된다.\"", 0, 150, 0)
                    it.removePotionEffect(PotionEffectType.SLOW)
                    it.removePotionEffect(PotionEffectType.BLINDNESS)
                    it.removePotionEffect(PotionEffectType.LEVITATION)
                    it.sendMessage(Component.text().content("스포일러 #3: 호기심, 그리고 의문의 인물."))
                    Bukkit.getScheduler().cancelTasks(getInstance())
                    HandlerList.unregisterAll(getInstance())
                }
            }
        }
    }
}