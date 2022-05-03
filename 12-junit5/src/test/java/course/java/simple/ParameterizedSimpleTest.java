package course.java.simple;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParameterizedSimpleTest {

    @ParameterizedTest
    @ValueSource(strings={"racecar", "radar", "able was I ere I saw elba"})
    void testPalindroms(String candidate) {
        assertTrue(StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest
    @MethodSource("stringAndBooleanResultProvider")
    void testPalindromsMethodSource(String candidate, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.isPalindrome(candidate));
    }

    static Stream<Arguments> stringAndBooleanResultProvider(){
        return Stream.of(
               arguments("racecar", true),
               arguments("radar", true),
               arguments("able was I ere I saw elba", true),
               arguments("byl hlyb", false)
        );
    }
}
