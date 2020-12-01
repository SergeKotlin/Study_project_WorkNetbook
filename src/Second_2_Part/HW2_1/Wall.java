package Second_2_Part.HW2_1;

public class Wall {
    private String name = "Wall";
    private int heightWall;

    public Wall(int heightWall) {
        this.heightWall = heightWall;
    }

    public void setHeightWall(int heightWall) {
        if (heightWall >= 0) {
            this.heightWall = heightWall;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public void setName(String name) {
        if (name.length() <= 20) {
            this.name = name;
        } else {
            System.out.println("Вы ввели что-то непонятное ");
        }
    }

    public int getHeightWall() {
        return heightWall;
    }

    public String getName() {
        return this.name;
    }
}
