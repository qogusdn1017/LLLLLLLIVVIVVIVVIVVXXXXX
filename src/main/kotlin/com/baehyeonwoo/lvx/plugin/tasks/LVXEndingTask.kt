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

package com.baehyeonwoo.lvx.plugin.tasks

import com.baehyeonwoo.lvx.plugin.objects.LVXObject.getInstance
import com.baehyeonwoo.lvx.plugin.objects.LVXObject.server
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title.Times.of
import net.kyori.adventure.title.Title.title
import org.bukkit.Sound
import org.bukkit.event.HandlerList
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration.ofSeconds

/***
 * @author BaeHyeonWoo
 */

class LVXEndingTask : Runnable {
    
    private var count = 0

    private var soundcount = 0

    override fun run() {
        when (count++) {
            5 -> {
                server.onlinePlayers.forEach {
                    it.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 1000000, 5, false, false))
                    it.playSound(it.location, Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1000F, 0.7F)
                    it.playSound(it.location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1000F, 1F)
                    while (soundcount < 12) {
                        it.playSound(it.location, Sound.AMBIENT_BASALT_DELTAS_MOOD, 1000F, 1F)
                        soundcount++
                    }
                }
            }
            12 -> {
                server.onlinePlayers.forEach {
                    it.playSound(it.location, "komq.ending", 1000F, 1F)
                    it.showTitle(title(text(""), text("당신의 첫 여정이 끝났습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            17 -> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("재미있으셨나요?", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            22-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("세계는 당신이 여정을 떠나는 동안 당신을 인정하고 싶지 않았습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("소수의 나쁜 사람들은 당신이 유명해지고, 사랑받는 것이 싫었습니다.", NamedTextColor.GRAY))
                }
            }
            27-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("따라서 이러한 시련들을 당신에게 주었습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("따라서 당신을 깎아 내리고, 모욕하고, 이 외의 끔찍한 만행들을 일으켰습니다.", NamedTextColor.GRAY))
                }
            }
            32-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("하지만,", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            35-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("당신은 이러한 시련들을 이겨냈습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("당신은 그들을 이겨내었습니다.", NamedTextColor.GRAY))
                }
            }
            40-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("당신이 이 자리에 오기까지의 시련들을 버텨냈습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("당신이 이 자리에 오기까지의 그들의 만행들을 버텨냈습니다.", NamedTextColor.GRAY))
                }
            }
            45-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("그리고 당신은,", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            48-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("앞으로의 시련들도 이겨 낼 것입니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("앞으로의 불행, 시련, 고통들도 이겨 낼 것입니다.", NamedTextColor.GRAY))
                }
            }
            53 -> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("당신은 이 여정이 끝난 자리에서 일어났습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.sendMessage(text("당신은 이 자리에서 일어났습니다.", NamedTextColor.GRAY))
                }
            }
            58 -> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("무기와 도구, 의지와 사랑을 챙겼습니다.", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            63-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("그리고 당신은,", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                }
            }
            67-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text(""), text("\"진짜 모험\"을 시작하게 됩니다.", NamedTextColor.GRAY), of(ofSeconds(5), ofSeconds(8), ofSeconds(5))))
                    it.sendMessage(text("더 나은 미래를 위해 앞으로 나아가게 됩니다.", NamedTextColor.GRAY))
                }
            }
            75-> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text("코마님의 구독자 10만명을 축하드립니다!", NamedTextColor.GREEN), text(""), of(ofSeconds(0), ofSeconds(8), ofSeconds(0))))
                    it.playSound(it.location, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1000F, 1F)
                }
            }
            78 -> {
                server.onlinePlayers.forEach {
                    it.showTitle(title(text("코마님의 구독자 10만명을 축하드립니다!", NamedTextColor.GREEN), text("THE END"), of(ofSeconds(0), ofSeconds(8), ofSeconds(0))))
                    it.playSound(it.location, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 1000F, 1F)
                }
            }
            81-> {
                server.onlinePlayers.forEach {
                    it.sendMessage(text("LLLLLLLIVVIVVIVVIVVXXXXX."))
                    it.sendMessage(text("Special Thanks: komq, PatrickKR, Twitter Followers\n"))
                    it.sendMessage(text("코마님의 구독자 10만명을 진심으로 축하드립니다!\n"))
                    it.sendMessage(text("- Hyeon."))

                    it.showTitle(title(text(""), text("\"그의 첫 여정이 끝나자, 세계는 기다렸다는 듯이 모든 시련을 없애주었다.\"", NamedTextColor.GRAY), of(ofSeconds((1.5).toLong()), ofSeconds(8), ofSeconds((1.5).toLong()))))
                    it.removePotionEffect(PotionEffectType.SLOW)
                    it.removePotionEffect(PotionEffectType.BLINDNESS)
                    it.removePotionEffect(PotionEffectType.LEVITATION)
                    server.scheduler.cancelTasks(getInstance())
                    HandlerList.unregisterAll(getInstance())
                }
            }
        }
    }
}