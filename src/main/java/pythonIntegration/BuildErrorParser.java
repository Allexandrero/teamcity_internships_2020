package pythonIntegration;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;

public class BuildErrorParser {

    @NotNull
    public PythonExceptionImpl parseExceptionMessage(@NotNull String message) {
        return new PythonExceptionImpl("", "", new File("."), Collections.emptyList());
    }
}
