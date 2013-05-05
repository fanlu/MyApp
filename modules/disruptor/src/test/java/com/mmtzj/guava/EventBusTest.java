package com.mmtzj.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-5-5
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class EventBusTest {

    @Test
    public void shouldReceiveEvent() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        // when
        eventBus.post(new OurTestEvent(200));

        // then
        assertThat(listener.getLastMessage(), is(200));
    }

    @Test
    public void shouldReceiveMultipleEvents() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        MultipleListener multiListener = new MultipleListener();

        eventBus.register(multiListener);

        // when
        eventBus.post(new Integer(100));
        eventBus.post(new Long(800));

        // then
        assertThat(multiListener.getLastInteger(), is(100));
        assertThat(multiListener.getLastLong(), equalTo(800L));
    }

    @Test
    public void shouldDetectEventWithoutListeners() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");

        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        // when
        eventBus.post(new OurTestEvent(200));

        assertThat(deadEventListener.isNotDelivered(), equalTo(true));
    }

    @Test
    public void shouldGetEventsFromSubclass() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        IntegerListener integerListener = new IntegerListener();
        NumberListener numberListener = new NumberListener();
        eventBus.register(integerListener);
        eventBus.register(numberListener);

        // when
        eventBus.post(new Integer(100));

        // then
        assertThat(integerListener.getLastMessage(), equalTo(100));
        assertThat(numberListener.getLastMessage(), equalTo((Number) 100));

        //when
        eventBus.post(new Long(200L));

        // then
        // this one should has the old value as it listens only for Integers
        assertThat(integerListener.getLastMessage(), equalTo(100));
        assertThat(numberListener.getLastMessage(), equalTo((Number) 200L));
    }

    @Test
    public void shouldExtendReceiveEvent() throws Exception {

        // given
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        EventListener listener1 = new EventListener();
        eventBus.register(listener);
        eventBus.register(listener1);

        // when
        eventBus.post(new OurTestExtendEvent(200));
        // then
        assertThat(listener.getLastMessage(), is(201));
        assertThat(listener1.getLastMessage(), is(201));

        eventBus.post(new OurTestEvent(200));
        // then
        assertThat(listener.getLastMessage(), is(200));
        assertThat(listener1.getLastMessage(), is(200));
    }

    @Test
    public void testAsyncEventBus(){
        AsyncEventBus bus = new AsyncEventBus(Executors.newSingleThreadExecutor());
        EventListener listener = new EventListener();
        bus.register(listener);

        bus.post(new OurTestEvent(200));

        System.out.println("222");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(listener.getLastMessage());
    }

}
