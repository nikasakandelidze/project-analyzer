package service;

import domain.VirtualProject;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;

public interface VirtualProjectProcessor {
    ProcessorMessage processVirtualProject(VirtualProject project);

    static VirtualProjectProcessor create() {
        MetadataStatisticalProcessor metadataStatisticalProcessor = MetadataStatisticalProcessor.create();
        return new VirtualProjectProcessorImpl(metadataStatisticalProcessor);
    }
}
