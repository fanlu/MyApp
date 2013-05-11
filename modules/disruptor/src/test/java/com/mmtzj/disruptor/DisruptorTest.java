package com.mmtzj.disruptor;

import com.jdon.annotation.pointcut.Before;
import com.jdon.async.disruptor.DisruptorFactory;
import com.jdon.async.disruptor.EventDisruptor;
import com.jdon.domain.message.DomainEventHandler;
import com.jdon.domain.message.DomainMessage;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.Test;

import java.util.TreeSet;
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

    DisruptorFactory disruptorFactory;

    @Before
    public void before(){
        disruptorFactory = new DisruptorFactory();
    }

    @Test
    public void test(){
        disruptorFactory = new DisruptorFactory();
        TreeSet<DomainEventHandler> handlers = disruptorFactory.getTreeSet();
        final DomainEventHandler<EventDisruptor> handler = new DomainEventHandler<EventDisruptor>() {

            @Override
            public void onEvent(EventDisruptor event, final boolean endOfBatch) throws Exception {
                System.out.println("MyEventA=" + event.getDomainMessage().getEventSource());
                event.getDomainMessage().setEventResult("not null");

            }
        };

        final DomainEventHandler<EventDisruptor> handler2 = new DomainEventHandler<EventDisruptor>() {

            @Override
            public void onEvent(EventDisruptor event, final boolean endOfBatch) throws Exception {
                System.out.println("MyEventA=" + event.getDomainMessage().getEventSource());
                event.getDomainMessage().setEventResult(null);

            }
        };

        handlers.add(handler2);
        handlers.add(handler);

        Disruptor disruptor = disruptorFactory.addEventMessageHandler("test", handlers);
        disruptor.start();

        int i = 0;

        // while (i < 10) {
        RingBuffer ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();

        DomainMessage domainMessage = new DomainMessage(sequence);

        EventDisruptor eventDisruptor = (EventDisruptor) ringBuffer.get(sequence);
        eventDisruptor.setTopic("test");
        eventDisruptor.setDomainMessage(domainMessage);

        ringBuffer.publish(sequence);
        System.out.print("\n RESULT=" + domainMessage.getBlockEventResult());

        System.out.print("\n RESULT=" + domainMessage.getBlockEventResult());

        System.out.print("\n RESULT=" + domainMessage.getBlockEventResult());

        i++;
        System.out.print(i);

        // }

        System.out.print("ok");
    }
}
