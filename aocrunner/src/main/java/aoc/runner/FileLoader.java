package aoc.runner;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class FileLoader {
    static final Logger logger = LoggerFactory.getLogger(FileLoader.class);

    // ----------++- FILE UTILITIES -++---------------------------------------
    public static @NotNull Optional<URI> checkedToURI(@NotNull URL url) {
        try {
            return Optional.of(url.toURI());
        } catch (URISyntaxException e) {
            logger.error("File path {} generated a syntax exception", url, e);
        }
        return Optional.empty();
    }

    public static @NotNull Optional<Path> findFile(Days forDay) {
        return Optional.ofNullable(Booter.class.getResource(forDay.getFileNameWithPackage()))
                .or(() -> Optional.ofNullable(Booter.class.getResource(forDay.getFileName())))
                .flatMap(FileLoader::checkedToURI)
                .map(Path::of)
                .filter(Files::exists)
                //try to find the file normally if .getResource failed
                .or(() -> Optional.of(Path.of(forDay.getFileNameWithPackage())))
                .filter(Files::exists)
                .or(() -> Optional.of(Path.of(forDay.getFileName())))
                .filter(Files::exists);
    }

    public static @NotNull Optional<Stream<String>> readFile(@NotNull Path path) {
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Unable to read non-existing file");
        }
        //TODO change to loan pattern
        try {
            return Optional.of(Files.lines(path));
        } catch (IOException e) {
            logger.error("ERROR reading file: {}", path);
        }
        return Optional.empty();
    }

    public static void writeFile(@NotNull Path path, @NotNull Collection<String> input) {
        try {
            Files.write(path, input);
        } catch (IOException e) {
            logger.error("Failed to write to file {}", path.toAbsolutePath(), e);
        }
    }

    public static void writeOrCreateFile(@NotNull Collection<String> input, Days forDay) {
        //TODO find the correct path without needing string manipulation
        Path path1 = findFile(forDay).orElse(Path.of(forDay.getFileNameWithPackage()));
        logger.debug("Path for input file to write is '{}'", path1.toAbsolutePath());
        Path path2 = Path.of(path1.toAbsolutePath().toString().replace("/target/classes/", "/src/main/resources/"));
        logger.debug("Path for input file changed to '{}'", path2.toAbsolutePath());

        writeFile(path1, input);
        writeFile(path2, input);
    }

    public static @NotNull Optional<Stream<String>> readInput(int dayIndex) {
        return readInput(Days.dayFromInt(dayIndex));
    }

    public static @NotNull Optional<Stream<String>> readInput(Days forDay) {
        return findFile(forDay)
                .flatMap(FileLoader::readFile);
    }
}
