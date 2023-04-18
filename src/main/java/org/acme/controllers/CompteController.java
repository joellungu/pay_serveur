package org.acme.controllers;

import org.acme.models.Compte;
import org.acme.models.Produit;
import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("compte")
public class CompteController {


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("id") long id){
        Compte compte = Compte.findById(id);
        if(compte == null){
            return Response.serverError().build();
        }
        return Response.ok(compte).build();
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
    @Path("all/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(@PathParam("id") long id){
        return Response.ok(Compte.find("idProprietaire",id).list()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Compte compte){
        compte.persist();
        return Response.ok(compte).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Compte.deleteById(id);
        if(delete){
            return Response.ok("Produit supprimé avec succès").build();
        }else{
            return Response.ok("Ce produit n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Compte compte) {
        Compte compte1 = Produit.findById(compte.id);
        if(compte1 == null){
            return Response.serverError().build();
        }
        //
        compte1.reseau = compte.reseau;
        compte1.numero = compte.numero;
        compte1.idProprietaire = compte.idProprietaire;
        //
        return Response.ok(compte1).build();
    }

    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Produit produit = Produit.findById(id);
        return produit.photo;
    }

}
