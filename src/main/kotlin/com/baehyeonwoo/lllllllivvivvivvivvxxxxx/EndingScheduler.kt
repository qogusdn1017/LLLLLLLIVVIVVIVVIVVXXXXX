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
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/***
 * @author BaeHyeonWoo
 */

class EndingScheduler : Runnable {
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
                    it.playSound(it.location, "komq.ending", SoundCategory.MASTER, 1000F, 1F)
                    it.sendTitle("", "${ChatColor.GRAY}당신의 첫 여정이 끝났습니다.", 5, 100, 5)
                }
            }
            17 -> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}재미있으셨나요?", 5, 100, 5)
                }
            }
            22-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}세계는 당신이 여정을 떠나는 동안 당신을 인정하고 싶지 않았습니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}몇몇 좋지 않은 사람들은 당신이 유명해지고, 사랑받는 것이 싫었습니다.").build())
                }
            }
            27-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}따라서 이러한 시련들을 당신에게 주었습니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}따라서 당신을 깎아 내리고, 모욕하고, 이 외의 끔찍한 짓을 하였습니다.").build())
                }
            }
            32-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}하지만,", 5, 100, 5)
                }
            }
            35-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}당신은 이러한 시련들을 이겨냈습니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}당신은 그들을 이겨내었습니다.").build())
                }
            }
            40-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}당신이 이 자리에 오기까지의 시련들을 버텨냈습니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}당신이 이 구독자 수치에 오기까지의 그들의 만행들을 버텨냈습니다.").build())
                }
            }
            45-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}그리고 당신은,", 5, 100, 5)
                }
            }
            48-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}앞으로의 시련들도 이겨 낼 것입니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}앞으로의 불행, 시련, 고통들도 이겨 낼 것입니다.").build())
                }
            }
            53 -> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}당신은 이 여정이 끝난 자리에서 일어났습니다.", 5, 100, 5)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}당신은 이 구독자 10만명의 자리에서 일어났습니다.").build())
                }
            }
            58 -> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY} 무기와 도구, 의지와 사랑을 챙겼습니다.", 5, 100, 5)
                }
            }
            63-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}그리고 당신은,", 5, 100, 5)
                }
            }
            67-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("", "${ChatColor.GRAY}\"진짜 모험\"을 시작하게 됩니다.", 25, 150, 25)
                    it.sendMessage(Component.text().color(TextColor.color(0x808080)).content("${ChatColor.GRAY}더 나은 미래를 위해 앞으로 나아가게 됩니다.").build())
                }
            }
            75-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendTitle("${ChatColor.GREEN}코마님의 구독자 10만명을 축하드립니다!", "", 0, 150, 0)
                    it.playSound(it.location, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, SoundCategory.MASTER, 1000f, 1f)
                }
                Bukkit.getScheduler().runTaskLater(getInstance(), Runnable {
                    Bukkit.getOnlinePlayers().forEach {
                        it.sendTitle("${ChatColor.GREEN}코마님의 구독자 10만명을 축하드립니다!", "THE END", 0, 150, 0)
                        it.playSound(it.location, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, SoundCategory.MASTER, 1000f, 1f)
                    }
             }, 20 * 3)
            }
            81-> {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendMessage(Component.text().content("LLLLLLLIVVIVVIVVIVVXXXXX Plugin").build())
                    it.sendMessage(Component.text().content("by BaeHyeonWoo\n").build())
                    it.sendMessage(Component.text().content("제작자 소셜:\n").build())
                    it.sendMessage(Component.text().content("Twitter: @qogusdn1017").build())
                    it.sendMessage(Component.text().content("GitHub: https://github.com/qogusdn1017\n").build())
                    it.sendMessage(Component.text().content("특별히 감사한 분들:\n").build())
                    it.sendMessage(Component.text().content("코마님, 이 플러그인을 플레이 해주신 분").build())
                    it.sendMessage(Component.text().content("PatrickKR님, 코드에 도움을 주신분 (https://github.com/patrick-mc)").build())
                    it.sendMessage(Component.text().content("마지막으로 영상을 시청해 주신 시청자 분들.\n").build())
                    it.sendMessage(Component.text().content("모두 감사드립니다.\n\n").build())
                    it.sendMessage(Component.text().content("코마님의 구독자 10만명을 다시한번 진심으로 축하드립니다!\n\n").build())
                    it.sendMessage(Component.text().content("- Hyeon.\n\n").build())
                    it.sendMessage(Component.text().content("추신: \"진짜 모험\"의 이야기로 만들어질 \"코마의 모험\"을 기대해주세요! 현재 제작중이랍니다 :D").build())
                    it.sendTitle("", "${ChatColor.GRAY}\"그의 첫 여정이 끝나자, 세계는 기다렸다는 듯이 모든 시련을 없애주었다.\"", 0, 150, 0)
                    it.removePotionEffect(PotionEffectType.SLOW)
                    it.removePotionEffect(PotionEffectType.BLINDNESS)
                    it.removePotionEffect(PotionEffectType.LEVITATION)
                    Bukkit.getScheduler().cancelTasks(getInstance())
                    HandlerList.unregisterAll(getInstance())
                    Bukkit.getPluginManager().registerEvents(REEndListener(), getInstance())
                }
            }
        }
    }
}