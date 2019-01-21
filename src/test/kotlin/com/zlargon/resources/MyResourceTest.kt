package com.zlargon.resources

import javax.ws.rs.core.Application

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest

import org.junit.Test
import org.junit.Assert.assertEquals

class MyResourceTest : JerseyTest() {

    override fun configure(): Application {
        return ResourceConfig(MyResource::class.java)
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    fun testGetIt() {
        val responseMsg = target().path("myresource").request().get(String::class.java)

        assertEquals("Hello, Jersey!", responseMsg)
    }
}
