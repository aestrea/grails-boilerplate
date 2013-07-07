import grails.util.Environment
import org.codehaus.groovy.grails.commons.ConfigurationHolder

def useLessResource = {_delegate, filename ->

    def filenames = ConfigurationHolder.config.less.filenames
    if( !filenames || !filenames.contains(filename) ){
        throw new RuntimeException("Less resource (${filename}.less) is not pre-defined in config (less.filenames).")
    }

    if (Environment.warDeployed) {
        _delegate.invokeMethod('resource', [url: "css/${filename}.css", disposition: 'head'])
    } else {
        _delegate.invokeMethod('resource', [url: "less/${filename}.less", attrs: [rel: 'stylesheet/less', type: 'css'], disposition: 'head'])
    }
}

modules = {

    application {
        dependsOn 'jquery', 'bootstrap'
        resource url:'js/application.js'
        resource url: 'css/scaffolding.css'
    }

    //==== Bootstrap ====//
    'bootstrap'{
        dependsOn 'bootstrap-js'
        useLessResource(delegate, 'bootstrap')
    }

    'bootstrap-js'{
        dependsOn 'jquery'
        resource id: 'js', url: [dir: 'js/bootstrap', file: 'bootstrap.min.js'], disposition: 'head', nominify: true
    }

    //==== Utilities ====//
    /* Less */
    'less'{
        resource url: 'js/less/less-1.3.3.min.js', nominify: true, disposition: 'head'
    }
}
