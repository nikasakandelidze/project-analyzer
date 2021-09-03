package service.output;

import service.statisticalProcessor.metadata.model.ExtensionsStatisticsModel;

import java.util.Optional;

public class ProcessorMessage {
    private final Optional<ExtensionsStatisticsModel> extensionModel;

    public ProcessorMessage(ExtensionsStatisticsModel model) {
        if (model == null) {
            extensionModel = Optional.empty();
        } else {
            extensionModel = Optional.of(model);
        }
    }

    public Optional<ExtensionsStatisticsModel> getExtensionModel() {
        return extensionModel;
    }
}
