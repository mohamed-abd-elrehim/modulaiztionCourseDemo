package com.example.core.domain

/**
 * A simple implementation of a Queue in Kotlin, similar to Java's java.util.Queue.
 *
 * @param T The type of elements stored in the queue.
 * @property items The list representing the queue.
 */

class Queue<T>(list: MutableList<T>) {

    var items: MutableList<T> = list

    /**
     * Checks if the queue is empty.
     * @return `true` if empty, `false` otherwise.
     */
    fun isEmpty(): Boolean = items.isEmpty()

    fun isNotEmpty(): Boolean = items.isNotEmpty()

    /**
     * Returns the number of elements in the queue.
     * @return The size of the queue.
     */
    fun count(): Int = items.count()

    /**
     * Returns a string representation of the queue.
     */
    override fun toString() = items.toString()

    /**
     * Adds an element to the end of the queue.
     * @param element The element to be added.
     */
    fun add(element: T) {
        items.add(element)
    }

    /**
     * Removes and returns the first element of the queue.
     * @throws Exception If the queue is empty.
     * @return The removed element.
     */
    @Throws(Exception::class)
    fun remove(): T {
        if (this.isEmpty()) {
            throw Exception("fun 'remove' threw an exception: Nothing to remove from the queue.")
        }
        return items.removeAt(0)
    }

    /**
     * Removes the first occurrence of a specific item from the queue.
     * @param item The item to remove.
     * @return `true` if the item was removed, `false` otherwise.
     */
    fun remove(item: T): Boolean {
        return items.remove(item)
    }

    /**
     * Retrieves, but does not remove, the first element of the queue.
     * @throws Exception If the queue is empty.
     * @return The first element of the queue.
     */
    @Throws(Exception::class)
    fun element(): T {
        if (this.isEmpty()) {
            throw Exception("fun 'element' threw an exception: Nothing in the queue.")
        }
        return items[0]
    }

    /**
     * Attempts to add an element to the queue.
     * @param element The element to add.
     * @return `true` if the element was successfully added, `false` otherwise.
     */
    fun offer(element: T): Boolean {
        return try {
            items.add(element)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Retrieves and removes the first element of the queue, or returns `null` if the queue is empty.
     * @return The first element or `null` if empty.
     */
    fun poll(): T? {
        return if (this.isEmpty()) null else items.removeAt(0)
    }

    /**
     * Retrieves, but does not remove, the first element of the queue, or returns `null` if the queue is empty.
     * @return The first element or `null` if empty.
     */
    fun peek(): T? {
        return if (this.isEmpty()) null else items[0]
    }

    /**
     * Adds all elements from another queue to this queue.
     * @param queue The queue whose elements will be added.
     */
    fun addAll(queue: Queue<T>) {
        this.items.addAll(queue.items)
    }

    /**
     * Clears all elements from the queue.
     */
    fun clear() {
        items.clear()
    }
}
