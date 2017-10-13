package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.rest.auth.DigestUtil;
import co.edu.sena.adsi.jpa.sessions.UsuarioFacade;
import co.edu.sena.adsi.rest.utils.SendEmail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author David Jamauca
 */
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioRest {
    
    @EJB
    private UsuarioFacade usuarioEJB;

    @GET
    //@RolesAllowed({"ADMIN"})
    public List<Usuario> findAll() {
        return usuarioEJB.findAll();
    }

    @GET
    @Path("{id}")
    public Usuario findById(@PathParam("id") Integer id) {
        return usuarioEJB.find(id);
    }
    
    @POST
    public Response create(Usuario usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuarioEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                if (usuarioEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    usuarioEJB.create(usuario);
                    
                    SendEmail enviarEmail = new SendEmail();
                    enviarEmail.sendEmailRegistroUsuario(
                    usuario, "password");
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario se creó correctamente!")).build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioRest.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }


    
    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Usuario usuario) {
         GsonBuilder gsonBuilder = new GsonBuilder();
         Gson gson = gsonBuilder.create();
        try{
         usuarioEJB.edit(usuario);
         return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario se actualizo Correctamente!")).build();
        }catch (Exception e){
            Logger.getLogger(UsuarioRest.class.getName()).log(Level.SEVERE,null,e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al Actualizar el Usuario!")).build();
        }
    }
}
    

