import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

@Slf4j
public class TrueMain {

    private static final String TASK = "Найдите площадь треугольника? со сторонами 3, 3, 3";
    private static final Pattern TRIANGLE_PATTERN = Pattern.compile("\\b(треугол.+?)\\b");
    private static final Pattern SIDES_PATTERN = Pattern.compile("\\b\\d+\\b");

    public static void main(String[] args) {
        try {
            double area = doTask(TASK);
            log.info(String.format("Площадь треугольника = %.2f", area));
        } catch (Exception ex) {
            log.error("Ошибка выполнения задания", ex);
        }
    }


    public static double doTask(String task) {
        if (!matchTriangle(task)) {
            throw new RuntimeException("Треугольник не найден");
        }
        List<Double> sides = parseTriangleSides(task);
        log.info("Стороны треугольника: {}", sides);

        if (!validateTriangleSides(sides)) {
            throw new RuntimeException("Не существует треугольник с такими сторонами");
        }

        return calculateTriangleArea(sides);
    }


    public static boolean matchTriangle(String task) {
        return task != null && makeMatcher(task, TRIANGLE_PATTERN).find();
    }

    public static List<Double> parseTriangleSides(String task) {
        List<Double> sides = new ArrayList<>();
        Matcher matcherSides = makeMatcher(task, SIDES_PATTERN);
        while (matcherSides.find()) {
            sides.add(Double.parseDouble(matcherSides.group()));
        }
        return sides;
    }

    public static Matcher makeMatcher(String task, Pattern pattern) {
        return pattern.matcher(task);
    }

    public static boolean validateTriangleSides(List<Double> sides) {
        if (sides == null || sides.size() != 3) {
            return false;
        }

        return ((abs(sides.get(0) - sides.get(1)) < sides.get(2) && sides.get(2) < sides.get(0) + sides.get(1)) &&
                (abs(sides.get(0) - sides.get(2)) < sides.get(1) && sides.get(1) < sides.get(0) + sides.get(2)) &&
                (abs(sides.get(1) - sides.get(2)) < sides.get(0) && sides.get(0) < sides.get(1) + sides.get(2)));
    }

    public static double calculateTriangleArea(List<Double> sides) {
        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);

        double semi = 0.5 * (a + b + c);
        double area = sqrt(semi * (semi - a) * (semi - b) * (semi - c));

        return Math.round(area * 100.0) / 100.0;
    }
}
