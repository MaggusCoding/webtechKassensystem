package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.repos.KassenbuchungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KassenbuchungService {

    @Autowired
    KassenbuchungRepository repo;

    public KassenbuchungEntity save(KassenbuchungEntity kassenbuchungEntity){
        return repo.save(kassenbuchungEntity);
    }

    public KassenbuchungEntity get(int id){
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<KassenbuchungEntity> findAll(){
        List<KassenbuchungEntity> list = new ArrayList<>();
        for(KassenbuchungEntity x:repo.findAll()){list.add(x);}
        return list;
    };
}
