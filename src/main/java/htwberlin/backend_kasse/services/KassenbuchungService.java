package htwberlin.backend_kasse.services;


import htwberlin.backend_kasse.api.Kassenbuchung;
import htwberlin.backend_kasse.api.KassenbuchungManipulationRequest;
import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.repos.KassenbuchungRepository;
import htwberlin.backend_kasse.repos.MitarbeiterRepository;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public Map<Date, BigDecimal> calculateMonthlyRevenue() {
        var kassenBuchungEntity = kassenbuchungRepository.findAll();
        Map<Date, BigDecimal> map = new HashMap<>();
        kassenBuchungEntity.forEach(kassenBuchung -> {
            Instant timestamp = kassenBuchung.getTimestamp();
            BigDecimal buchungsbetrag = kassenBuchung.getBuchungsbetrag();
            timestamp = timestamp.truncatedTo(ChronoUnit.SECONDS);
            Date date = Date.from(timestamp);
            map.put(date, buchungsbetrag);
        });

        Map<Date, BigDecimal> monthlyMap = new HashMap<>();
        for (Map.Entry<Date, BigDecimal> entry : map.entrySet()) {
            Date date = entry.getKey();
            BigDecimal value = entry.getValue();

            // Set the day, hour, minute, second, and millisecond to zero
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // Add 1 to the month index
            int month = calendar.get(Calendar.MONTH) + 1;
            calendar.set(Calendar.MONTH, month);

            Date startOfMonthDate = calendar.getTime();

            // Check if the startOfMonthDate is already present in monthlyMap
            if (monthlyMap.containsKey(startOfMonthDate)) {
                // Add the value to the existing sum
                BigDecimal sum = monthlyMap.get(startOfMonthDate).add(value);
                monthlyMap.put(startOfMonthDate, sum);
            } else {
                // Add a new entry for the month with the initial value
                monthlyMap.put(startOfMonthDate, value);
            }
        }

        return monthlyMap;
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
