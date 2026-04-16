package Design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 */
public class LRUCache {

    /**
     * Use hashmap -  store key-value pairs.
     * <p>
     * Use Queue - When capacity is reached, we need to remove the least recently used element. Once evicted, we need to know what is next LRU element to be evicted next time
     * The key at the front of the queue is the least recently used key, and the key at the back of the queue is the most recently used key
     * When we insert a key for the first time, we put it in the back of the queue.
     * When we use an existing key (with either get or put), we locate it in the queue and move it to the back as it is accessed.
     * If the data structure exceeds capacity, we can reference the front of the queue to find the key that should be deleted.
     * We need a way to implement this queue such that the operations will run in O(1).
     * <p>
     * Doubly Linked List - We need ability to remove the element from any position in constant time given node's address and we need capability to add and rmeove at the front/back/
     * In general, if we want to remove a node from the list, we need a pointer to the node before it. Because of this, we shall use a doubly linked list. That way, when we want to remove a node, we have a prev pointer to reference the node before it.
     * <p>
     * Use hashmap [map each key to its associated node (int: LRUCacheNode).]
     * For O(1) retrievals and exisitence checks
     * O(1) to find reference of the elemt in the linkedlist or queue
     * <p>
     * Data structure to implement
     * 1. Store, Retrieve, Delete key-value pair : HashMap and Doubly LL
     * 2. Given a key, return value if it exissts or return -1 : HashMap and Doubly LL
     * 3. Insert new element - insert at the back of the queue : HashMap and Doubly LL
     * 4. When element is accessed - move it to the back of the queue :  HashMap and Doubly LL
     * 5. When capacity is met, remove element from the front of the queue : DoublyLL
     * <p>
     * <p>
     * For our LRUCache class, we need the following attributes:
     * capacity - to detect when we need to start deleting key-value pairs.
     * dict - sthis will be our hash map that maps keys to nodes.
     * head - the head of our linked list
     * tail - the tail of our linked list
     * <p>
     * <p>
     * The cleanest way to handle the empty list case is by using sentinel nodes.
     * We will have our head and tail attributes both set to dummy nodes.
     * The "real" head will be head.next and the "real" tail will be tail.prev.
     * These dummy nodes sit just "outside" of our linked list. What is the purpose? We never want head or tail to be null.
     *
     * @param capacity
     */


    int totalCapacity;
    int currentSize;
    LRUCacheNode head;
    LRUCacheNode tail;

    Map<Integer, LRUCacheNode> keyToNodeMap = new HashMap<>();

    public LRUCache(int capacity) {
        totalCapacity = capacity;
        currentSize = 0;
        head = new LRUCacheNode(0, 0);
        tail = new LRUCacheNode(0, 0);
        head.next = tail;
        tail.previous = head;
    }

    /**
     * Returns the value of the node
     * Moves the node to most frequently used position
     */
    public int get(int key) {

        if (!keyToNodeMap.containsKey(key) || currentSize == 0) {
            return -1;
        }

        LRUCacheNode currNode = keyToNodeMap.get(key);

        //remove node from its existing position
        removeFromExistingPosition(currNode);

        //add the node to the tail side since its accessed
        add(key, currNode.value);

        return currNode.value;
    }

    /**
     * adds a node to the tail of the linked list
     */
    public void put(int key, int value) {

        if (keyToNodeMap.containsKey(key)) {
            //same key existing
            removeFromExistingPosition(keyToNodeMap.get(key));
        } else if (currentSize == totalCapacity) {
            //remove one element
            removeFromHead();
        }

        add(key, value);
    }

    /**
     * adds a new node at the tail of the linked list
     */
    public LRUCacheNode add(int key, int value) {
        LRUCacheNode newNode;

        LRUCacheNode previousLastNode = tail.previous;
        newNode = new LRUCacheNode(key, value);
        newNode.next = tail;
        newNode.previous = previousLastNode;
        tail.previous = newNode;
        previousLastNode.next = newNode;

        //increment size
        currentSize++;

        // update map with new node reference
        keyToNodeMap.put(key, newNode);
        return newNode;
    }

    /**
     * perform removals when we update/fetch an existing key from the current position
     *
     * @return the node with key and value
     */
    public LRUCacheNode removeFromExistingPosition(LRUCacheNode currNode) {

        if (currentSize == 0) {
            return null;
        }

        LRUCacheNode prevNode = currNode.previous;
        LRUCacheNode nextNode = currNode.next;

        prevNode.next = nextNode;
        nextNode.previous = prevNode;

        // clear references
        currNode.next = null;
        currNode.previous = null;

        //remove node from map
        keyToNodeMap.remove(currNode.key);
        currentSize--;

        return currNode;

    }

    /**
     * perform removals when capacity os reached
     *
     * @return the node with key and value
     */
    public LRUCacheNode removeFromHead() {

        //remove when the data structure exceeds capacity

        if (currentSize == 0) {
            return null;
        }

        LRUCacheNode currentFirstNode = head.next;
        LRUCacheNode currentSecondNode = currentFirstNode.next;

        head.next = currentSecondNode;
        currentSecondNode.previous = head;

        //decreemnt size
        currentSize--;

        // remove node from map
        keyToNodeMap.remove(currentFirstNode.key);

        // clear references
        currentFirstNode.previous = null;
        currentFirstNode.next = null;

        return currentFirstNode;

    }

    public static void main(String[] args) {
        System.out.println("--- LRU Cache Test Execution ---");
        LRUCache lru = new LRUCache(2);

        // 1. Put (1,1)
        lru.put(1, 1);
        printStep("put(1, 1)", "null", lru);

        // 2. Put (2,2)
        lru.put(2, 2);
        printStep("put(2, 2)", "null", lru);

        // 3. Get (1)
        int res1 = lru.get(1);
        printStep("get(1)", String.valueOf(res1), lru);

        // 4. Put (3,3) - Should evict 2
        lru.put(3, 3);
        printStep("put(3, 3)", "null (evicts 2)", lru);

        // 5. Get (2)
        int res2 = lru.get(2);
        printStep("get(2)", String.valueOf(res2), lru);

        // 6. Put (4,4) - Should evict 1
        lru.put(4, 4);
        printStep("put(4, 4)", "null (evicts 1)", lru);

        // 7. Get (1)
        int res3 = lru.get(1);
        printStep("get(1)", String.valueOf(res3), lru);

        // 8. Get (3)
        int res4 = lru.get(3);
        printStep("get(3)", String.valueOf(res4), lru);

        // 9. Get (4)
        int res5 = lru.get(4);
        printStep("get(4)", String.valueOf(res5), lru);

        // 10. Update existing key (3) with a new value
        lru.put(3, 30);
        printStep("put(3, 30)", "null (update)", lru);

        // 11. Add brand new element (5) - should evict key 4
        lru.put(5, 5);
        printStep("put(5, 5)", "null (evict 4)", lru);

        // 12. Final check on key 3
        int res6 = lru.get(3);
        printStep("get(3)", String.valueOf(res6), lru);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--- Test Finished ---");
    }

    /**
     * Pretty prints the operation, the result, and the current order of the Doubly LL
     */
    private static void printStep(String operation, String result, LRUCache cache) {
        StringBuilder listOrder = new StringBuilder();
        LRUCacheNode curr = cache.head.next;
        while (curr != cache.tail) {
            listOrder.append("[").append(curr.key).append(":").append(curr.value).append("]");
            if (curr.next != cache.tail) listOrder.append(" -> ");
            curr = curr.next;
        }

        System.out.printf("%-18s | Result: %-15s | Cache Order (LRU -> MRU): %s%n",
                operation, result, listOrder.toString());
    }

}

class LRUCacheNode {
    int key;
    int value;
    LRUCacheNode previous;
    LRUCacheNode next;

    public LRUCacheNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public LRUCacheNode(int key, int value, LRUCacheNode previous, LRUCacheNode next) {
        this.key = key;
        this.value = value;
        this.previous = previous;
        this.next = next;
    }
}
