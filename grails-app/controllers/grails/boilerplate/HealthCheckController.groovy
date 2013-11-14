package grails.boilerplate

class HealthCheckController {

    def index() {
        if (params.password == grailsApplication.config.health.page.password) {
            def model = [:]

            render view: 'status', model: model
        } else {
            response.sendError 404
        }
    }
}
