package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock {
    private final Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {

        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int releases) {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }


        public boolean isLocked() {
            return getState() != 0;
        }

    }

    public void lock()              { sync.acquire(1); }
    public boolean tryLock()        { return sync.tryAcquire(1); }
    public void unlock()            { sync.release(1); }
    public boolean isLocked()       { return sync.isLocked(); }
    public boolean isHeldByCurrentThread() {
        return sync.isHeldExclusively();
    }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
