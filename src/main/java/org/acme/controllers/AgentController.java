package org.acme.controllers;

import org.acme.models.Agent;
import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("agent")
public class AgentController {


    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        System.out.println("Cool!!");
        return Response.ok(Agent.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Agent agent){
        agent.persist();
        return Response.ok(agent).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Agent.deleteById(id);
        if(delete){
            return Response.ok("Agent supprimé avec succès").build();
        }else{
            return Response.ok("Cette Agent n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Agent agent) {
        Agent agent1 = Agent.findById(agent.id);
        if(agent1 == null){
            return Response.serverError().build();
        }
        //
        agent1.nom = agent.nom;
        agent1.postnom = agent.postnom;
        agent1.prenom = agent.prenom;
        agent1.email = agent.email;
        agent1.telephone = agent.telephone;
        agent1.adresse = agent.adresse;
        agent1.sexe = agent.sexe;
        agent1.dateNaissance = agent.dateNaissance;
        agent1.suspendu = agent.suspendu;
        //
        return Response.ok(agent1).build();
    }

}
