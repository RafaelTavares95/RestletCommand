/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletserver;

import edu.ifpb.pos.restletserver.resources.PersonResource;
import edu.ifpb.pos.restletserver.resources.PersonResourceFilter;
import edu.ifpb.pos.restletserver.resources.UserResource;
import edu.ifpb.pos.restletserver.resources.UserResourceFilter;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 *
 * @author Rafael
 */
public class App {
    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        
        Router router = new Router();
        router.attach("/person", PersonResource.class);
        router.attach("/user", UserResource.class);
        router.attach("/person/{code}", PersonResourceFilter.class);
        router.attach("/user/{code}", UserResourceFilter.class);
        
        Application application = new Application();
        application.setInboundRoot(router);
        
        component.getDefaultHost().attach("/app", application);
        component.start();

    }
}
