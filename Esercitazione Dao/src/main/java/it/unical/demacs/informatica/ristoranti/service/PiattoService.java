package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.exception.PiattoNotValidException;
import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.PiattoDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiattoService implements IPiattoService {

    /* opzione auto wiring 1
    // @Autowired
    // private PiattoDAO dao;
    */

    // opzione auto wiring 2
    private final PiattoDAO piattoDAO;

    PiattoService(PiattoDAO piattoDAO) {
        this.piattoDAO = piattoDAO;
    }

    @Override
    public List<Piatto> findAll() {
        return piattoDAO.findAll();
    }

    @Override
    public Piatto findById(String nome) {
        return this.piattoDAO.findByPrimaryKey(nome);
    }

    @Override
    public Piatto createPiatto(Piatto piatto) throws Exception {
        // verify that not exists a Piatto with the same name
        if (this.piattoDAO.findByPrimaryKey(piatto.getNome()) != null) {
            throw new Exception("Already exists a Piatto with the same name! " + piatto.getNome());
        }
        // verify that Piatto is consistent
        checkPiattoIsValid(piatto);
        this.piattoDAO.save(piatto);
        return this.piattoDAO.findByPrimaryKey(piatto.getNome());
    }

    private void checkPiattoIsValid(Piatto piatto) throws PiattoNotValidException {
        if (piatto == null) {
            throw new PiattoNotValidException("Piatto must be not null");
        }
        if (piatto.getNome() == null || piatto.getNome().isEmpty()) {
            throw new PiattoNotValidException("Piatto.nome must be not null and not empty");
        }
    }

    @Override
    public Piatto updatePiatto(String nome, Piatto piatto) throws Exception {
        piatto.setNome(nome);
        checkPiattoIsValid(piatto);
        // verify that exists a Piatto with the same name
        if (this.piattoDAO.findByPrimaryKey(piatto.getNome()) == null) {
            throw new Exception("Not exists a Piatto with the same name! " + piatto.getNome());
        }
        this.piattoDAO.save(piatto);
        return this.piattoDAO.findByPrimaryKey(piatto.getNome());
    }

    @Override
    public void deletePiatto(String nome) {
        this.piattoDAO.delete(nome);
    }
}
