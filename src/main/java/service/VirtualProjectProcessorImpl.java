package service;

import domain.VirtualProject;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

class VirtualProjectProcessorImpl implements VirtualProjectProcessor {
    private final MetadataStatisticalProcessor metadataStatisticalProcessor;

    VirtualProjectProcessorImpl(MetadataStatisticalProcessor metadataStatisticalProcessor) {
        this.metadataStatisticalProcessor = metadataStatisticalProcessor;
    }

    @Override
    public ProcessorMessage processVirtualProject(VirtualProject project) {
        FileExtensionsStatisticsModel fileExtensionsStatisticsModel = metadataStatisticalProcessor.calculateExtensions(project);
        return new ProcessorMessage(fileExtensionsStatisticsModel);
    }
}
