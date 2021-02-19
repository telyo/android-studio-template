package com.github.telyo.androidstudiotemplate.services

import com.github.telyo.androidstudiotemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
