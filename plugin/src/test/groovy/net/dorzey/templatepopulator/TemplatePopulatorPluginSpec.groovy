package net.dorzey.templatepopulator

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class TemplatePopulatorPluginSpec extends Specification {
    def project = ProjectBuilder.builder().build()

    def setup() {
        project.apply plugin: 'template-populator'
    }

    def "should have populateTemplates task"() {
        expect:
            project.tasks.populateTemplates instanceof PopulateTemplatesTask
    }
}
