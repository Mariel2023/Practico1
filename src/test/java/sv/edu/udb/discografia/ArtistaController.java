package sv.edu.udb.discografia;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/artistas")
public class ArtistaController {

    private ServicioArtista artistaService = new ServicioArtista();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosArtistas() {
        List<Artista> artistas = artistaService.obtenerTodosLosArtistas();
        return Response.status(200).entity(artistas).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistaPorId(@PathParam("id") int id) {
        Artista artista = artistaService.obtenerArtistaPorId(id);
        if (artista == null) {
            return Response.status(404).entity("Artista no encontrado").build();
        }
        return Response.status(200).entity(artista).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarArtista(Artista artista) {
        if (artista.getNombreArtista() == null || artista.getNombreArtista().isEmpty()) {
            return Response.status(400).entity("Nombre del artista no válido").build();
        }

        artistaService.registrarArtista(artista);
        return Response.status(201).entity(artista).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarArtista(@PathParam("id") int id, Artista artista) {
        Artista artistaExistente = artistaService.obtenerArtistaPorId(id);
        if (artistaExistente == null) {
            return Response.status(404).entity("Artista no encontrado").build();
        }

        artistaService.modificarArtista(id, artista);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarArtista(@PathParam("id") int id) {
        Artista artistaExistente = artistaService.obtenerArtistaPorId(id);
        if (artistaExistente == null) {
            return Response.status(404).entity("Artista no encontrado").build();
        }

        artistaService.borrarArtista(id);
        return Response.status(204).build();
    }
}


