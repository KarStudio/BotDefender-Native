//package com.ghostchu.botdefender.sysio;
//
//import com.ghostchu.botdefender.sysio.ipset.IPSetUtil;
//import com.ghostchu.botdefender.sysio.util.TimeUtil;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Arrays;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.DelayQueue;
//import java.util.concurrent.Delayed;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//@Slf4j
//public class BlockManagerLegacy {
//    private final Lock LOCK = new ReentrantLock();
//    private final DelayQueue<BlockEntry> queue = new DelayQueue<>();
//    private final Timer unblockTimer = new Timer("BlockManager", true);
//    private final IPSetUtil ipSetUtil = new IPSetUtil();
//
//    public BlockManagerLegacy(){
//        unblockTimer.schedule(new TimerTask(){
//
//            /**
//             * The action to be performed by this timer task.
//             */
//            @Override
//            public void run() {
//                BlockEntry entry;
//                while ((entry = queue.poll()) != null) {
//                    if(unBlockIp(entry.getIpAddress())) {
//                        log.info("Unblocked expired blocked ip: {}.", entry.getIpAddress());
//                    }
//                }
//            }
//        }, 0, 1000);
//    }
//
//    public boolean blockIp(@NotNull String ip, long endTime) {
//        LOCK.lock();
//        if (Arrays.stream(queue.toArray(new BlockEntry[0])).anyMatch(entry -> entry.getIpAddress().equals(ip))) {
//            LOCK.unlock();
//            return false;
//        }
//        log.info("Blocking ip {}, Duration: {}", ip, TimeUtil.convert(endTime - System.currentTimeMillis()));
//        queue.offer(new BlockEntry(endTime, ip));
//        ipSetUtil.add(ip);
//        LOCK.unlock();
//        return true;
//    }
//
//    public boolean unBlockIp(@NotNull String ip) {
//        LOCK.lock();
//        boolean result = queue.removeIf(blockEntry -> blockEntry.getIpAddress().equals(ip));
//        LOCK.unlock();
//        if(result){
//            log.info("Unblocking ip {}", ip);
//            ipSetUtil.remove(ip);
//        }
//        return result;
//    }
//
//
//
//    @AllArgsConstructor
//    static class BlockEntry implements Delayed {
//        @Getter
//        private final long endTime;
//        @Getter
//        private String ipAddress;
//
//
//        /**
//         * Returns the remaining delay associated with this object, in the
//         * given time unit.
//         *
//         * @param unit the time unit
//         * @return the remaining delay; zero or negative values indicate
//         * that the delay has already elapsed
//         */
//        @Override
//        public long getDelay(@NotNull TimeUnit unit) {
//            return unit.convert(endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
//        }
//
//        /**
//         * Compares this object with the specified object for order.  Returns a
//         * negative integer, zero, or a positive integer as this object is less
//         * than, equal to, or greater than the specified object.
//         *
//         * <p>The implementor must ensure {@link Integer#signum
//         * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
//         * all {@code x} and {@code y}.  (This implies that {@code
//         * x.compareTo(y)} must throw an exception if and only if {@code
//         * y.compareTo(x)} throws an exception.)
//         *
//         * <p>The implementor must also ensure that the relation is transitive:
//         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
//         * {@code x.compareTo(z) > 0}.
//         *
//         * <p>Finally, the implementor must ensure that {@code
//         * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
//         * == signum(y.compareTo(z))}, for all {@code z}.
//         *
//         * @param o the object to be compared.
//         * @return a negative integer, zero, or a positive integer as this object
//         * is less than, equal to, or greater than the specified object.
//         * @throws NullPointerException if the specified object is null
//         * @throws ClassCastException   if the specified object's type prevents it
//         *                              from being compared to this object.
//         * @apiNote It is strongly recommended, but <i>not</i> strictly required that
//         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
//         * class that implements the {@code Comparable} interface and violates
//         * this condition should clearly indicate this fact.  The recommended
//         * language is "Note: this class has a natural ordering that is
//         * inconsistent with equals."
//         */
//        @Override
//        public int compareTo(@NotNull Delayed o) {
//            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
//        }
//    }
//}
