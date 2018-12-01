import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

public class TrueMainTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testMatchTriangle() {
        assertFalse(TrueMain.matchTriangle(null));
        assertFalse(TrueMain.matchTriangle(""));
        assertFalse(TrueMain.matchTriangle("треуго1"));
        assertTrue(TrueMain.matchTriangle("треугольник"));
    }

    @Test
    public void testParseTriangleSides() {
        assertEquals(asList(3.0, 4.0, 5.0), TrueMain.parseTriangleSides("Найдите площадь треугольника? со сторонами 3, 4, 5"));
        assertEquals(emptyList(), TrueMain.parseTriangleSides("Найдите площадь треугольника? с разными сторонами"));
        assertEquals(asList(0.4, 9.4, 9.0), TrueMain.parseTriangleSides("Найдите площадь треугольника? с разными сторонами 0.4, 9.4, 9.0"));
    }

    @Test
    public void testValidateTriangleSides() {
        assertFalse(TrueMain.validateTriangleSides(emptyList()));
        assertFalse(TrueMain.validateTriangleSides(null));
        assertTrue(TrueMain.validateTriangleSides(asList(3.0, 5.0, 7.0)));
        assertFalse(TrueMain.validateTriangleSides(asList(3.0, 2.0, 7.0)));
    }

    @Test
    public void testCalculateTriangleArea() {
        assertEquals(6.4, TrueMain.calculateTriangleArea(asList(3.0, 5.0, 7.0)));
    }

    @Test
    public void testDoTaskNotFoundTriangle() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Треугольник не найден");
        TrueMain.doTask("");
    }

    @Test
    public void testDoTaskTriangleNotExisted() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Не существует треугольник с такими сторонами");
        TrueMain.doTask("Дан треугольник со сторонами 2, 5, 7");
    }
}