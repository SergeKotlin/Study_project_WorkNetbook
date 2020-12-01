package Second_2_Part.HW2_1;

    import java.util.List;

    public class Cat implements Run, Jump {

    private Integer id;
    private String name;
    private final String defaultName = "some cat";
    private int maxRunDistance;
    private int maxJumpHeight;
    private boolean success;

    Cat(String name, int maxRunDistance, int maxJumpHeight) {
        setName(name);
        setMaxRunDistance(maxRunDistance);
        setMaxJumpHeight(maxJumpHeight);
        success = true;
    }

    Cat(String name) {
        setName(name);
        success = true;
    }

    Cat() {
        setName(defaultName);
        success = true;
    }


    public void setId(Integer id, Participants participants) {
        participants.setId(id);
        this.id = id;
    }

    public void setName(String name) {
        if (name.length() <= 20) {
            this.name = name;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
            this.name = defaultName;
        }
    }

    public void setMaxRunDistance(int maxRunDistance) {
        if (maxRunDistance >= 0) {
            this.maxRunDistance = maxRunDistance;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public void setMaxJumpHeight(int maxJumpHeight) {
        if (maxJumpHeight >= 0) {
            this.maxJumpHeight = maxJumpHeight;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public void setSuccess() {
        System.out.println("Успех прохождения испытаний обнулён");
        success = true;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        System.out.println("Кота зовут " + this.name);
        return name;
    }

    public int[] getRunAndJumpInfo() {
        System.out.println("Максимальная беговая дистанция и высота прыжка для " + this.name +
                ": " + maxRunDistance + ", " + maxJumpHeight);
        return new int[]{maxRunDistance, maxJumpHeight};
    }

    public boolean getSuccess() {
        System.out.println("Успех прохождения испытаний " + this.success);
        return success;
    }

    @Override
    public void run(int runDistance) {
        if (success == true) {
            System.out.print("Кот, мяу, бежит ");
            if (this.maxRunDistance >= runDistance) {
                System.out.println(runDistance + ", успешно!");
            } else {
                System.out.println(runDistance + ", не удалось.");
                success = false;
            }
        } else {
            System.out.println("К соревнованию не допущен");
        }
    }

    @Override
    public void jump(int jumpHeight) {
        if (success == true) {
            System.out.print("Кот, мяу, прыгает ");
            if (this.maxJumpHeight >= jumpHeight) {
                System.out.println(jumpHeight + ", успешно!");
            } else {
                System.out.println(jumpHeight + ", не удалось.");
                success = false;
                System.out.println("Кот " + this.name + " ливает.");
            }
        } else {
            System.out.println("К соревнованию не допущен");
        }
    }
}

