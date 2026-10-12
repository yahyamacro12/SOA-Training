package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/Ue")
public class UniteEnRestApi {
 static UniteEnseignementBusiness helper= new UniteEnseignementBusiness();
 @Path("/list")
 @GET
 @Produces(MediaType.APPLICATION_JSON)
    public Response getListUEs(){
        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUe(UniteEnseignement unite){
     if (helper.addUniteEnseignement(unite))
     return  Response.status(201).entity("ok").build();
     else
         return Response.status(400).entity("error").build();
    }

    @Path("/{inputcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUeByCode(@PathParam("inputcode") int code){
     return Response.status(200)
             .entity(helper.getUEByCode(code))
             .build();
    }
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUeBySemestere(@QueryParam("semestere") int semestere){
        return Response.status(200)
                .entity(helper.getUEBySemestre(semestere))
                .build();
    }
    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUeByCode(@QueryParam("code") int code){
     if(helper.deleteUniteEnseignement(code))
         return Response.status(200).entity("ok").build();
     else
         return Response.status(400).entity("error").build();
    }

}
