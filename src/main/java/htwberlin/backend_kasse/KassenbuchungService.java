package htwberlin.backend_kasse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
