package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterCreateRequest;
import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MitarbeiterService {

    @Autowired
    private MitarbeiterRepository repo;

    public MitarbeiterService(MitarbeiterRepository mitarbeiterRepository) {
        this.repo = mitarbeiterRepository;
    }

    public Mitarbeiter create(MitarbeiterCreateRequest request) {
        var mitarbeiterEntity = new MitarbeiterEntity(request.getVorname(),
                request.getNachname(),
                request.getStudiengang());
       mitarbeiterEntity = repo.save(mitarbeiterEntity);
    return transformEntity(mitarbeiterEntity);
    }

    public MitarbeiterEntity get(int id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Mitarbeiter> findAll() {
        List<MitarbeiterEntity> mitarbeiter = repo.findAll();
        return mitarbeiter.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    private Mitarbeiter transformEntity(MitarbeiterEntity mitarbeiterEntity){
        return new Mitarbeiter(mitarbeiterEntity.getId()
                , mitarbeiterEntity.getVorname()
                , mitarbeiterEntity.getNachname()
                , mitarbeiterEntity.getStudiengang()
                , mitarbeiterEntity.getTimestamp()
                , mitarbeiterEntity.getLastUpdatedOn());
    }
}
