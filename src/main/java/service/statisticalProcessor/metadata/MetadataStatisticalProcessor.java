package service.statisticalProcessor.metadata;

import domain.VirtualProject;
import service.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

public interface MetadataStatisticalProcessor {
    ExtensionsStatisticsModel calculateExtensions(VirtualProject project);

    static MetadataStatisticalProcessor create() {
        return new MetadataStaticsticalProcessorImpl();
    }
}
