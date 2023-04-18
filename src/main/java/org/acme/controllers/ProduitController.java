package org.acme.controllers;

import org.acme.models.Produit;
import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("produit")
public class ProduitController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("id") long id){
        Produit produit = Produit.findById(id);
        if(produit == null){
            return Response.serverError().build();
        }
        return Response.ok(produit).build();
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
        return Response.ok(Produit.find("idBoutique",id).list()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Produit produit){
        produit.persist();
        return Response.ok(produit).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Produit.deleteById(id);
        if(delete){
            return Response.ok("Produit supprimé avec succès").build();
        }else{
            return Response.ok("Ce produit n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Produit produit) {
        Produit produit1 = Produit.findById(produit.id);
        if(produit1 == null){
            return Response.serverError().build();
        }
        //
        produit1.nom = produit.nom;
        produit1.type = produit.type;
        produit1.prix = produit.prix;
        produit1.devise = produit.devise;
        produit1.quantite = produit.quantite;
        produit1.poids = produit.poids;
        produit1.unite = produit.unite;
        produit1.photo = produit.photo;
        //
        return Response.ok(produit1).build();
    }

    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Produit produit = Produit.findById(id);
        return produit.photo;
    }

}
