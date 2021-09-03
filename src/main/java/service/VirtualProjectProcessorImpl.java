package service;

import domain.VirtualProject;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

class VirtualProjectProcessorImpl implements VirtualProjectProcessor {
    private final MetadataStatisticalProcessor metadataStatisticalProcessor;
    private final HelpMessageBuilder helpMessageBuilder;

    VirtualProjectProcessorImpl(MetadataStatisticalProcessor metadataStatisticalProcessor, HelpMessageBuilder helpMessageBuilder) {
        this.metadataStatisticalProcessor = metadataStatisticalProcessor;
        this.helpMessageBuilder = helpMessageBuilder;
    }

    @Override
    public ProcessorMessage processVirtualProject(VirtualProject project) {
        FileExtensionsStatisticsModel fileExtensionsStatisticsModel = metadataStatisticalProcessor.calculateExtensions(project);
        return new ProcessorMessage(fileExtensionsStatisticsModel);
    }

    @Override
    public HelpMessage buildHelpMessage() {
        return helpMessageBuilder.getHelpMessage();
    }
}
