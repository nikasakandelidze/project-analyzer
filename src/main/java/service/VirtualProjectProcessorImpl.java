package service;

import domain.VirtualProject;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

class VirtualProjectProcessorImpl implements VirtualProjectProcessor {
    private final MetadataStatisticalProcessor metadataStatisticalProcessor;

    VirtualProjectProcessorImpl(MetadataStatisticalProcessor metadataStatisticalProcessor) {
        this.metadataStatisticalProcessor = metadataStatisticalProcessor;
    }

    @Override
    public ProcessorMessage processVirtualProject(VirtualProject project) {
        ExtensionsStatisticsModel extensionsStatisticsModel = metadataStatisticalProcessor.calculateExtensions(project);
        ProcessorMessage message = new ProcessorMessage(extensionsStatisticsModel);
        return message;
    }
}
