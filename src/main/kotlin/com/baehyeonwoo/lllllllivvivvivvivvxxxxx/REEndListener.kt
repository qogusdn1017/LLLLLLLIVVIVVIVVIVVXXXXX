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

import com.github.monun.tap.effect.playFirework
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.block.data.type.Bed
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class REEndListener : Listener {

    /***
     * @author BaeHyeonWoo
     */

    private fun getInstance(): Plugin {
        return HardenedPluginMain.instance
    }

    @EventHandler
    fun onEntityDeathEvent(e: EntityDeathEvent) {

        val entity: LivingEntity = e.entity
        val firework = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build()

        fun ReEnd() {
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
            Bukkit.getScheduler().scheduleSyncRepeatingTask(getInstance(), ReEndTask(), 20, 20)
        }

        Bukkit.getOnlinePlayers().forEach {
            it.sendTitle("", "${ChatColor.GRAY}\"과연 이것이 진정 옳은 값을 되돌려 받을 수 있었을까?\"", 0, 150, 0)
            it.sendMessage(
                Component.text().color(TextColor.color(0x808080))
                    .content("끝없이 시도하라. 끝없이 도전하라. 비록, 그것이 옳은 값으로 되돌려 받지 못하더라도.").build()
            )
            it.sendMessage(Component.text().content("이스터에그 #2: 안녕히. 그리고 한번 더."))
            ReEnd()
        }
    }
}
