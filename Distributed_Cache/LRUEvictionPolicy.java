import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    private static class DoublyLinkedNode {
        String val;
        DoublyLinkedNode previous;
        DoublyLinkedNode nextNode;

        DoublyLinkedNode(String val) {
            this.val = val;
        }
    }

    private final Map<String, DoublyLinkedNode> nodeTracker = new HashMap<>();
    private final DoublyLinkedNode frontMarker;
    private final DoublyLinkedNode rearMarker;

    public LRUEvictionPolicy() {
        frontMarker = new DoublyLinkedNode("START");
        rearMarker = new DoublyLinkedNode("END");
        frontMarker.nextNode = rearMarker;
        rearMarker.previous = frontMarker;
    }

    @Override
    public void keyAccessed(String key) {
        DoublyLinkedNode existingNode = nodeTracker.get(key);
        
        if (existingNode != null) {
            detachNode(existingNode);
            insertAtFront(existingNode);
        } else {
            DoublyLinkedNode newNode = new DoublyLinkedNode(key);
            nodeTracker.put(key, newNode);
            insertAtFront(newNode);
        }
    }

    @Override
    public String getKeyToEvict() {
        if (rearMarker.previous == frontMarker) {
            return null; 
        }
        return rearMarker.previous.val;
    }

    @Override
    public void removeKey(String key) {
        DoublyLinkedNode target = nodeTracker.remove(key);
        if (target != null) {
            detachNode(target);
        }
    }

    private void insertAtFront(DoublyLinkedNode node) {
        node.nextNode = frontMarker.nextNode;
        node.previous = frontMarker;
        frontMarker.nextNode.previous = node;
        frontMarker.nextNode = node;
    }

    private void detachNode(DoublyLinkedNode node) {
        DoublyLinkedNode prev = node.previous;
        DoublyLinkedNode nxt = node.nextNode;
        
        prev.nextNode = nxt;
        nxt.previous = prev;
        
        
        node.previous = null;
        node.nextNode = null;
    }
}