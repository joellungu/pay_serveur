package org.acme.controllers;

import org.acme.models.Commande;
import org.acme.models.Faq;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("commande")
public class CommandeController {


    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Commande.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Commande commande){
        commande.persist();
        return Response.ok(commande).build();
    }

    @GET
    @Path("entreprise/{idBoutique}/{date}")
    @Transactional
    public List<Commande> allEntreprise(@PathParam("idBoutique") long idBoutique, @PathParam("date") String date){
        HashMap params = new HashMap();
        params.put("idBoutique",idBoutique);
        params.put("date",date);

        List<Commande> liste = Commande.list("idBoutique =:idBoutique and date =:date",params);
        return liste;
    }

    @GET
    @Path("utilisateur/{idUtilisateur}/{date}")
    @Transactional
    public List<Commande> allUtilisateur(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("date") String date){
        HashMap params = new HashMap();
        params.put("idUtilisateur",idUtilisateur);
        params.put("date",date);

        List<Commande> liste = Commande.list("idBoutique =:idBoutique and date =:date",params);
        return liste;
    }

    @GET
    @Path("journalier/{idBoutique}/{date}")
    @Transactional
    public List<Commande> allJournalier(@PathParam("idBoutique") long idBoutique, @PathParam("date") String date){
        HashMap params = new HashMap();
        params.put("idBoutique",idBoutique);
        params.put("date",date);
        //
        List<Commande> liste = Commande.list("idBoutique =:idBoutique and date =:date",params);
        return liste;
    }

}
