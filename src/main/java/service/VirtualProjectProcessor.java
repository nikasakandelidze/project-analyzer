package service;

import domain.VirtualProject;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;

public interface VirtualProjectProcessor {
    ProcessorMessage processVirtualProject(VirtualProject project);

    HelpMessage buildHelpMessage();

    static VirtualProjectProcessor create() {
        MetadataStatisticalProcessor metadataStatisticalProcessor = MetadataStatisticalProcessor.create();
        HelpMessageBuilder helpMessageBuilder = HelpMessageBuilder.create();
        return new VirtualProjectProcessorImpl(metadataStatisticalProcessor, helpMessageBuilder);
    }
}
