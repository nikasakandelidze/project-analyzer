package service;

import domain.VirtualProject;
import service.gitProcessor.GitDataMessage;
import service.gitProcessor.GitProcessor;
import service.help.HelpMessage;
import service.help.HelpMessageBuilder;
import service.output.ProcessorMessage;
import service.statisticalProcessor.metadata.MetadataStatisticalProcessor;
import service.tree.TreeMessage;
import service.tree.TreeProcessor;

import java.util.function.Consumer;

public interface VirtualProjectProcessor {
    ProcessorMessage processVirtualProject(VirtualProject project);

    HelpMessage buildHelpMessage();

    GitDataMessage processGitData(String path);

    TreeMessage processTree(VirtualProject virtualProject, Consumer<String> dataConsumer);

    static VirtualProjectProcessor create() {
        MetadataStatisticalProcessor metadataStatisticalProcessor = MetadataStatisticalProcessor.create();
        HelpMessageBuilder helpMessageBuilder = HelpMessageBuilder.create();
        GitProcessor gitProcessor = GitProcessor.create();
        TreeProcessor treeProcessor = TreeProcessor.create();
        return new VirtualProjectProcessorImpl(metadataStatisticalProcessor, helpMessageBuilder, gitProcessor, treeProcessor);
    }
}
