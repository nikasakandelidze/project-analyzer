package parser.utils;

import java.io.File;
import java.util.Optional;

public class ParserUtils {
    private ParserUtils() {
    }

    public static Optional<String> getNameForFolder(String pathToFolder) {
        if (pathToFolder == null || pathToFolder.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new File(pathToFolder).getName());
    }
}
