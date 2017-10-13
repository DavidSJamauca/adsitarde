
package co.edu.sena.adsi.rest.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
    
    public ApplicationConfig(){
    packages("co.edu.sena.adsi.rest.auth;co.edu.sena.adsi.rest.services");
    register(RolesAllowedDynamicFeature.class);
    }
}
    