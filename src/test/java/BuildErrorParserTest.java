import org.junit.Assert;
import org.junit.Test;
import python_integration.BuildErrorParser;
import python_integration.ExceptionStackTraceElement;
import python_integration.PythonExceptionImpl;

import java.util.List;

public class BuildErrorParserTest {
    private final BuildErrorParser parser = new BuildErrorParser();

    @Test
    public void testShouldParseRuntimeException() {
        String message = "Traceback (most recent call last):\n" +
                "  File \"file.py\", line 1, in <module>\n" +
                "    import library\n" +
                "  File \"/home/admin/library.py\", line 2\n" +
                "    return 1 1 1 1\n" +
                "             ^\n" +
                "SyntaxError: invalid syntax";

        PythonExceptionImpl exception = parser.parseExceptionMessage(message);

        Assert.assertEquals("SyntaxError", exception.getExceptionType());
        Assert.assertEquals("invalid syntax", exception.getExceptionMessage());
        Assert.assertEquals("/home/admin/library.py", exception.getExceptionLocation().getAbsolutePath());

        List<ExceptionStackTraceElement> stackTrace = exception.getStackTrace();
        Assert.assertEquals(2, stackTrace.size());

        ExceptionStackTraceElement exceptionSource = stackTrace.get(0);
        Assert.assertEquals("/home/admin/library.py", exceptionSource.getLocation().getAbsolutePath());
        Assert.assertEquals(2, exceptionSource.getLine());
    }
}
