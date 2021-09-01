package processor.statisticalProcessor.metadata.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExtensionsStatisticsModel {
    private final Map<String, List<String>> extensions;

    public ExtensionsStatisticsModel(Map<String, List<String>> extensions) {
        this.extensions = extensions;
    }

    public long getNumberOfFilesWithExtension(String extension) {
        return extensions.getOrDefault(extension, List.of()).size();
    }

    public List<String> getNamesOfFilesWithExtension(String extension) {
        return extensions.getOrDefault(extension, List.of());
    }

    public Set<String> getExtensions() {
        return extensions.keySet();
    }
}
