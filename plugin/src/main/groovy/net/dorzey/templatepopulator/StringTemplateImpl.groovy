package net.dorzey.templatepopulator

import com.google.common.io.CharStreams
import com.google.common.io.Files
import org.stringtemplate.v4.ST

class StringTemplateImpl implements PopulateTemplateInterface{
    def environment = new Environment()

    @Override
    def populate(templateFile, outputFile) {
        def populatedString = populateTemplate(templateFile, getEnvVars())
        writeToFile(populatedString, outputFile)
    }

    def writeToFile(aString, outputFile) {
        Files.write(aString.getBytes(), new File(outputFile))
    }

    def populateTemplate(sourceFile, envVarMap) {
        def input = new FileInputStream(sourceFile)
        String stringFromStream = CharStreams.toString(new InputStreamReader(input, "UTF-8"));
        ST st = new ST(stringFromStream)
        st.add('ENV', envVarMap)
        input.close()
        return st.render()
    }

    def getEnvVars(){
        environment.getEnvVars()
    }
}

class Environment{
    def getEnvVars(){
        return System.getenv()
    }
}

