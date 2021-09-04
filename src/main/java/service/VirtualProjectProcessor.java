package service;

import domain.VirtualProject;
import service.gitProcessor.GitDataMessage;
import service.gitProcessor.GitProcessor;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;

public interface VirtualProjectProcessor {
    ProcessorMessage processVirtualProject(VirtualProject project);

    HelpMessage buildHelpMessage();

    GitDataMessage processGitData(String path);

    static VirtualProjectProcessor create() {
        MetadataStatisticalProcessor metadataStatisticalProcessor = MetadataStatisticalProcessor.create();
        HelpMessageBuilder helpMessageBuilder = HelpMessageBuilder.create();
        GitProcessor gitProcessor = GitProcessor.create();
        return new VirtualProjectProcessorImpl(metadataStatisticalProcessor, helpMessageBuilder, gitProcessor);
    }
}
