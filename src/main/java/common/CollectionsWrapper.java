package common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsWrapper {

    private CollectionsWrapper() {
    }

    public static List<String> mutableListWithFirstElement(String element) {
        List<String> set = new ArrayList<>();
        set.add(element);
        return set;
    }
}
