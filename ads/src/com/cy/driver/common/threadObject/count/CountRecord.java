package com.cy.driver.common.threadObject.count;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by haoy on 2014/10/8.
 */
public interface CountRecord {
    ConcurrentLinkedQueue<Count> counts = new ConcurrentLinkedQueue<Count>();

    public void addCountToQueue(Count count);
}
