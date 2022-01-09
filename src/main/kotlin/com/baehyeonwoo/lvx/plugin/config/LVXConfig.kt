package com.baehyeonwoo.lvx.plugin.config

import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import java.io.File

object LVXConfig {
    @Config
    var debugMode = false

    @Config
    var monsterDamage = 3

    @Config
    var jagangDucheon = true

    @Config
    var playerUuid = arrayListOf(
        "389c4c9b-6342-42fc-beb3-922a7d7a72f9",
        "5082c832-7f7c-4b04-b0c7-2825062b7638"
    )

    fun load(configFile: File) {
        ConfigSupport.compute(this, configFile)
    }
}