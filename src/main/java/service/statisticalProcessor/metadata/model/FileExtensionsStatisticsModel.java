package service.statisticalProcessor.metadata.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileExtensionsStatisticsModel {
    private final Map<String, List<String>> extensions;
    private final Map<String, Integer> countsWithExtensions;

    public FileExtensionsStatisticsModel(Map<String, List<String>> extensions) {
        this.extensions = extensions;
        countsWithExtensions = new HashMap<>();
        extensions.keySet().stream().filter(e -> !e.isEmpty()).forEach(e -> {
            countsWithExtensions.put(e, extensions.get(e).size());
        });
    }

    public long getNumberOfFilesWithExtension(String extension) {
        return extensions.getOrDefault(extension, List.of()).size();
    }

    public List<String> getNamesOfFilesWithExtension(String extension) {
        return extensions.getOrDefault(extension, List.of());
    }

    public Map<String, Integer> getMapWithExtensionCounts() {
        return countsWithExtensions;
    }

    public Set<String> getExtensions() {
        return extensions.keySet();
    }
}
