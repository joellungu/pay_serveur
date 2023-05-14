package org.acme.controllers;

import org.acme.models.Boutique;
import org.acme.models.Commande;
import org.acme.models.Faq;
import org.acme.models.Produit;

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
        if(!commande.produits.isEmpty()){
            commande.produits.forEach((p)->{
                //
                Produit produit = Produit.findById(p.get("id"));
                produit.quantite = produit.quantite - Integer.parseInt(p.get("quantite")+"");
                //
            });
        }
        return Response.ok(commande).build();
    }

    @GET
    @Path("entreprise/{idBoutique}/{date}")
    @Transactional
    public Response allEntreprise(@PathParam("idBoutique") long idBoutique, @PathParam("date") String date){
        HashMap params = new HashMap();
        params.put("idBusiness",idBoutique);
        params.put("date",date);

        List<Commande> liste = Commande.list("idBusiness =:idBusiness and date =:date",params);
        //List<Commande> liste = Commande.listAll();
        return Response.ok(liste).build();
    }

    @GET
    @Path("utilisateur/{idUtilisateur}/{date}")
    @Transactional
    public List<Commande> allUtilisateur(@PathParam("idUtilisateur") long idUtilisateur, @PathParam("date") String date){
        HashMap params = new HashMap();
        params.put("idUtilisateur",idUtilisateur);
        params.put("date",date);

        List<Commande> liste = Commande.list("idUtilisateur =:idUtilisateur and date =:date",params);
        return liste;
    }

    @GET
    @Path("/{idcommande}")
    @Transactional
    public Commande allJournalier(@PathParam("idcommande") long idcommande){

        return Commande.findById(idcommande);
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Commande commande) {
        Commande commande1 = Commande.findById(commande.id);
        if(commande1 == null){
            return Response.serverError().build();
        }
        //
        commande1.deja = commande.deja;
        commande1.type = commande.type;
        commande1.date = commande.date;
        //
        return Response.ok(commande1).build();
    }

}
