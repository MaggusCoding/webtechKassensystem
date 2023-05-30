package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.api.Kassenbuchung;
import htwberlin.backend_kasse.api.KassenbuchungManipulationRequest;
import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterManipulationRequest;
import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.entities.MitarbeiterEntity;
import htwberlin.backend_kasse.repos.KassenbuchungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KassenbuchungService {

    @Autowired
    private KassenbuchungRepository repo;

    public KassenbuchungService(KassenbuchungRepository kassenbuchungRepository) {
        this.repo = kassenbuchungRepository;
    }

    public Kassenbuchung create(KassenbuchungManipulationRequest request) {
        var kassenbuchungEntity = new KassenbuchungEntity(request.getBuchungsbetrag());
        kassenbuchungEntity = repo.save(kassenbuchungEntity);
        return transformEntity(kassenbuchungEntity);
    }

    public Kassenbuchung findById(int id) {
        Optional<KassenbuchungEntity> kassenbuchungEntity = repo.findById(id);
        return kassenbuchungEntity.map(this::transformEntity).orElse(null);
    }

    public List<Kassenbuchung> findAll() {
        List<KassenbuchungEntity> kassenbuchung = repo.findAll();
        return kassenbuchung.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Kassenbuchung update(Integer id, KassenbuchungManipulationRequest request) {
        var kassenbuchungEntityOptional = repo.findById(id);
        if (kassenbuchungEntityOptional.isEmpty()) {
            return null;
        }
        var kassenbuchungEntity = kassenbuchungEntityOptional.get();
        kassenbuchungEntity.setBuchungsbetrag(request.getBuchungsbetrag());
        kassenbuchungEntity= repo.save(kassenbuchungEntity);
        return transformEntity(kassenbuchungEntity);
    }

    private Kassenbuchung transformEntity(KassenbuchungEntity kassenbuchungEntity) {
        return new Kassenbuchung(kassenbuchungEntity.getId()
                ,kassenbuchungEntity.getBuchungsbetrag()
                , kassenbuchungEntity.getTimestamp()
                , kassenbuchungEntity.getLastUpdatedOn());
    }
}
