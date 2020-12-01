package Home_Work_5;

public class staffMember {
    String  fcs;
    String position;
    String email;
    String phone;
    int salary;
    int age;

    public staffMember(String fcs, String position, String email, String phone, int salary, int age) {
        this.fcs = fcs;
        /*if (this.fcs.length() <= 100) {
            this.fcs = (fcs != null) ? fcs.toUpperCase() : null;
        }*/
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = (salary >= 90 || salary == 0) ? salary : -1;
        setAge(age);
    }

    private void setAge(int age) {
        if (age == 0 && this.salary == -1) {
            this.age = salary;
            this.salary = 0;
        } else if (age > 90) {
            this.age = 0;
            this.salary = age;
        } else {
            this.age = age;
        }
    }

    public staffMember(String fcs, String position, String email, String phone, int age) {
        this(fcs, position, email, phone, 0, age);
    }
    public staffMember(String fcs, String email, String phone, int age) {
        this(fcs, null, email, phone, 0, age);
    }
    public staffMember(String fcs, String email, String phone) {
        this(fcs, null, email, phone, 0, 0);
    }

    //равнозначно переопредению toString();
    public void printStaffMemoryInfo() {
        System.out.println(this);
    }
    //равнозначно printStaffMemoryInfo()
    @Override
    public String toString() {
        return "staffMember{" +
                "fcs='" + fcs + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }


    public static void main(String[] args) {
        //staffMember agentBoris = new staffMember("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        //agentBoris.printStaffMemoryInfo();
        //System.out.println(agentBoris);

        staffMember[] persons = new staffMember[5];
        persons[0] = new staffMember("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 150000, 30);
        persons[1] = new staffMember("Lyuda", "OfficeWoman", "sendme@mailbox.com", "892436661", 75000, 42);
        persons[2] = new staffMember("Veronika", "DinnerWoman", "giveme@mailbox.com", "892436771", 75000, 37);
        persons[3] = new staffMember("Larisa Ivanovna Bondarchyuk", "Mentor", "takeme@mailbox.com", "892322212", 75000);
        persons[4] = new staffMember("Katrin", "Hostess", "commandme@mailbox.com", "892312323", 39);

        System.out.println(ageList(">", 40, persons));
    }

    static String ageList(String simbol, int age, staffMember[] persons) {
        System.out.println();
        if (simbol == ">") {
            for (staffMember person : persons) {
                if (person.age >= age || person.age == 0) {
                    System.out.println(person);
                }
            }
            return "";
        } else if (simbol == "<") {
            for (staffMember person : persons) {
                if (person.age <= age || person.age == 0) {
                    System.out.println(person);
                }
            }
            return "";
        }

        return "данные засекречены или ввод некорректен";
    }

}

/** ✓Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
 * ✓Конструктор класса должен заполнять эти поля при создании объекта;
 * ✓Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
 * ✓Создать массив из 5 сотрудников
 *  Пример:
 *  // Вначале объявляем массив объектов
 *  Person[] persArray = new Person[5];
 *  // потом для каждой ячейки массива задаем объект
 *  persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
 *  persArray[1] = new Person(...);
 *  ...
 *  persArray[4] = new Person(...);
 * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;**/
//Serega, sure