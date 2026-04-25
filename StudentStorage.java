import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentStorage {
    private Map<Long,Student> studentStorageMap = new HashMap<>();
    private long currentId;

    private StudentSurnameStorage stSurnameStorage = new StudentSurnameStorage();
    public long createStudent(Student student){
        long nextId = getNextId();
        studentStorageMap.put(nextId, student);
        stSurnameStorage.studentCreated(nextId, student.getSurname());
        return nextId;
    }
    /*
    Обновление данных о студенте
    @param id идентификатор студента
    @ param student данные студента
    @return true еслиданные были обновлены, false если не был найден
     */
    public  boolean updateStudent(long id, Student student){
        if (!studentStorageMap.containsKey(id)){
            return false;
        }else {
            String newSurname = student.getSurname();
            String oldSurname = studentStorageMap.get(id).getSurname();
            stSurnameStorage.studentUpdated(id, oldSurname, newSurname);
            studentStorageMap.put(id,student);
            return true;
        }

    }


    public Map<String, Long> getCountByCity(){
        return studentStorageMap.values().stream()
                .collect(Collectors.groupingBy(
                        Student::getCity,
                        Collectors.counting()
                ));
    }


    /*
    Обновление данных о студенте
    @param id идентификатор студента
    @ param student данные студента
    @return true еслиданные были обновлены, false если не был найден
    */
    public boolean deleteStudent(long id){
        Student removed = studentStorageMap.remove(id);
        if(removed!=null){
            String surname = removed.getSurname();
            stSurnameStorage.studentDeleted(id,surname);
        }
        return removed != null;
    }

    public void search(String input){
//        Set<Long> students= stSurnameStorage.getSurnamesLessOrEqualThan(surname);
//
//        for(Long studentId : students){
//           Student student = studentStorageMap.get(studentId);
//            System.out.println(student);
//        }
        input=input.trim();

        if (input.isEmpty()){
            printAll();
            return;
        }
        String[] parts = input.split(",");

        if(parts.length==1){
            String surname = parts[0].trim();
            Set<Long> ids = stSurnameStorage.getExactSurname(surname);
            if(ids==null|| ids.isEmpty()){
                System.out.println("Студенты не найдены");
                return;
            }
            for (Long id : ids){
                System.out.println(studentStorageMap.get(id));
            }
            return;
        }
        if(parts.length ==2){
            String from = parts[0].trim();
            String to = parts[1].trim();

            Set<Long> ids = stSurnameStorage.getRange(from,to);
            if(ids==null||ids.isEmpty()){
                System.out.println("Студентыне найдены");
                return;
            }
            for (Long id : ids){
                System.out.println(studentStorageMap.get(id));
            }
            return;
        }
        System.out.println("Некорректный фпормат поиска");
    }
    public Long getNextId(){
        return  currentId+=1;
    }

    public void printAll(){
        System.out.println(studentStorageMap);
    }

    public void printMap(Map<String, Long> data){
        data.entrySet().stream().forEach(e->{
            System.out.println(e.getKey() + " - " + e.getValue());
        });
    }

    public Map<String,Long> getCountByCourse(){
//        Map<String, Long> res = new HashMap<>();
//        for (Student student: studentStorageMap.values()){
//            String key = student.getCourse();
//            long count = res.getOrDefault(key, 0L);
//            count++;
//            res.put(key,count);
//        }
//        return res
        Map<String,Long> res =studentStorageMap.values().stream()
                .collect(Collectors.toMap(
                     student ->student.getCourse(),
                     student -> 1L,
                        (count1,count2) ->count1 + count2
                ));
        return res;
    }
}
