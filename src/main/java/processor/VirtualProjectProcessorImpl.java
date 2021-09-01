package processor;

import core.VirtualProject;
import processor.output.ProcessorMessage;
import processor.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import processor.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

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
