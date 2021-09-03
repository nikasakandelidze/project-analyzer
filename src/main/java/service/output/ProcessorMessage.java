package service.output;

import service.statisticalProcessor.metadata.model.FileExtensionsStatisticsModel;

import java.util.Optional;

public class ProcessorMessage {
    private final Optional<FileExtensionsStatisticsModel> extensionModel;

    public ProcessorMessage(FileExtensionsStatisticsModel model) {
        if (model == null) {
            extensionModel = Optional.empty();
        } else {
            extensionModel = Optional.of(model);
        }
    }

    public Optional<FileExtensionsStatisticsModel> getExtensionModel() {
        return extensionModel;
    }
}
