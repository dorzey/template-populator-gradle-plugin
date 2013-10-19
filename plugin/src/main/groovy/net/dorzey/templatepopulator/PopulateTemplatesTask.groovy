package net.dorzey.templatepopulator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class PopulateTemplatesTask extends DefaultTask {
    def populateTemplateImpl = new StringTemplateImpl()

    @TaskAction
    def populate() {
        project.templatesToPopulate.each {
            logger.info("Template ${it.name}")
            logger.debug("Source File: ${it.templateFile}")
            logger.debug("Output File: ${it.outputFile}")

            populateTemplateImpl.populate(it.templateFile, it.outputFile)
        }
    }


}