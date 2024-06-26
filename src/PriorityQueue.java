import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class PriorityQueue {
    private List<Node> nodes;

    public PriorityQueue() {
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        nodes.add(node);
        nodes.sort(Comparator.comparingInt(n -> n.frequency));
    }

    public Node pull() {
        return nodes.remove(0);
    }

    public int size() {
        return nodes.size();
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }
}
