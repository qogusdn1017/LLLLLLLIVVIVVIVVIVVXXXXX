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

package com.baehyeonwoo.lvx.plugin.commands

import com.baehyeonwoo.lvx.plugin.objects.LVXObject.getInstance
import com.baehyeonwoo.lvx.plugin.objects.LVXObject.startGame
import com.baehyeonwoo.lvx.plugin.objects.LVXObject.stopGame
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text

/***
 * @author BaeHyeonWoo
 */

class LVXKommand {
    fun lvxKommand() {
        getInstance().kommand {
            register("lvx") {
                executes {
                    sender.sendMessage(text("LVX 0.0.2\nChallenging survival for komq."))
                }
                then("start") {
                    requires { isOp }
                    executes {
                        startGame()
                    }
                }
                then("stop") {
                    executes {
                        stopGame()
                    }
                }
            }
        }
    }
}