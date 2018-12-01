import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

public class BadMain {

    public static void main(String[] args) {

        final String TASK = "Найдите площадь треугольника? со сторонами 3, 3, 3";

        Pattern pattern = Pattern.compile("\\b(треугол.+?)\\b");
        Matcher matcher = pattern.matcher(TASK);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }


        Pattern pattern1 = Pattern.compile("\\b\\d+\\b");
        Matcher matcher1 = pattern1.matcher(TASK);

        List<Double> sides = new ArrayList<>();
        while (matcher1.find()) {
            sides.add(Double.parseDouble(matcher1.group()));
            System.out.println(matcher1.group());
        }
        System.out.println(String.format("sides: %s", sides));


        if (!((abs(sides.get(0) - sides.get(1)) < sides.get(2) && sides.get(2) < sides.get(0) + sides.get(1)) &&
                (abs(sides.get(0) - sides.get(2)) < sides.get(1) && sides.get(1) < sides.get(0) + sides.get(2)) &&
                (abs(sides.get(1) - sides.get(2)) < sides.get(0) && sides.get(0) < sides.get(1) + sides.get(2))))
            return;
        System.out.println("Все нормально");


        double a = sides.get(0);
        double b = sides.get(1);
        double c = sides.get(2);

        double semi = 0.5 * (a + b + c);
        double area = sqrt(semi * (semi - a) * (semi - b) * (semi -c));

        System.out.println(String.format("Площадь треугольника = %.2f", area));
    }
}
