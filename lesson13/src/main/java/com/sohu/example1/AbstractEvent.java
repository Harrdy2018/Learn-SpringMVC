package com.sohu.example1;

import lombok.Getter;
import lombok.Setter;

/**
 * 事件对象
 */
@Getter
@Setter
public abstract class AbstractEvent {
    // 事件源
    protected Object source;

    public AbstractEvent(Object source) {
        this.source = source;
    }
}
