package net.dorzey.templatepopulator

import com.google.common.io.CharStreams
import spock.lang.Specification

class StringTemplateImplSpec extends Specification {
    private static final String SOURCE_FILE = 'src/test/resources/string_template_test.txt'
    private static final String OUTPUT_FILE = 'output.txt'

    def stringTemplateImpl = Spy(StringTemplateImpl)

    def setup() {
        stringTemplateImpl.getEnvVars() >> [test_env_var: 'expected_value']
    }

    def "should call writeToFile once"() {
        when:
            stringTemplateImpl.populate(SOURCE_FILE, OUTPUT_FILE)
        then:
            1 * stringTemplateImpl.writeToFile(_, _)
    }

    def "should call populateTemplate once"() {
        when:
            stringTemplateImpl.populate(SOURCE_FILE, OUTPUT_FILE)
        then:
            1 * stringTemplateImpl.populateTemplate(_, _)
    }

    def "should populate the template with the supplied environment variables"(){
        when:
            stringTemplateImpl.populate(SOURCE_FILE, OUTPUT_FILE)
        then:
            def reader = new InputStreamReader(new FileInputStream(OUTPUT_FILE), "UTF-8")
            CharStreams.toString(reader) == 'expected_value'
            reader.close()
    }

    def cleanupSpec() {
        new File(OUTPUT_FILE).delete()
    }

}
