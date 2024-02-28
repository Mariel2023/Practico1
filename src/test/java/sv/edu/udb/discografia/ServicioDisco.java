package sv.edu.udb.discografia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioDisco {

    private Map<Integer, Discos> discos;

    public ServicioDisco() {
       
        this.discos = new HashMap<>();
    }

    public List<Disco> obtenerTodosLosDiscos() {
        return new ArrayList<>(discos.values());
    }

    public Disco obtenerDiscoPorId(int id) {
        return discos.get(id);
    }

    public void registrarDisco(Discos disco) {
        
        int nuevoId = discos.size() + 1;
        disco.setId(nuevoId);

        
        discos.put(nuevoId, disco);
    }

    public void modificarDisco(int id, Disco discoModificado) {
       
        if (discos.containsKey(id)) {
          
            Disco discoExistente = discos.get(id);
            discoExistente.setNombreDisco(discoModificado.getNombreDisco());
            discoExistente.setIdArtista(discoModificado.getIdArtista());
            discoExistente.setNumeroCanciones(discoModificado.getNumeroCanciones());
            discoExistente.setPrecio(discoModificado.getPrecio());
        }
    }

    public void borrarDisco(int id) {
       
        discos.remove(id);
    }
}

