import java.util.Iterator;

public class SkipListIterator<K extends Comparable<K>,V> implements Iterator<SkipList<K, V>.Node<K, V>>{
	
	//SkipList<K, V> skipList;
	SkipList<K, V>.Node<K, V> iter;
	
	public SkipListIterator(SkipList<K,V>.Node<K, V> iter)
	{
		this.iter = iter;
		while (this.iter.down != null) {
			this.iter = this.iter.down;
		}
	}

	@Override
	public boolean hasNext() {
		if(iter.next != null) return true;
		return false;
	}

	@Override
	public SkipList<K,V>.Node<K, V> next() {
		if(hasNext()){
			iter = iter.next;
			return iter;
		}
		else
			return null;
	}
	
	@Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported in skipList");
    }

}
