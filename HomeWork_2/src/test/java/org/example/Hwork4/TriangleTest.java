package org.example.Hwork4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Test
    @DisplayName("Норма")
    public void testNorma() throws MyExceptions {
        int a = 13;
        int b = 24;
        int c = 18;
        logger.info("Запуск теста Норма с данными: " + a + ", "+ b + ", " + c );
        TriangleArea triangleArea = new TriangleArea();
        Assertions.assertEquals(115.145289,triangleArea.triangleArea(a,b,c));
        logger.info("Тест Норма с данными: " + a + ", " + b + ", " + c + " завершён успешно");
    }

    @Test
    @DisplayName("Неверное сравнение") // крайне искусственно и ради лога :о)
    public void testUnNormal() throws MyExceptions {
        int a = 15;
        int b = 24;
        int c = 18;
        logger.info("Запуск теста Неверное сравнение с данными: " + a + ", "+ b + ", " + c );
        TriangleArea triangleArea = new TriangleArea();
        Assertions.assertNotEquals(115.145289,triangleArea.triangleArea(a,b,c));
        logger.error("Ожидаемый результат не соответствует исходным данным :о)))");
    }

    @Test
    @DisplayName("Не треугольник")
    public void testNotTriangle() throws MyExceptions {
        logger.info("Запуск теста 'Не треугольник' с данными: 213, 24, 18");
        TriangleArea triangleArea = new TriangleArea();
        Assertions.assertThrows(MyExceptions.class,()-> triangleArea.triangleArea(213,24,18));
        logger.info("Тест 'Не треугольник' завершён успешно");
    }

    @Test
    @DisplayName("Сторона=0")
    public void testNotTriangle0Side() throws MyExceptions {
        logger.info("Запуск теста 'Сторона=0' с данными: 0, 24, 18");
        TriangleArea triangleArea = new TriangleArea();
        Assertions.assertThrows(MyExceptions.class,()-> triangleArea.triangleArea(0,24,18));
        logger.info("Тест 'Сторона=0' завершён успешно");
    }

    @Test
    @DisplayName("Отрицательная сторона")
    public void testNotTriangleNegativeSide() throws MyExceptions {
        logger.info("Запуск теста 'Отрицательная сторона' с данными: -10, 24, 18");
        TriangleArea triangleArea = new TriangleArea();
        Assertions.assertThrows(MyExceptions.class,()-> triangleArea.triangleArea(-10,24,18));
        logger.info("Тест 'Отрицательная сторона' завершён успешно");
    }
}
