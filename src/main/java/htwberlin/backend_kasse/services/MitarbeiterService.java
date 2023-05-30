package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterManipulationRequest;
import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MitarbeiterService {

    @Autowired
    private MitarbeiterRepository repo;

    public MitarbeiterService(MitarbeiterRepository mitarbeiterRepository) {
        this.repo = mitarbeiterRepository;
    }

    public Mitarbeiter create(MitarbeiterManipulationRequest request) {
        var mitarbeiterEntity = new MitarbeiterEntity(request.getVorname(),
                request.getNachname(),
                request.getStudiengang());
        mitarbeiterEntity = repo.save(mitarbeiterEntity);
        return transformEntity(mitarbeiterEntity);
    }

    public Mitarbeiter findById(int id) {
        Optional<MitarbeiterEntity> mitarbeiterEntity = repo.findById(id);
        return mitarbeiterEntity.map(this::transformEntity).orElse(null);
    }

    public List<Mitarbeiter> findAll() {
        List<MitarbeiterEntity> mitarbeiter = repo.findAll();
        return mitarbeiter.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Mitarbeiter update(Integer id, MitarbeiterManipulationRequest request) {
        var mitarbeiterEntityOptional = repo.findById(id);
        if (mitarbeiterEntityOptional.isEmpty()) {
            return null;
        }
        var mitarbeiterEntity = mitarbeiterEntityOptional.get();
        mitarbeiterEntity.setVorname(request.getVorname());
        mitarbeiterEntity.setNachname(request.getNachname());
        mitarbeiterEntity.setStudiengang(request.getStudiengang());
        mitarbeiterEntity= repo.save(mitarbeiterEntity);
        return transformEntity(mitarbeiterEntity);
    }

    private Mitarbeiter transformEntity(MitarbeiterEntity mitarbeiterEntity) {
        return new Mitarbeiter(mitarbeiterEntity.getId()
                , mitarbeiterEntity.getVorname()
                , mitarbeiterEntity.getNachname()
                , mitarbeiterEntity.getStudiengang()
                , mitarbeiterEntity.getTimestamp()
                , mitarbeiterEntity.getLastUpdatedOn());
    }
}
