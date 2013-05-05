package com.mmtzj.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-3-11
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public class DisruptorTest {

    final EventHandler<ValueEvent> handler = new EventHandler<ValueEvent>()
    {
        public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception
        {
            // process a new event.
        }
    };

    private static final int BUFFER_SIZE = 1024 * 64;
    private static final long ITERATIONS = 1000L * 1000L * 200L;

    private final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(DaemonThreadFactory.INSTANCE);

    private final Sequencer sequencer = new SingleProducerSequencer(BUFFER_SIZE, new YieldingWaitStrategy());
    private final MyRunnable myRunnable = new MyRunnable(sequencer);
    {
        sequencer.addGatingSequences(myRunnable.sequence);
    }

    private static class MyRunnable implements Runnable
    {
        private CountDownLatch latch;
        private long expectedCount;
        Sequence sequence = new Sequence(-1);
        private SequenceBarrier barrier;

        public MyRunnable(Sequencer sequencer)
        {
            this.barrier = sequencer.newBarrier();
        }

        public void reset(CountDownLatch latch, long expectedCount)
        {
            this.latch = latch;
            this.expectedCount = expectedCount;
        }

        @Override
        public void run()
        {
            long expected = expectedCount;
            long processed = -1;

            try
            {
                do
                {
                    processed = barrier.waitFor(sequence.get() + 1);
                    sequence.set(processed);
                }
                while (processed < expected);

                latch.countDown();
                sequence.setVolatile(processed);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test(){
    }
}
