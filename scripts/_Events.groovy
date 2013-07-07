import org.lesscss.LessCompiler
import org.codehaus.groovy.grails.commons.ConfigurationHolder

//void preserveApplicationProperties(){
//    try{
//        AntBuilder antBuilder = new AntBuilder()
//        antBuilder.copy(file: "application.properties", toFile: "application.properties.original")
//    }catch(e){
//        e.printStackTrace()
//    }
//}
//
//void returnOriginalApplicationProperties() {
//    try {
//        AntBuilder antBuilder = new AntBuilder()
//        antBuilder.move(file: "application.properties.original", toFile: "application.properties")
//    } catch (e) { e.printStackTrace() }
//}
//
//void appendGitReferenceToApplicationProperties(){
//    event "StatusUpdate", ["HEALTH CHECK: appending git reference"]
//    try {
//        def gitRef = "git rev-parse HEAD".execute().text.replace('\n', '')
//        if (gitRef) {
//            event "StatusUpdate", ["HEALTH CHECK: appending git reference: ${gitRef}"]
//            Properties properties = new Properties()
//            properties.load(new FileInputStream("application.properties"))
//            properties['app.git.reference'] = gitRef
//            properties.store(new FileOutputStream("application.properties"), 'Grails Metadata file')
//        }
//    } catch (Exception e) {
//        e.printStackTrace()
//    }
//}

void compileLessCss(name, stagingDir) {

    // Instantiate the LESS compiler
    def lessCompiler = new LessCompiler()

    // Or compile LESS input file to CSS output file
    event "StatusUpdate", ["LESS CSS: compiling .less files"]

    def filenames = ConfigurationHolder.config.less.filenames ?: []
    filenames.each{ filename ->
        event "StatusUpdate", ["LESS CSS: Compiling ${filename}.less"]
        lessCompiler.compile(
                new File(stagingDir, "less/${filename}.less"),
                new File(stagingDir, "css/${filename}.css"))
    }

    event "StatusUpdate", ["LESS CSS: compilation done"]
}

eventCreateWarStart = { name, stagingDir ->
    compileLessCss name, stagingDir
}

eventWarStart = {
//    preserveApplicationProperties()
//    appendGitReferenceToApplicationProperties()
}

eventWarEnd = {
//    returnOriginalApplicationProperties()
}
