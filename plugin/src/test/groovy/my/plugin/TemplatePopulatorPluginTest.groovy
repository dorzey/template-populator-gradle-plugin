package my.plugin

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.fest.assertions.Assertions.assertThat

class TemplatePopulatorPluginTest {

    @Test
    def void should_add_environmentTemplateApply_to_project() {
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'template-populator'

        assertThat(project.tasks.populateTemplates).isInstanceOf(PopulateTemplatesTask)
        def actualNamedDomainObject = (NamedDomainObjectContainer) project.extensions.templatesToPopulate
    }
}
