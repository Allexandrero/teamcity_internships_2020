package python_integration;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public interface PythonException {
    @NotNull
    String getExceptionType();

    @NotNull
    String getExceptionMessage();

    @NotNull
    File getExceptionLocation();

    @NotNull
    List<ExceptionStackTraceElement> getStackTrace();
}