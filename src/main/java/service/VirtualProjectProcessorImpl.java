package service;

import domain.VirtualProject;
import service.gitProcessor.GitDataMessage;
import service.gitProcessor.GitProcessor;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

class VirtualProjectProcessorImpl implements VirtualProjectProcessor {
    private final MetadataStatisticalProcessor metadataStatisticalProcessor;
    private final HelpMessageBuilder helpMessageBuilder;
    private final GitProcessor gitProcessor;

    VirtualProjectProcessorImpl(MetadataStatisticalProcessor metadataStatisticalProcessor, HelpMessageBuilder helpMessageBuilder, GitProcessor gitProcessor) {
        this.metadataStatisticalProcessor = metadataStatisticalProcessor;
        this.helpMessageBuilder = helpMessageBuilder;
        this.gitProcessor = gitProcessor;
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

    @Override
    public GitDataMessage processGitData(String path) {
        return gitProcessor.processGitData(path);
    }
}
