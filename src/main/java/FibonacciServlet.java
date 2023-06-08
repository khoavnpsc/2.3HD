import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FibonacciServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int n = Integer.parseInt(request.getParameter("n"));
        int result = calculateFibonacci(n);

        response.setContentType("text/plain");
        response.getWriter().write("The Fibonacci number at position " + n + " is: " + result);
    }

    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int prev = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int temp = current;
            current = prev + current;
            prev = temp;
        }

        return current;
    }
}
