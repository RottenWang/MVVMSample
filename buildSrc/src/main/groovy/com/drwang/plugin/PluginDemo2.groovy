package com.drwang.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class PluginDemo2 implements Plugin<Project> {
    @Override
    void apply(Project target) {
        def extension = target.extensions.create('code2', ExtensionDemo)
        target.afterEvaluate {
            println "hello ${extension.name}!"
        }
    }
}
