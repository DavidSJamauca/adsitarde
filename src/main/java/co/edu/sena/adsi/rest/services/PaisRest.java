/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Pais;
import co.edu.sena.adsi.jpa.sessions.PaisFacade;
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
@Path("paises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaisRest {
    

      @EJB
    private PaisFacade paisEJB;
    
    
    @GET
    public List<Pais> findAll(){
        return paisEJB.findAll();
    }
    
    
    @GET
    @Path("{id}")
    public Pais findById(@PathParam("id") String id){
        return paisEJB.find(id);
    }
    
    /**
     * Crear un pais
     * @param pais 
     */
    @POST
    public void create(Pais pais){
        paisEJB.create(pais);
    }
    
    /**
     * Edita un pais
     * @param id
     * @param pais 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") String id, Pais pais){
        paisEJB.edit(pais);
    }
}
