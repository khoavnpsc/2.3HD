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

        // Mock the request parameter
        when(request.getParameter("n")).thenReturn("6");

        // Create a StringWriter to capture the response content
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Invoke the servlet's doGet method
        servlet.doGet(request, response);

        // Flush the writer and capture the written content
        writer.flush();
        String responseContent = stringWriter.toString();

        // Verify the response content
        String expectedContent = "The Fibonacci number at position 6 is: 8";
        assertEquals(expectedContent, responseContent);
    }
}
