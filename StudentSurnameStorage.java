import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private TreeMap<String, Set<Long>> surnamesTreeMap = new TreeMap<>();

    public void studentCreated(long id, String surname){
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname.trim().toLowerCase(), new HashSet<>());
        existingIds.add(id);
        surnamesTreeMap.put(surname.trim().toLowerCase(),existingIds);
    }

    public void studentDeleted(long id, String surname){
        surnamesTreeMap.get(surname.trim().toLowerCase()).remove(id);
    }


    public  Set<Long> getExactSurname(String surname){
        return surnamesTreeMap.getOrDefault(surname.trim().toLowerCase(),new HashSet<>());
    }

    public Set<Long> getRange(String from, String to){
        String keyFrom = from.toLowerCase().trim();
        String keyTo = to.trim().toLowerCase();
        if(keyFrom.compareTo(keyTo)>0){
            String temp = keyFrom;
            keyFrom = keyTo=temp;
        }
        return surnamesTreeMap.subMap(keyFrom,true,keyTo,true)
                .values().stream().flatMap(Set::stream).collect(Collectors.toSet());
    }
    public void studentUpdated(Long id, String oldSurname, String newSurname){
//        surnamesTreeMap.get(oldSurname).remove(id);
//        surnamesTreeMap.get(newSurname).add(id);
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);
    }
    /**
     * Двнный метод возвращает уникальные идентификаторы студентов,
     * чьи фамилиименьшеили равныпереданной
     * @return set
     */
    public Set<Long> getSurnamesLessOrEqualThan(String surname){
        Set<Long> res = surnamesTreeMap.headMap(surname, true)
                .values()
                        .stream()
                .flatMap(longs -> longs.stream())
                .collect(Collectors.toSet());
        return res;
    }
}
