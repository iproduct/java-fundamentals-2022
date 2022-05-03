package course.java.simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class TestingAStackDemo {
    Stack<Object> stack;

    @Nested
    @DisplayName("when new")
    class WhenNew {
        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPoped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }
    }

    @Nested
    @DisplayName("after pushing an element")
    class AfterPushing {
        String anElement = "an element";

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
            stack.push(anElement);
        }

        @Test
        @DisplayName("is no longer empty")
        void isEmpty() {
            assertFalse(stack.isEmpty());
        }

        @Test
        @DisplayName("returns element when poped")
        void returnsElementWhenPoped() {
            assertEquals(anElement, stack.pop());
            assertTrue(stack.isEmpty(), "Stack should be empty after pop()");
        }
    }
}
