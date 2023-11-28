import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Nombres {
    private static final List<String> allNames = Arrays.asList(
            "Alejandro", "Alberto", "Beatriz", "Carlos", "Diana", "Eduardo", "Frida", "Gabriel", "Hilda", "Maria",
            "Juana", "Luis", "Maria", "Nacho", "Ofelia", "Pablo", "Quetzal", "Rosa", "Sergio", "Teresa", "Uriel",
            "Valentina", "Walter", "Ximena", "Yahir", "Zaida", "Adriana", "Benito", "Carmen", "Diego", "Elena",
            "Fernando", "Gloria", "Hector", "Ignacio", "Isabel", "Javier", "Karla", "Lorena", "Miguel", "Natalia",
            "Oscar", "Patricia", "Quirino", "Rocio", "Raquel", "Magdalena", "Salvador", "Trinidad", "Ursula", "Victor",
            "Wendy",
            "Xochitl", "Yolanda", "Jorge", "Aurora", "Beto", "Clara", "Daniel", "Esperanza", "Francisco", "Graciela",
            "Hugo",
            "Ines", "Jaime", "Karen", "Luz", "Manuel", "Nancy", "Omar", "Paulina", "Rafael", "Silvia", "Tomas",
            "Ursula",
            "Violeta", "Wilfredo", "Xochitl", "Yadira");
    private static ArrayList<String> names = new ArrayList<>();

    public synchronized static String getName() {
        if (names.size() == 0) {
            names = new ArrayList<>(allNames);
            Collections.shuffle(names); // Shuffle the names to get a random order
        }
        Random random = new Random();
        int d = random.nextInt(names.size());
        String name = names.get(d);
        names.remove(d);
        return name;
    }
}
