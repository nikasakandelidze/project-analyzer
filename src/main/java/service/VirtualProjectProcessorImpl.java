package service;

import domain.VirtualProject;
import service.gitProcessor.GitDataMessage;
import service.gitProcessor.GitProcessor;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;
import service.tree.TreeMessage;
import service.tree.TreeProcessor;

import java.util.function.Consumer;

class VirtualProjectProcessorImpl implements VirtualProjectProcessor {
    private final MetadataStatisticalProcessor metadataStatisticalProcessor;
    private final HelpMessageBuilder helpMessageBuilder;
    private final GitProcessor gitProcessor;
    private final TreeProcessor treeProcessor;

    VirtualProjectProcessorImpl(MetadataStatisticalProcessor metadataStatisticalProcessor, HelpMessageBuilder helpMessageBuilder, GitProcessor gitProcessor, TreeProcessor treeProcessor) {
        this.metadataStatisticalProcessor = metadataStatisticalProcessor;
        this.helpMessageBuilder = helpMessageBuilder;
        this.gitProcessor = gitProcessor;
        this.treeProcessor = treeProcessor;
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

    @Override
    public TreeMessage processTree(VirtualProject virtualProject, Consumer<String> dataConsumer) {
        return treeProcessor.processTree(virtualProject, dataConsumer);
    }
}
