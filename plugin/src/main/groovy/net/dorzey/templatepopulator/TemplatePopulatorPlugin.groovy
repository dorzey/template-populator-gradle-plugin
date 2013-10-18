package net.dorzey.templatepopulator

import org.gradle.api.Plugin
import org.gradle.api.Project

class TemplatePopulatorPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def populateTemplates = project.container(PopulateTemplate)

        project.extensions.templatesToPopulate = populateTemplates

        project.task('populateTemplates', type: PopulateTemplatesTask)
    }
}