/*
 *    Copyright 2023 Roman Likhachev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.yugyd.hida.buildlogic.convention.IMPLEMENTATION
import com.yugyd.hida.buildlogic.convention.KAPT
import com.yugyd.hida.buildlogic.convention.libs
import dagger.hilt.android.plugin.HiltExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class HiltAndroidConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("kotlin-kapt")

            dependencies {
                add(IMPLEMENTATION, libs.findLibrary("hilt-android").get())
                add(KAPT, libs.findLibrary("hilt-dagger-compiler").get())
            }

            project.extensions.configure<HiltExtension> {
                enableAggregatingTask = true
            }
        }
    }
}
