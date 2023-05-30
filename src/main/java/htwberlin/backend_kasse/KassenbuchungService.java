package htwberlin.backend_kasse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KassenbuchungService {

    @Autowired
    KassenbuchungRepository repo;

    public Kassenbuchung save(Kassenbuchung kassenbuchung){
        return repo.save(kassenbuchung);
    }

    public Kassenbuchung get(int id){
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Kassenbuchung> findAll(){
        List<Kassenbuchung> list = new ArrayList<>();
        for(Kassenbuchung x:repo.findAll()){list.add(x);}
        return list;
    };
}
