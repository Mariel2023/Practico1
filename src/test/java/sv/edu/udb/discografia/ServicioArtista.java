package sv.edu.udb.discografia;

import java.sql.SQLException;
import java.util.List;
import sv.edu.udb.model.Artista;
import sv.edu.udb.model.ArtistaDAO;
import sv.edu.udb.model.Artista;

public class ServicioArtista {

    private ArtistaDAO artistaDAO = new ArtistaDAO();

    public List<Artista> obtenerTodosLosArtistas() {
        try {
            return artistaDAO.findAll();
        } catch (SQLException e) {
        
            e.printStackTrace();
            return null;
        }
    }

    public Artista obtenerArtistaPorId(int id) {
        try {
            return artistaDAO.findById(id);
        } catch (SQLException e) {
       
            e.printStackTrace();
            return null;
        }
    }

    public void registrarArtista(Artista artista) {
        try {
            artistaDAO.insert(artista);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void modificarArtista(int id, Artista artista) {
        try {
        
            if (artistaDAO.findById(id) != null) {
                artista.setIdArtista(id);
                artistaDAO.update(artista);
            }
        } catch (SQLException e) {
      
            e.printStackTrace();
        }
    }

    public void borrarArtista(int id) {
        try {
           
            if (artistaDAO.findById(id) != null) {
                artistaDAO.delete(id);
            }
        } catch (SQLException e) {
      
            e.printStackTrace();
        }
    }
}


