package com.telyo.template.services

import com.intellij.openapi.project.Project
import com.telyo.template.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
