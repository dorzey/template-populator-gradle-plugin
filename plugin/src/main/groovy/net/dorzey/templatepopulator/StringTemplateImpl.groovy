package net.dorzey.templatepopulator

import com.google.common.io.CharStreams
import com.google.common.io.Files
import org.stringtemplate.v4.ST

class StringTemplateImpl implements PopulateTemplateInterface{

    @Override
    def populate(templateFile, outputFile) {
        def populatedString = populateTemplate(templateFile, System.getenv())
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
        return st.render()
    }
}

