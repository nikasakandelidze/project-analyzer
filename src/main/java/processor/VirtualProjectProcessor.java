package processor;

import core.VirtualProject;
import processor.output.ProcessorMessage;
import processor.statisticalProcessor.metadata.MetadataStatisticalProcessor;

public interface VirtualProjectProcessor {
    ProcessorMessage processVirtualProject(VirtualProject project);

    static VirtualProjectProcessor create() {
        MetadataStatisticalProcessor metadataStatisticalProcessor = MetadataStatisticalProcessor.create();
        return new VirtualProjectProcessorImpl(metadataStatisticalProcessor);
    }
}
