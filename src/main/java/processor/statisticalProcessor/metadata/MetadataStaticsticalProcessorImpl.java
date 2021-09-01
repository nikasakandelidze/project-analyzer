package processor.statisticalProcessor.metadata;

import common.CollectionsWrapper;
import core.VirtualDir;
import core.VirtualFile;
import core.VirtualProject;
import org.apache.commons.io.FilenameUtils;
import processor.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MetadataStaticsticalProcessorImpl implements MetadataStatisticalProcessor {
    @Override
    public ExtensionsStatisticsModel calculateExtensions(VirtualProject project) {
        Map<String, List<String>> filledMap = getFilledMap(project);
        return new ExtensionsStatisticsModel(filledMap);
    }

    private Map<String, List<String>> getFilledMap(VirtualProject project) {
        VirtualDir root = project.getRoot();
        return getSetOfFiles(root);
    }

    private Map<String, List<String>> getSetOfFiles(VirtualDir dir) {
        Map<String, List<String>> resultMap = new HashMap<>();
        List<VirtualFile> files = dir.getFiles();
        files.forEach(e -> {
            String name = e.getName();
            String extension = FilenameUtils.getExtension(name);
            List<String> strings = resultMap.putIfAbsent(extension, CollectionsWrapper.mutableListWithFirstElement(name));
            if (strings != null) {
                strings.add(name);
            }
        });
        dir.getDirectories().stream()
                .map(this::getSetOfFiles)
                .forEach(e -> {
                    e.keySet().forEach(key -> {
                        List<String> value = e.get(key);
                        List<String> strings = resultMap.putIfAbsent(key, value);
                        if (strings != null) {
                            strings.addAll(value);
                        }
                    });
                });
        return resultMap;
    }
}
