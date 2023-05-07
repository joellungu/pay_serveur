package org.acme.controllers;

import org.acme.models.Agent;
import org.acme.models.Ambassadeur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ambassadeur")
public class AmbassadeurController {


    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Ambassadeur.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Ambassadeur ambassadeur){
        ambassadeur.persist();
        return Response.ok(ambassadeur).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Ambassadeur.deleteById(id);
        if(delete){
            return Response.ok("Ambassadeur supprimé avec succès").build();
        }else{
            return Response.ok("Cette Ambassadeur n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Ambassadeur ambassadeur) {
        Ambassadeur ambassadeur1 = Agent.findById(ambassadeur.id);
        if(ambassadeur1 == null){
            return Response.serverError().build();
        }
        //
        ambassadeur1.nom = ambassadeur.nom;
        ambassadeur1.postnom = ambassadeur.postnom;
        ambassadeur1.prenom = ambassadeur.prenom;
        ambassadeur1.email = ambassadeur.email;
        ambassadeur1.telephone = ambassadeur.telephone;
        ambassadeur1.adresse = ambassadeur.adresse;
        ambassadeur1.sexe = ambassadeur.sexe;
        //
        return Response.ok(ambassadeur1).build();
    }

}
