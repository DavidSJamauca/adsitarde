/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Rol;
import co.edu.sena.adsi.jpa.sessions.RolFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David Jamauca
 */
@Path("roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolRest {

    
    @EJB
    private RolFacade rolEJB;
    
   
    @GET
    public List<Rol> findAll(){
        return rolEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    public Rol findById(@PathParam("id") String id){
        return rolEJB.find(id);
    }
    
    @POST
    public void create(Rol rol){
        rolEJB.create(rol);
    }
    
    
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") String id, Rol rol){
        rolEJB.edit(rol);
    }
}