package org.acme.controllers;

import org.acme.models.Utilisateur;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@Path("utilisateur")
public class UtilisateurController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("id") long id){
        Utilisateur utilisater = Utilisateur.findById(id);
        if(utilisater == null){
            return Response.serverError().build();
        }
        return Response.ok(utilisater).build();
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
    @Path("all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUtilisateurs(){
        return Response.ok(Utilisateur.listAll()).build();
    }

    @GET
    @Path("all/{codePromo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUtilisateurs(@PathParam("codePromo") String codePromo){
        List<Utilisateur> liste = Utilisateur.list("codePromo",codePromo);
        return Response.ok(liste).build();
    }

    @GET
    @Path("email/test")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmailTest(String email){
        try{
            sending(email);
            return Response.ok().build();
        }catch (Exception ex){
            System.out.println(ex);
            return Response.serverError().build();
        }

    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUtilisateur(Utilisateur utilisateur){
        HashMap<String, Object> params = new HashMap<>();
        params.put("email",utilisateur.email);

        Utilisateur utilisater = Utilisateur.find("email =:email ",params).firstResult();
        if(utilisater == null){
        utilisateur.persist();
            return Response.ok(utilisateur).build();
        }else{
            utilisater.idUtilisateur = utilisateur.idUtilisateur;
            utilisater.nom = utilisateur.nom;
            utilisater.email = utilisateur.email;
            utilisater.photoURL = utilisateur.photoURL;
            return Response.ok(utilisater).build();
        }

    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUtilisareur(@PathParam("id") long id){
        boolean delete = Utilisateur.deleteById(id);
        if(delete){
            return Response.ok("Utilisateur supprimé avec succès").build();
        }else{
            return Response.ok("Cette utilisateur n'a pu etre supprimé").build();
        }
    }

    @PUT
    @Transactional
    public Response updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = Utilisateur.findById(utilisateur.id);
        if(utilisateur1 == null){
            return Response.serverError().build();
        }
        //
        utilisateur1.idUtilisateur = utilisateur.idUtilisateur;
        utilisateur1.nom = utilisateur.nom;
        utilisateur1.email = utilisateur.email;
        utilisateur1.photoURL = utilisateur.photoURL;
        //
        return Response.ok(utilisateur1).build();
    }

    /*
    @GET
    @Path("photo.png")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getPhoto(@QueryParam("id") long id){
        Utilisateur utilisateur = Utilisateur.findById(id);
        return utilisateur.photo;
    }
    */

    //
    public void sending(String too) throws Exception {
        // Recipient's email ID needs to be mentioned.
        //String to = "abcd@gmail.com";
        // Sender's email ID needs to be mentioned


        String to = "lungujoel138@gmail.com";
        String from = "sonofgad34@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        /*
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        */
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //properties.put("mail.smtp.auth", "true");
        //
        //Session session = Session.getInstance(properties);
        //


        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lungujoel138@gmail.com", "0815381693Lungu");
            }
        });
        //
        sendEmail(session,"lungujoel138@gmail.com","Test","Un peu de test");

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the email subject");
            message.setText("This is the email body");

            //Transport.send(message);
            //
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    //
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
