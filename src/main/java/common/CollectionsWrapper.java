package common;

import java.util.ArrayList;
import java.util.List;

public class CollectionsWrapper {

    private CollectionsWrapper() {
    }

    public static <T> List<T> mutableListWithFirstElement(T element) {
        List<T> set = new ArrayList<>();
        set.add(element);
        return set;
    }
}
