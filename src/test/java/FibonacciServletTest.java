import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FibonacciServletTest {
    @Test
    public void testFibonacciServlet() throws ServletException, IOException {
        FibonacciServlet servlet = new FibonacciServlet();

        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Test Case 1: Fibonacci number at position 6
        when(request.getParameter("n")).thenReturn("6");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        String responseContent = stringWriter.toString();

        String expectedContent = "The Fibonacci number at position 6 is: 8";
        assertEquals(expectedContent, responseContent);

        // Test Case 2: Fibonacci number at position 0
        when(request.getParameter("n")).thenReturn("0");
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        responseContent = stringWriter.toString();

        expectedContent = "The Fibonacci number at position 0 is: 0";
        assertEquals(expectedContent, responseContent);

        // Test Case 3: Fibonacci number at position 10
        when(request.getParameter("n")).thenReturn("10");
        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        writer.flush();
        responseContent = stringWriter.toString();

        expectedContent = "The Fibonacci number at position 10 is: 55";
        assertEquals(expectedContent, responseContent);
    }
}
