package com.sohu.example1.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Getter
@Setter
class OrderCreateEvent extends ApplicationEvent {
    private Long orderId;

    public OrderCreateEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }
}

class SendEmailOnOrderCreateListener implements ApplicationListener<OrderCreateEvent> {
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        Object source = event.getSource();
        System.out.println(String.format("%s  %s创建完成，请发邮件", (String)source,event.getOrderId()));
    }
}

public class Demo2 {
    public static void main(String[] args) {
        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.addApplicationListener(new SendEmailOnOrderCreateListener());
        multicaster.multicastEvent(new OrderCreateEvent("订单", 135413L));
    }
}
