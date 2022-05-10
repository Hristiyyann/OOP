import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        material m1 = new material("Wood", 20, 15);
        material m2 = new material("Metal", 10, 25);

        ArrayList<material> materials_for_chair_1 = new ArrayList<>();
        materials_for_chair_1.add(m1);

        ArrayList<material> materials_for_desk_1 = new ArrayList<>();
        materials_for_desk_1.add(m2);

        chair c1 = new chair("big stol", materials_for_chair_1);
        desk d1 = new desk("small desk", materials_for_desk_1);

        System.out.println(d1.getPrice());
    }
}
