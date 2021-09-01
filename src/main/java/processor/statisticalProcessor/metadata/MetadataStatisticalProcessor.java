package processor.statisticalProcessor.metadata;

import core.VirtualProject;
import processor.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

public interface MetadataStatisticalProcessor {
    ExtensionsStatisticsModel calculateExtensions(VirtualProject project);

    static MetadataStatisticalProcessor create() {
        return new MetadataStaticsticalProcessorImpl();
    }
}
