---
layout: index
---

The plugin takes the environment variables and populates a <a href="http://www.stringtemplate.org/">StringTemplate</a> template.
The environment variables are accessible within the template via ```ENV.<env_var>```.

Example usage is shown below:
{% highlight groovy %}
apply plugin: 'template-populator'

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath group: 'net.dorzey.templatepopulator', name: 'templatepopulator', version: '0.0.1'
        classpath group: 'org.antlr', name: 'ST4', version: '4.0.7'
        classpath group: 'com.google.guava', name: 'guava', version: '15.0'
    }

}

templatesToPopulate {
        foo {
                templateFile = "src/resources/foo_template.txt"
                outputFile = "output/foo.txt"
        }
        bar {
                templateFile = "src/resources/bar_template.txt"
                outputFile = "output/bar.txt"
        }
}
defaultTasks 'populateTemplates'
{% endhighlight %}

