import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

public class SkipList<K extends Comparable<K>, V> implements Iterable<SkipList<K,V>.Node<K,V>> {

	public int levels = 0;
	Node<K, V> head;

	public class Node<A, B> {
		Node<K, V> next;
		Node<K, V> prev;
		Node<K, V> up;
		Node<K, V> down;
		K key;
		V value;

		public Node(K k, V v) {
			key = k;
			value = v;
		}
		
		public String toString()
		{
			return key + "=" + value;
		}
	}

	public SkipList() {
		head = null;
	}

	public Node<K, V> searchIns(K k, int lev) {
		Node<K, V> tmp = head;
		int tmpLevel = levels;
		while (tmpLevel > lev) {
			while ((tmp.next != null) && ((tmp.next.key).compareTo(k) < 0)) {
				tmp = tmp.next;
			}
			tmp = tmp.down;
			tmpLevel--;
		}
		while ((tmp.next != null) && ((tmp.next.key).compareTo(k) < 0)) {
			tmp = tmp.next;
		}
		return tmp;
	}

	public V put(K k, V v) {
		Node<K, V> nowy = new Node<>(k, v);
		Node<K, V> upto = nowy;
		Random ran = new Random();
		if (levels == 0) {
			levels++;
			Node<K, V> sent = new Node<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
			sent.next = nowy;
			sent.prev = null;
			sent.up = null;
			sent.down = null;
			nowy.next = null;
			nowy.prev = sent;
			nowy.up = null;
			nowy.down = null;
			head = sent;
		} else {
			int tmpLev = 1;
			Node<K, V> ptr = searchIns(k, 1);
			if(ptr.next!=null && ptr.next.key == k)
				remove(k);
			nowy.next = ptr.next;
			nowy.prev = ptr;
			if (ptr.next != null)
				ptr.next.prev = nowy;
			ptr.next = nowy;
			nowy.down = null;
			while (ran.nextInt(2) > 0) {
				tmpLev++;
				Node<K, V> nowy2 = new Node<>(k, v);
				if (levels >= tmpLev) {
					nowy2.down = upto;
					nowy2.up = null;
					upto.up = nowy2;
					ptr = searchIns(k, tmpLev);
					nowy2.next = ptr.next;
					nowy2.prev = ptr;
					if (ptr.next != null)
						ptr.next.prev = nowy2;
					ptr.next = nowy2;
					upto = nowy2;
				} else {
					levels++;
					Node<K, V> sent = new Node<>(Integer.MIN_VALUE, Integer.MIN_VALUE);
					sent.next = null;
					sent.prev = null;
					sent.up = null;
					sent.down = head;
					head = sent;
					nowy2.next = null;
					nowy2.prev = sent;
					nowy2.up = null;
					nowy2.down = upto;
					upto = nowy2;

				}

			}

		}
		return v;
	}

	K lowerKey(K k) {
		Node<K, V> ptr = searchIns(k, 1);
		if (ptr.next.key != k) {
			System.out.println("Podany klucz nie wystepuje w strukturze");
			return null;
		} else
			return ptr.key;
	}

	K higherKey(K k) {

		try {
			Node<K, V> ptr = searchIns(k, 1);
			if ((ptr.next != null) && (ptr.next.next != null) && (ptr.next.key == k))
				return ptr.next.next.key;
			else
				throw new IndexOutOfBoundsException();
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Podanej wartosci nie ma w strukturze danych");
			return null;
		}
	}

	boolean containsKey(K k) {
		Node<K, V> ptr = searchIns(k, 1);
		if ((ptr.next != null) && (ptr.next.key == k))
			return true;
		else
			return false;
	}

	V get(Object k) {
		@SuppressWarnings("unchecked")
		Node<K, V> ptr = searchIns((K)k, 1);
		if (ptr.next != null && ptr.next.key == k) {
			return ptr.next.value;
		}
		return null;

	}

	K remove(K k) {
		K x;
		Node<K, V> ptr = searchIns(k, 1);
		ptr = ptr.next;
		if (ptr.key.equals(k)) {
			x = ptr.key;
			ptr.prev.next = ptr.next;
			if (ptr.next != null)
				ptr.next.prev = ptr.prev;
			Node<K, V> tmp = ptr.up;
			ptr.down = null;
			ptr.up = null;
			ptr = tmp;

			while (ptr != null) {
				ptr.prev.next = ptr.next;
				if (ptr.next != null)
					ptr.next.prev = ptr.prev;
				tmp = ptr.up;
				ptr.down = null;
				ptr.up = null;
				ptr = tmp;
			}
			relaxing();
			return x;
		}
		return null;

	}
	
	private void relaxing()
	{
		while(head.next==null && levels>1)
		{
			head=head.down;
			levels--;
		}
	}
	
	@Override
	public Iterator<Node<K,V>> iterator() {
		return new SkipListIterator<>(head);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Iterator<Node<K,V>> it = this.iterator();
		while(it.hasNext())
		{
			sb.append(it.next().toString() + " ");
		}
		return sb.toString() + "]";
	}

	public void print() {
		Node<K, V> tmp = head;
		while (tmp.down != null) {
			tmp = tmp.down;
		}
		tmp = tmp.next;
		while (tmp != null) {
			System.out.println(tmp.key);
			tmp = tmp.next;
		}
	}


}
