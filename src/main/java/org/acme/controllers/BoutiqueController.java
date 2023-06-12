package org.acme.controllers;

import org.acme.models.Boutique;
import org.acme.models.Business;
import org.acme.models.Utilisateur;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

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
        params.put("motDePasse",motdepasse);
        System.out.println("Le telephone: "+telephone);
        System.out.println("Le motDePasse: "+motdepasse);

        Boutique boutique = Boutique.find("telephone =:telephone and motDePasse =:motDePasse ",params).firstResult();

        if(boutique != null){
            return Response.ok(boutique).build();
        }else{
            HashMap<String, Object> params2 = new HashMap<>();
            //String truc = telephone.replace("+243","");
            //String tel = telephone.replaceAll("/+243","");
            params2.put("telephone",telephone);
            params2.put("mdp",motdepasse);
            System.out.println("Le telephone: "+telephone+" :: ");
            System.out.println("Le motDePasse: "+motdepasse);

            Business business = Business.find("telephone =:telephone and mdp =:mdp ",params2).firstResult();
            if(business != null){
                return Response.ok(business).build();
            }else{
                return Response.serverError().build();
            }

        }

    }

    @GET
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Boutique.listAll()).build();
    }

    @GET
    @Path("all/{codePromo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBoutiques(@PathParam("codePromo") String codePromo){
        List<Boutique> liste = Boutique.list("codePromo",codePromo);
        return Response.ok(liste).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Boutique boutique){
        HashMap<String, Object> params = new HashMap<>();
        params.put("telephone",boutique.telephone);

        Boutique utilisater = Boutique.find("telephone =:telephone ",params).firstResult();
        if(utilisater == null){
            boutique.persist();
            return Response.ok(boutique).build();
        }else{
            return Response.status(405).entity("Ce numéro exist déjà veuillez-vous connecter.").build();
        }


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
        boutique1.rccm = boutique.rccm;
        boutique1.provinceSiege = boutique.provinceSiege;
        boutique1.typeEtablissement = boutique.typeEtablissement;
        boutique1.categorie = boutique.categorie;
        boutique1.nombreEtablissement = boutique.nombreEtablissement;
        boutique1.photo = boutique.photo;
        boutique1.codePromo = boutique.codePromo;
        boutique1.motDePasse = boutique.motDePasse;
        boutique1.status = boutique.status;
        boutique1.code = boutique.code;
        boutique1.token = boutique.token;
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
