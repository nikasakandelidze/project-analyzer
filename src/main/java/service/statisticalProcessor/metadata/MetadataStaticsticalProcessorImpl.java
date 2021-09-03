package service.statisticalProcessor.metadata;

import common.CollectionsWrapper;
import domain.VirtualDir;
import domain.VirtualFile;
import domain.VirtualProject;
import org.apache.commons.io.FilenameUtils;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MetadataStaticsticalProcessorImpl implements MetadataStatisticalProcessor {
    @Override
    public FileExtensionsStatisticsModel calculateExtensions(VirtualProject project) {
        Map<String, List<String>> filledMap = getFilledMap(project);
        return new FileExtensionsStatisticsModel(filledMap);
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
