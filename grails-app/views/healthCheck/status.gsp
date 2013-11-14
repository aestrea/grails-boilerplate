<%@ page import="grails.util.Environment" %>
<html>
<head>
    <title><g:message code="health.check.label"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
    .health-check {
        background: white;
        padding: 30px;
    }

    h1, h2, h3, h4, h5 {
        text-align: left !important;
    }

    h4 {
        border-bottom: 2px solid #cacaca;
    }

    .label-orange {
        padding: 5px;
    }

    .label-orange:after {
        content: ",";
    }

    .label-orange:last-child:after {
        content: '';
    }

    .health-check .section > div{
        margin-top: 35px;
    }

    .health-check li{
        list-style: none;
        font-size: 17px;
        line-height: 27px;
    }
    </style>
</head>

<body>

<div class="section health-check">

    <g:set var="gitReference"
           value="${g.meta(name: 'app.git.reference') ?: grailsApplication.config.app.git.reference}"/>
    <h3>${meta(name: 'app.name')}${g.meta(name: 'app.version')} ( ${gitReference} )</h3>

    <div class="section">
        <div>
            <h4>Grails</h4>
            <ul>
                <li>Version: <span class="label-orange">${g.meta(name: 'app.grails.version')}</span></li>
                <li>Environment: <span class="label-orange">${Environment.current.name}</span></li>
                <li>Java: <span class="label-orange">${System.getProperty('java.runtime.name')} (${System.getProperty('java.runtime.version')})</span></li>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
