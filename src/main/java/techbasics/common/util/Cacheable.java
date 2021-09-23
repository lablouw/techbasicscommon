package techbasics.common.util;

public abstract class Cacheable<T> {

	private T t;

	public T get() {
		return t;
	}

	public synchronized void refresh() {
		if (!isStale()) {
			return;
		}

		refreshInternal();
	}

	protected abstract void refreshInternal();
	public abstract boolean isStale();
}
