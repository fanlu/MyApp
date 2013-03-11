package com.mmtzj.disruptor;

import com.lmax.disruptor.*;
import org.junit.Test;

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

//    RingBuffer<ValueEvent> ringBuffer =
//            new RingBuffer<ValueEvent>(ValueEvent.EVENT_FACTORY,
//                    new SingleThreadedClaimStrategy(RING_SIZE),
//                    new SleepingWaitStrategy());
//
//    SequenceBarrier barrier = ringBuffer.newBarrier();
//    BatchEventProcessor<ValueEvent> eventProcessor = new BatchEventProcessor<ValueEvent>(ringBuffer, barrier, handler);
//    {
//        ringBuffer.addGatingSequences(eventProcessor.getSequence());
//    }
//// Each EventProcessor can run on a separate thread
//    EXECUTOR.submit(eventProcessor);

    @Test
    public void test(){
    }
}
