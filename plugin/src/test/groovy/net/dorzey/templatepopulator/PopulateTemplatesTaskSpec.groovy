package net.dorzey.templatepopulator

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class PopulateTemplatesTaskSpec extends Specification{
    def project = ProjectBuilder.builder().build()
    def mockTemplateImpl = Mock(PopulateTemplateInterface)


    def setup(){
        project.task('populateTemplates', type: PopulateTemplatesTask)
        project.tasks.populateTemplates.populateTemplateImpl = mockTemplateImpl
        project.extensions.add('templatesToPopulate', getPopulateTemplateList())
    }

    def getPopulateTemplateList() {
        [
                new PopulateTemplate('one'),
                new PopulateTemplate('two'),
                new PopulateTemplate('three')
        ]
    }

    def "should call mockTemplateImpl.populate for each template in the list"(){
        when:
            project.tasks.populateTemplates.populate()
        then:
            3 * mockTemplateImpl.populate(_,_)
    }
}
