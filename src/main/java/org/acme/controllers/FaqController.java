package org.acme.controllers;

import org.acme.models.Agent;
import org.acme.models.Faq;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("faq")
public class FaqController {


    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Faq.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Faq faq){
        faq.persist();
        return Response.ok(faq).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Faq.deleteById(id);
        if(delete){
            return Response.ok("Faq supprimé avec succès").build();
        }else{
            return Response.ok("Cette Faq n'a pu etre supprimé").build();
        }
    }

}
