class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        '/'(controller: 'start', action: 'index')
        '/admin'(view: '/admin/index')
        "500"(view: '/error')
    }
}
