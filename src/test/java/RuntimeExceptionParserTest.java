import org.junit.Assert;
import org.junit.Test;
import python_integration.ExceptionStackTraceElement;
import python_integration.PythonExceptionImpl;
import python_integration.RuntimeExceptionParser;

import java.util.List;

public class RuntimeExceptionParserTest {
    private final RuntimeExceptionParser parser = new RuntimeExceptionParser();

    @Test
    public void testShouldParseRuntimeException() {
        String message = "Traceback (most recent call last):\n" +
                "  File \"file.py\", line 7, in <module>\n" +
                "    main()\n" +
                "  File \"file.py\", line 4, in main\n" +
                "    library.maths()\n" +
                "  File \"/home/admin/library.py\", line 2, in maths\n" +
                "    return 0 / 0\n" +
                "ZeroDivisionError: division by zero";

        PythonExceptionImpl exception = parser.parseExceptionMessage(message);

        Assert.assertEquals("ZeroDivisionError", exception.getExceptionType());
        Assert.assertEquals("division by zero", exception.getExceptionMessage());
        Assert.assertEquals("/home/admin/library.py", exception.getExceptionLocation().getAbsolutePath());

        List<ExceptionStackTraceElement> stackTrace = exception.getStackTrace();
        Assert.assertEquals(3, stackTrace.size());

        ExceptionStackTraceElement exceptionSource = stackTrace.get(0);
        Assert.assertEquals("/home/admin/library.py", exceptionSource.getLocation().getAbsolutePath());
        Assert.assertEquals("maths", exceptionSource.getCodeLocation());
        Assert.assertEquals(2, exceptionSource.getLine());
    }
}
