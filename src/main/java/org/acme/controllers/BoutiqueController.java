package org.acme.controllers;

import org.acme.models.Boutique;
import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("boutique")
public class BoutiqueController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("id") long id){
        Boutique boutique = Boutique.findById(id);
        if(boutique == null){
            return Response.serverError().build();
        }
        return Response.ok(boutique).build();
    }

    @GET
    @Path("login/{telephone}/{motdepasse}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("telephone") String telephone,
                                   @PathParam("motdepasse") String motdepasse){
        HashMap<String, Object> params = new HashMap<>();
        params.put("telephone",telephone);
        params.put("motdepasse",motdepasse);

        Boutique boutique = Boutique.find("telephone =:telephone and motdepasse =:motdepasse ",params).firstResult();
        if(boutique == null){
            return Response.serverError().build();
        }
        return Response.ok(boutique).build();
    }

    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Boutique.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Boutique boutique){
        boutique.persist();
        return Response.ok(boutique).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Boutique.deleteById(id);
        if(delete){
            return Response.ok("Utilisateur supprimé avec succès").build();
        }else{
            return Response.ok("Cette utilisateur n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Boutique boutique) {
        Boutique boutique1 = Boutique.findById(boutique.id);
        if(boutique1 == null){
            return Response.serverError().build();
        }
        //
        boutique1.nom = boutique.nom;
        boutique1.postnom = boutique.postnom;
        boutique1.prenom = boutique.prenom;
        boutique1.sexe = boutique.sexe;
        boutique1.etatCivil = boutique.etatCivil;
        boutique1.email = boutique.email;
        boutique1.telephone = boutique.telephone;
        boutique1.adresse = boutique.adresse;
        boutique1.denomination = boutique.denomination;
        boutique1.adresseEtablissement = boutique.adresseEtablissement;
        boutique1.RCCM = boutique.RCCM;
        boutique1.provinceSiege = boutique.provinceSiege;
        boutique1.typeEtablissement = boutique.typeEtablissement;
        boutique1.categorie = boutique.categorie;
        boutique1.nombreEtablissement = boutique.nombreEtablissement;
        boutique1.photo = boutique.photo;
        boutique1.codePromo = boutique.codePromo;
        boutique1.motDePasse = boutique.motDePasse;
        //
        return Response.ok(boutique1).build();
    }

    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Boutique boutique = Boutique.findById(id);
        return boutique.photo;
    }

}
