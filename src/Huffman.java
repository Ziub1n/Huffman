import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Huffman {
    private static Map<Character, String> huffmanCodes = new TreeMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }

        String filePath = args[0];
        List<Character> characters = new ArrayList<>();
        List<Integer> frequencies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int n = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().trim().split(" ");
                characters.add(line[0].charAt(0));
                frequencies.add(Integer.parseInt(line[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        PriorityQueue queue = new PriorityQueue();

        for (int i = 0; i < characters.size(); i++) {
            queue.add(new Node(String.valueOf(characters.get(i)), frequencies.get(i)));
        }

        int step = 1;
        while (queue.size() > 1) {
            System.out.println(step + ": ");
            printQueue(queue);
            Node left = queue.pull();
            Node right = queue.pull();
            Node parent = new Node(left.frequency + right.frequency, left, right);
            queue.add(parent);
            step++;
        }

        Node root = queue.pull();
        generateCodes(root, "");

        System.out.println("\n---Wynik---\n");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    private static void generateCodes(Node root, String code) {
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.characters.charAt(0), code);
            return;
        }
        if (root.left != null) {
            generateCodes(root.left, code + "0");
        }
        if (root.right != null) {
            generateCodes(root.right, code + "1");
        }
    }

    private static void printQueue(PriorityQueue queue) {
        List<Node> nodes = queue.getNodes();
        for (Node node : nodes) {
            System.out.print("(" + node.characters + ", " + node.frequency + ") ");
        }
        System.out.println();
    }


}