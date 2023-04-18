package org.acme.controllers;

import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("utilisateur")
public class UtilisateurController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("id") long id){
        Utilisateur utilisater = Utilisateur.findById(id);
        if(utilisater == null){
            return Response.serverError().build();
        }
        return Response.ok(utilisater).build();
    }

    @GET
    @Path("login/{telephone}/{motdepasse}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("telephone") String telephone,
                                   @PathParam("motdepasse") String motdepasse){
        HashMap<String, Object> params = new HashMap<>();
        params.put("telephone",telephone);
        params.put("motdepasse",motdepasse);

        Utilisateur utilisater = Utilisateur.find("telephone =:telephone and motdepasse =:motdepasse ",params).firstResult();
        if(utilisater == null){
            return Response.serverError().build();
        }
        return Response.ok(utilisater).build();
    }

    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Utilisateur.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Utilisateur utilisateur){
        utilisateur.persist();
        return Response.ok(utilisateur).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Utilisateur.deleteById(id);
        if(delete){
            return Response.ok("Utilisateur supprimé avec succès").build();
        }else{
            return Response.ok("Cette utilisateur n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = Utilisateur.findById(utilisateur.id);
        if(utilisateur1 == null){
            return Response.serverError().build();
        }
        //
        utilisateur1.nomUtilisateur = utilisateur.nomUtilisateur;
        utilisateur1.motdepasse = utilisateur.motdepasse;
        utilisateur1.telephone = utilisateur.telephone;
        utilisateur1.photo = utilisateur.photo;
        //
        return Response.ok(utilisateur1).build();
    }

    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Utilisateur utilisateur = Utilisateur.findById(id);
        return utilisateur.photo;
    }

}
