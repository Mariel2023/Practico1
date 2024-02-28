package sv.edu.udb.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class DiscosDAO extends Conexion {

    public void insert(Discos disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("INSERT INTO discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.execute();
        close();
    }

    public void update(Discos disco) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("UPDATE discos SET nombre_disco = ?, id_artista = ?, numero_canciones = ?, precio = ? WHERE id_disco = ?");
        pstmt.setString(1, disco.getNombreDisco());
        pstmt.setInt(2, disco.getIdArtista());
        pstmt.setInt(3, disco.getNumeroCanciones());
        pstmt.setDouble(4, disco.getPrecio());
        pstmt.setInt(5, disco.getIdDisco());
        pstmt.execute();
        close();
    }

    public void delete(int id) throws SQLException {
        connect();
        pstmt = conn.prepareStatement("DELETE FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        pstmt.execute();
        close();
    }

    public ArrayList<Discos> findAll() throws SQLException {
        connect();
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos");
        ArrayList<Discos> discos = new ArrayList<>();

        while (resultSet.next()) {
            Discos tmp = new Discos();
            tmp.setIdDisco(resultSet.getInt(1));
            tmp.setNombreDisco(resultSet.getString(2));
            tmp.setIdArtista(resultSet.getInt(3));
            tmp.setNumeroCanciones(resultSet.getInt(4));
            tmp.setPrecio(resultSet.getDouble(5));

            discos.add(tmp);
        }

        close();

        return discos;
    }

    public Discos findById(int id) throws SQLException {
        Discos disco = null;

        connect();
        pstmt = conn.prepareStatement("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos WHERE id_disco = ?");
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            disco = new Discos();
            disco.setIdDisco(resultSet.getInt(1));
            disco.setNombreDisco(resultSet.getString(2));
            disco.setIdArtista(resultSet.getInt(3));
            disco.setNumeroCanciones(resultSet.getInt(4));
            disco.setPrecio(resultSet.getDouble(5));
        }

        close();
        return disco;
    }
}
