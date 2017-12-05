package Functions;

import java.util.ArrayList;
import java.util.List;

public class Input {
    private List<String> ids;
    private List<Double> values;

    public Input() {
        ids = new ArrayList<>();
        values = new ArrayList<>();
    }

    public Input(double in) {
        ids = new ArrayList<>();
        values = new ArrayList<>();
        ids.add("x");
        values.add(in);
    }

    public Input(List<String> ids, List<Double> values) {
        if (ids.size() == values.size()) {
            this.ids = ids;
            this.values = values;
        } else {
            throw new IllegalArgumentException("Your inputs should have same length");
        }
    }

    public List<String> getIds() {
        return ids;
    }

    public List<Double> getValues() {
        return values;
    }

    public void addVal(String id, double value) {
        if (!ids.contains(id)) {
            ids.add(id);
            values.add(value);
        } else {
            throw new IllegalArgumentException("Already have that variable name dumbass");
        }
    }

    public double getValueAt(String id) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(id)) {
                return values.get(i);
            }
        }
        throw new IllegalArgumentException("That variable was not found");
    }
}
