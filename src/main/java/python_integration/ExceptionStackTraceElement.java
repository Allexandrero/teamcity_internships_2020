package python_integration;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ExceptionStackTraceElement {
    @NotNull
    private final File location;
    private final int line;
    @NotNull
    private final String codeLocation;

    public ExceptionStackTraceElement(@NotNull File location, int line, @NotNull String codeLocation) {
        this.location = location;
        this.line = line;
        this.codeLocation = codeLocation;
    }

    @NotNull
    public File getLocation() {
        return location;
    }

    public int getLine() {
        return line;
    }

    @NotNull
    public String getCodeLocation() {
        return codeLocation;
    }
}
