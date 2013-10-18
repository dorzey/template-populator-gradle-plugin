package my.plugin

import com.google.common.io.CharStreams
import com.google.common.io.Files
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.stringtemplate.v4.ST;



class PopulateTemplatesTask extends DefaultTask {

    @TaskAction
    def populate() {
        project.templatesToPopulate.each {
            logger.info("Template ${it.name}")
            logger.debug("Source File: ${it.templateFile}")
            logger.debug("Output File: ${it.outputFile}")

            def populatedString = populateTemplate(it.templateFile, System.getenv())
            writeToFile(populatedString, it.outputFile)
        }
    }

    def writeToFile(aString, outputFile) {
        Files.write(aString.getBytes(), new File(outputFile))
    }

    def populateTemplate(sourceFile, envVarMap) {
        def input = new FileInputStream(sourceFile)
        String stringFromStream = CharStreams.toString(new InputStreamReader(input, "UTF-8"));
        ST st = new ST(stringFromStream)
        st.add('ENV', envVarMap)
        return st.render()
    }

}
