class Node {
    String characters;
    int frequency;
    Node left;
    Node right;

    Node(String characters, int frequency) {
        this.characters = characters;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    Node(int frequency, Node left, Node right) {
        this.characters = left.characters + right.characters;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
