package org.acme.controllers;

import org.acme.models.Boutique;
import org.acme.models.Business;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("business")
public class BusinessController {
    @GET
    @Path("all/{idBoutique}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBusiness(@PathParam("idBoutique") Long idBoutique){
        System.out.println("le id: "+idBoutique);
        List<Business> liste = Business.list("idBoutique",idBoutique);
        return Response.ok(liste).build();
    }

    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBusiness(){
        //System.out.println("le id: "+idBoutique);
        List<Business> liste = Business.listAll();
        return Response.ok(liste).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Business business){
        business.persist();
        return Response.ok(business).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Business.deleteById(id);
        if(delete){
            return Response.ok("Utilisateur supprimé avec succès").build();
        }else{
            return Response.ok("Cette utilisateur n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Business business) {
        Business business1 = Business.findById(business.id);
        if(business1 == null){
            return Response.serverError().build();
        }
        //
        business1.adresse = business.adresse;
        business1.nomUtilisateur = business.nomUtilisateur;
        business1.mdp = business.mdp;
        business1.nomBusiness = business.nomBusiness;
        business1.role = business.role;
        business1.photo = business.photo;
        //
        return Response.ok(business1).build();
    }

    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Business business = Business.findById(id);
        return business.photo;
    }

}
