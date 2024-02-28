package sv.edu.udb.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArtistaDAO extends Conexion {

  
    public void insert(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("INSERT INTO artistas (nombre, descripcion) VALUES (?, ?)");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.execute();
        close();
    }

  
    public void update(Artista artista) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE artistas SET nombre = ?, descripcion = ? WHERE id = ?");
        pstmt.setString(1, artista.getNombreArtista());
        pstmt.setString(2, artista.getDescripcion());
        pstmt.setInt(3, artista.getIdArtista());
        pstmt.execute();
        close();
    }

   
    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM artistas WHERE id = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    
    public ArrayList<Artista> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id, nombre, descripcion FROM artistas");
        ArrayList<Artista> artistas = new ArrayList<>();

        while (resultSet.next()) {
            Artista tmp = new Artista();
            tmp.setIdArtista(resultSet.getInt(1));
            tmp.setNombreArtista(resultSet.getString(2));
            tmp.setDescripcion(resultSet.getString(3));

            artistas.add(tmp);
        }

        close();

        return artistas;
    }

 
    public Artista findById(int id) throws SQLException {
        Artista artista = null;

        connect();
        pstmt = conn.prepareStatement("SELECT id, nombre, descripcion FROM artistas WHERE id = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            artista = new Artista();
            artista.setIdArtista(resultSet.getInt(1));
            artista.setNombreArtista(resultSet.getString(2));
            artista.setDescripcion(resultSet.getString(3));
        }

        close();
        return artista;
    }
}


