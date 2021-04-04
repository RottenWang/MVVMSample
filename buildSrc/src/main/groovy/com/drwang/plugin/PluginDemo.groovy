package com.drwang.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project;

class PluginDemo implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('code', ExtensionDemo)
        project.afterEvaluate {
            println "hello ${extension.name}!"
        }
        def transform = new TransformDemo()
        def baseExtension = project.extensions.getByType(BaseExtension)
        baseExtension.registerTransform(transform)
    }
}
