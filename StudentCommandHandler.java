import java.util.Map;

public class StudentCommandHandler {
    private  StudentStorage stStorage = new StudentStorage();
    public void processCommand(Command command){
        Action action = command.getAction();
        switch (action){
            case CREATE -> processCreateCommand(command);
            case UPDATE -> processUpdateCommand(command);
            case DELETE -> processDeleteCommand(command);
            case SEARCH -> processSearchCommand(command);
            case STAT_BY_COURSE -> processStateByCourseCommand(command);
            case STAT_BY_CITY -> processStateByCityCommand(command);
            default -> System.out.println("Действие " + action +" не поддерживается");

        }
        System.out.println("Обработка команды." +
                "Действие: " + command.getAction().name()
                + ", данные: " + command.getData());
    }

    private void processStateByCityCommand(Command command) {
        Map<String, Long> data = stStorage.getCountByCity();
        stStorage.printMap(data);
    }

    private void processStateByCourseCommand(Command command) {
        Map<String,Long> data = stStorage.getCountByCourse();
        stStorage.printMap(data);
    }


    private void processCreateCommand(Command command){
        System.out.println("Введите данные: Фамилия,Имя,Курс,Город,Возраст ");
        System.out.println("Пример: 1,Иванов,Петр,Go,Астана,21 ");
        try {
            String[] data = command.getData().split(",");
            if(data.length!=5){
                System.out.println("Ошибка ввода данных");
                return;
            }
            String surname = data[0].trim();
            String name = data[1].trim();
            String course = data[2].trim();
            String city = data[3].trim();
            int age = Integer.parseInt(data[4].trim());
            if(surname.isEmpty()||name.isEmpty()||
            course.isEmpty()||city.isEmpty()||age<=0){
                System.out.println("Ошибка ввода данный");
                return;
            }
//
//            String data = command.getData();
//            String[] dataArray = data.split(",");

            Student student = new Student();
            student.setSurname(surname);
            student.setName(name);
            student.setCourse(course);
            student.setCity(city);
            student.setAge(age);

            stStorage.createStudent(student);
            stStorage.printAll();
        }catch (Exception e){
            System.out.println("Ошибка ввода данных" + e.getMessage());
        }
    }

    public void processUpdateCommand(Command command){
        System.out.println("Введите данные: id, Фамилия,Имя,Курс,Город,Возраст ");
        System.out.println("Пример: 1,Иванов,Петр,Go,Астана,21 ");
        try {
            String[] data = command.getData().split(",");
            if(data.length != 6){
                System.out.println("Ошибка ввода данных");
                return;
            }

            long id = Long.parseLong(data[0].trim());

            String surname = data[1].trim();
            String name = data[2].trim();
            String course = data[3].trim();
            String city = data[4].trim();
            int age = Integer.parseInt(data[5].trim());

            Student student = new Student();
            student.setSurname(surname);
            student.setName(name);
            student.setCourse(course);
            student.setCity(city);
            student.setAge(age);

            boolean updated = stStorage.updateStudent(id, student);
            if(!updated){
                System.out.println("Студент не найдке");
            }

//            stStorage.updateStudent(id, student);
//            stStorage.printAll();
        }catch (Exception e){
            System.out.println("Ошибка ввода данных" + e.getMessage());
        }
    }

    public void processDeleteCommand(Command command){
        System.out.println("Введите ID студента для удаления ");
        try {
            long id = Long.parseLong(command.getData().trim());
            boolean deleted = stStorage.deleteStudent(id);
            if (!deleted){
                System.out.println("Студент не найден");
            }
        }catch (Exception e){
            System.out.println("Ошибка ввода ID " +e.getMessage());
        }
//        String data = command.getData();
//        Long id = Long.valueOf(data);
//        stStorage.deleteStudent(id);
//        stStorage.printAll();
    }

    private  void processSearchCommand(Command command){
        System.out.println("Формат поиска: ");
        System.out.println("1) пусто -> все студенты ");
        System.out.println("2) точный поиск по фамилии ");
        System.out.println("3) диапазон поиска,входящих в диапазон поиска между фамилиями ");
        try {
            String surname = command.getData();
            if(surname == null){
                stStorage.printAll();
                return;
            }
            stStorage.search(surname);
        }catch (Exception e){
            System.out.println("Ошибка поиска " + e.getMessage());
        }
    }
}
