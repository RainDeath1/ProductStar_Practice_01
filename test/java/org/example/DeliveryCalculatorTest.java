package org.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCalculatorTest {

    @ParameterizedTest
    @Tag("basic")
    @CsvSource({
            "1.0,   small, false, normal,    400.0",
            "5.0,   small, false, normal,    400.0",
            "15.0,  small, false, normal,    400.0",
            "31.0,  small, false, normal,    400.0",

            "1.0,   large, false, normal,    400.0",
            "15.0,  large, false, normal,    400.0",

            "15.0,  large, true,  normal,    700.0",
            "15.0,  large, true,  elevated,  840.0",
            "15.0,  large, true,  high,      980.0",
            "15.0,  large, true,  very_high, 1120.0"
    })
    void shouldCalculateDeliveryCost(
            double distance,
            String size,
            boolean fragile,
            String workload,
            double expected
    ) {

        double actual = DeliveryCalculator.calculateDeliveryCost(
                distance,
                size,
                fragile,
                workload
        );

        assertEquals(expected, actual, 0.001);
    }


    @ParameterizedTest
    @Tag("edge")
    @CsvSource({
            "0.0,  400.0",
            "2.0,  400.0",
            "10.0, 400.0",
            "30.0, 400.0",
            "30.1, 400.0"
    })
    void shouldHandleDistanceBoundaries(
            double distance,
            double expected
    ) {

        double actual = DeliveryCalculator.calculateDeliveryCost(
                distance,
                "small",
                false,
                "normal"
        );

        assertEquals(expected, actual, 0.001);
    }


    @ParameterizedTest
    @Tag("basic")
    @CsvSource({
            "normal,    700.0",
            "elevated,  840.0",
            "high,      980.0",
            "very_high, 1120.0"
    })
    void shouldApplyWorkloadCoefficient(
            String workload,
            double expected
    ) {

        double actual = DeliveryCalculator.calculateDeliveryCost(
                15,
                "large",
                true,
                workload
        );

        assertEquals(expected, actual, 0.001);
    }


    @Test
    @Tag("edge")
    void deliveryCostShouldNotBeLessThanMinimum() {

        double actual = DeliveryCalculator.calculateDeliveryCost(
                1,
                "small",
                false,
                "normal"
        );

        assertTrue(actual >= 400);
    }


    @Test
    @Tag("error")
    void shouldThrowExceptionForNegativeDistance() {

        assertThrows(
                IllegalArgumentException.class,
                () -> DeliveryCalculator.calculateDeliveryCost(
                        -1,
                        "small",
                        false,
                        "normal"
                )
        );
    }


    @Test
    @Tag("error")
    void shouldThrowExceptionForFragileCargoOver30Km() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> DeliveryCalculator.calculateDeliveryCost(
                        30.1,
                        "small",
                        true,
                        "normal"
                )
        );

        assertTrue(
                exception.getMessage()
                        .contains("Хрупкие грузы нельзя перевозить")
        );
    }


    @Test
    @Tag("error")
    void shouldThrowExceptionForInvalidSize() {

        assertThrows(
                IllegalArgumentException.class,
                () -> DeliveryCalculator.calculateDeliveryCost(
                        10,
                        "medium",
                        false,
                        "normal"
                )
        );
    }


    @Test
    @Tag("error")
    void shouldThrowExceptionForInvalidWorkload() {

        assertThrows(
                IllegalArgumentException.class,
                () -> DeliveryCalculator.calculateDeliveryCost(
                        10,
                        "small",
                        false,
                        "unknown"
                )
        );
    }
}