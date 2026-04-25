public class Student {
    private String surname;
    private String name;

    private String course;
    private int age;
    private String city;


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Фамилия'" + surname + '\'' +
                ", Имя'" + name + '\'' +
                ", Курс'" + course + '\'' +
                ", Возраст" + age +
                ", Город'" + city + '\'' +
                '}';
    }
}
