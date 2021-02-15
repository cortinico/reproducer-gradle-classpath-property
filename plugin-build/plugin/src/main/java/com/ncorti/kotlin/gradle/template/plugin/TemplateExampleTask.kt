package com.ncorti.kotlin.gradle.template.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class TemplateExampleTask : DefaultTask() {

    init {
        description = "Just a sample template task"

        // Don't forget to set the group here.
        // group = BasePlugin.BUILD_GROUP
    }

    @get:Internal
    abstract val configurationName: Property<String>

    @get:Classpath
    @get:InputFiles
    val configuration: Configuration
        get() = project.configurations.getByName(configurationName.get())

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun sampleAction() {
        logger.lifecycle("configurationName is: ${configurationName.orNull}")
        logger.lifecycle("configuration is: $configuration")

        outputFile.get().asFile.writeText("$configurationName")
    }
}
