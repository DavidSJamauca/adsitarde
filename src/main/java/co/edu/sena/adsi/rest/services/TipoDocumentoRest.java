/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.TipoDocumento;
import co.edu.sena.adsi.jpa.sessions.TipoDocumentoFacade;
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
@Path("tipos_documentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoDocumentoRest {
    
  @EJB
    private TipoDocumentoFacade tipoDocumentoEJB;
    
    @GET
    public List<TipoDocumento> findAll(){
        return tipoDocumentoEJB.findAll();
    }
    
    
    @GET
    @Path("{id}")
    public TipoDocumento findById(@PathParam("id") Integer id){
        return tipoDocumentoEJB.find(id);
    }
  
    @POST
    public void create(TipoDocumento tipoDocumento){
        tipoDocumentoEJB.create(tipoDocumento);
    }
    
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, TipoDocumento tipoDocumento){
        tipoDocumentoEJB.edit(tipoDocumento);
    }
}