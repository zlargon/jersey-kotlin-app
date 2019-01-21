package com.zlargon

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

/**
 * This class launches the web application in an embedded Jetty container. This is the entry point to your application. The Java
 * command that is used for launching should fire this main method.
 */
object App {

    @JvmStatic
    fun main(args: Array<String>) {
        // The port that we should run on can be set into an environment variable
        // Look for that variable and default to 8080 if it isn't there.
        val webPort: String = System.getenv("PORT") ?: "8080"

        val server = Server(Integer.valueOf(webPort)!!)
        val root = WebAppContext()

        root.contextPath = "/"
        // Parent loader priority is a class loader setting that Jetty accepts.
        // By default Jetty will behave like most web containers in that it will
        // allow your application to replace non-server libraries that are part of the
        // container. Setting parent loader priority to true changes this behavior.
        // Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.isParentLoaderPriority = true

        val webappDirLocation = "src/main/webapp/"
        root.descriptor = "$webappDirLocation/WEB-INF/web.xml"
        root.resourceBase = webappDirLocation

        server.handler = root

        server.start()
        server.join()
    }
}
