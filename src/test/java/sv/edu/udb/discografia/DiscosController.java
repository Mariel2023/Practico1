package sv.edu.udb.discografia;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/discos")
public class DiscosController {

    private ServicioDisco discoService = new ServicioDisco();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosDiscos() {
        List<Disco> discos = discoService.obtenerTodosLosDiscos();
        return Response.status(200).entity(discos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDiscoPorId(@PathParam("id") int id) {
        Disco disco = discoService.obtenerDiscoPorId(id);
        if (disco == null) {
            return Response.status(404).entity("Disco no encontrado").build();
        }
        return Response.status(200).entity(disco).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDisco(Disco disco) {
        if (disco.getNombreDisco() == null || disco.getNombreDisco().isEmpty() ||
            disco.getIdArtista() <= 0 || disco.getNumeroCanciones() <= 0 || disco.getPrecio() <= 0) {
            return Response.status(400).entity("Datos del disco no válidos").build();
        }

        discoService.registrarDisco(disco);
        return Response.status(201).entity(disco).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarDisco(@PathParam("id") int id, Disco disco) {
        Disco discoExistente = discoService.obtenerDiscoPorId(id);
        if (discoExistente == null) {
            return Response.status(404).entity("Disco no encontrado").build();
        }

        discoService.modificarDisco(id, disco);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarDisco(@PathParam("id") int id) {
        Discos discoExistente = discoService.obtenerDiscoPorId(id);
        if (discoExistente == null) {
            return Response.status(404).entity("Disco no encontrado").build();
        }

        discoService.borrarDisco(id);
        return Response.status(204).build();
    }
}

