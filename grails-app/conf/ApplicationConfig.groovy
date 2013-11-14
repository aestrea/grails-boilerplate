println "Loading ApplicationConfig.groovy"

health.page.password = "p4ssw01rd"


environments {
    development {
        try {
            app.git.reference = "git rev-parse HEAD".execute().text
        } catch (Exception e) {
            println "could not get git revision because of $e"
        }
    }
}

if( Environment.developmentMode ){
    try {
        app.git.reference = "git rev-parse HEAD".execute().text.replace('\n', '')
    } catch (Exception e) {
        println "could not get git revision because of $e"
    }
}
