package service.statisticalProcessor.metadata;

import domain.VirtualProject;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

public interface MetadataStatisticalProcessor {
    FileExtensionsStatisticsModel calculateExtensions(VirtualProject project);

    static MetadataStatisticalProcessor create() {
        return new MetadataStaticsticalProcessorImpl();
    }
}
