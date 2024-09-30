package test;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class JourneyRepo {

    private Map<LocalDate, Map<String, List<BusJourney>>> journeyByDateMap = new HashMap<>();


    public void addJourney(BusJourney busJourney){
        LocalDate journeyDate = busJourney.getJourneyDate();
        journeyByDateMap.putIfAbsent(journeyDate, new HashMap<>());
        journeyByDateMap.get(journeyDate).putIfAbsent(busJourney.getSource(), new ArrayList<>());
        journeyByDateMap.get(journeyDate).get(busJourney.getSource()).add(busJourney);

    }

    public List<BusJourney> searchJourney(LocalDate journeyDate, String source, String destination, int capacity){
        if(capacity <=0){
            throw new IllegalArgumentException("capacity should be positive");
        }
        Map<String, List<BusJourney>> busJourneyMap = journeyByDateMap.get(journeyDate);
        if(busJourneyMap == null || busJourneyMap.size()==0){
            return new ArrayList<>();
        }

        return busJourneyMap.getOrDefault(source, new ArrayList<>())
                .stream().filter(busJourney -> busJourney.getDestination().equals(destination)
                        && busJourney.getCapacity()>= capacity)
                .collect(Collectors.toList());
    }
}
