package webservices;


import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Bu")
public class BusinessRestApi {
    static ModuleBusiness helper = new ModuleBusiness();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListBUs(){
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBu(Module unite){
        if (helper.addModule(unite))
            return  Response.status(201).entity("ok").build();
        else
            return Response.status(400).entity("error").build();
    }
    @Path("/{inputcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUbByMatricule(@PathParam("inputcode") String code){
        return Response.status(200)
                .entity(helper.getModuleByMatricule(code))
                .build();
    }
    @Path("/{ue}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUbByenseignement(@PathParam("ue") UniteEnseignement ue){
        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuBytype(@QueryParam("type") Module.TypeModule type){
        return Response.status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }

@Path("/delete")
@DELETE
@Produces(MediaType.APPLICATION_JSON)
public Response deleteUeBymatrcul(@QueryParam("code") String code){
    if(helper.deleteModule(code))
        return Response.status(200).entity("ok").build();
    else
        return Response.status(400).entity("error").build();
}
}
