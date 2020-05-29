package queues;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue<T> {

	// Make some variables

	private Stack<T> stackNew;
	private Stack<T> stackOld;
	private int count = 0;

	public QueueFromStacks() {
		this.stackNew = new Stack<T>();
		this.stackOld = new Stack<T>();
		this.count = 0;
	}

	@Override
	public void clear() {
		this.stackNew = new Stack<T>();
		this.stackOld = new Stack<T>();
		this.count = 0;
	}

	@Override
	public void enqueue(T item) {
		this.stackNew.push(item);
		this.count++;

	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (this.stackNew.isEmpty() && this.stackOld.isEmpty()) {
			throw new NoSuchElementException();
		}
		if (this.stackOld.isEmpty()) {
			switchStack();
		}

		this.count--;
		return this.stackOld.pop();
	}

	public void switchStack() {
		while (!stackNew.isEmpty()) {
			this.stackOld.push(stackNew.pop());
		}

	}

	public String toString() {
		if (this.stackNew.isEmpty() && this.stackOld.isEmpty()) {
			return "[]";
		}

		String s = "[";
		int p = 0;
		int u = 0;
		int si = this.stackOld.size();
		int g = 0;
		int o = 0;
		if (this.stackNew.isEmpty()) {
			while (!this.stackOld.isEmpty()) {
				if (si - g == 1) {
					T thing9 = this.stackOld.pop();
					s += thing9 + "]";
					this.stackNew.push(thing9);
					g++;
				} else {
					T thing2 = this.stackOld.pop();
					s += thing2 + ", ";
					this.stackNew.push(thing2);
					g++;
				}
			}
			while (o != g) {
				T thing3 = this.stackNew.pop();
				this.stackOld.push(thing3);
				o++;
			}
			return s;
		}
		while (!this.stackOld.isEmpty()) {
			T thing2 = this.stackOld.pop();
			s += thing2 + ", ";
			this.stackNew.push(thing2);
			p++;
		}
		while (u != p) {
			T thing3 = this.stackNew.pop();
			this.stackOld.push(thing3);
			u++;
		}
		int a = 0;
		int b = 0;
		while (!this.stackNew.isEmpty()) {
			this.stackOld.push(this.stackNew.pop());
			a++;
		}
		while (a != b) {
			if (a - b == 1) {
				T thing5 = this.stackOld.pop();
				s += thing5 + "]";
				this.stackNew.push(thing5);
				b++;
			} else {
				T thing4 = this.stackOld.pop();
				s += thing4 + ", ";
				this.stackNew.push(thing4);
				b++;
			}
		}
		return s;

	}

	@Override
	public T peek() throws NoSuchElementException {
		if (this.stackNew.isEmpty() && this.stackOld.isEmpty()) {
			throw new NoSuchElementException();
		}
		if (this.stackOld.isEmpty()) {
			switchStack();
		}
		T temp = this.stackOld.pop();
		this.stackOld.push(temp);

		return temp;
	}

	@Override
	public boolean isEmpty() {
		if (this.stackNew.isEmpty() && this.stackOld.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.count;
	}

	@Override
	public boolean contains(T item) {
		int s = 0;
		while (!this.stackNew.isEmpty()) {
			T thing = stackNew.pop();
			if (thing == item) {
				s++;
				this.stackOld.push(thing);
				return true;
			}
			s++;
			this.stackOld.push(thing);
		}
		int k = 0;
		while (k != s) {
			this.stackNew.push(this.stackOld.pop());
			k++;
		}
		int o = 0;
		while (!this.stackOld.isEmpty()) {
			T thing1 = stackOld.pop();
			if (thing1 == item) {
				o++;
				this.stackNew.push(thing1);
				return true;
			}
			o++;
			this.stackNew.push(thing1);
		}
		int l = 0;
		while (l != o) {
			this.stackOld.push(this.stackOld.pop());
			l++;
		}
		return false;

	}

	@Override
	public String debugString() {
		return null;
	}

}
