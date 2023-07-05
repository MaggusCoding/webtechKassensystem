package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.api.Kassenbuchung;
import htwberlin.backend_kasse.api.KassenbuchungManipulationRequest;
import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.repos.KassenbuchungRepository;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KassenbuchungService {

    @Autowired
    private KassenbuchungRepository kassenbuchungRepository;
    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    public KassenbuchungService(KassenbuchungRepository kassenbuchungRepository) {
        this.kassenbuchungRepository = kassenbuchungRepository;
    }

    public Kassenbuchung create(KassenbuchungManipulationRequest request) {
        var mitarbeiter = mitarbeiterRepository.findById(request.getMitarbeiter_id()).orElseThrow();
        var kassenbuchungEntity = new KassenbuchungEntity(request.getBuchungsbetrag(),mitarbeiter,request.getComment());
        kassenbuchungEntity = kassenbuchungRepository.save(kassenbuchungEntity);
        return transformEntity(kassenbuchungEntity);
    }

    public Kassenbuchung findById(int id) {
        Optional<KassenbuchungEntity> kassenbuchungEntity = kassenbuchungRepository.findById(id);
        return kassenbuchungEntity.map(this::transformEntity).orElse(null);
    }

    public List<Kassenbuchung> findAll() {
        List<KassenbuchungEntity> kassenbuchung = kassenbuchungRepository.findAll();
        return kassenbuchung.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Kassenbuchung update(Integer id, KassenbuchungManipulationRequest request) {
        var kassenbuchungEntityOptional = kassenbuchungRepository.findById(id);
        if (kassenbuchungEntityOptional.isEmpty()) {
            return null;
        }
        var kassenbuchungEntity = kassenbuchungEntityOptional.get();
        kassenbuchungEntity.setBuchungsbetrag(request.getBuchungsbetrag());
        kassenbuchungEntity= kassenbuchungRepository.save(kassenbuchungEntity);
        return transformEntity(kassenbuchungEntity);
    }

    private Kassenbuchung transformEntity(KassenbuchungEntity kassenbuchungEntity) {
        return new Kassenbuchung(kassenbuchungEntity.getId()
                ,kassenbuchungEntity.getBuchender().getId()
                ,kassenbuchungEntity.getBuchungsbetrag()
                ,kassenbuchungEntity.getComment()
                , kassenbuchungEntity.getTimestamp()
                , kassenbuchungEntity.getLastUpdatedOn());
    }
}
