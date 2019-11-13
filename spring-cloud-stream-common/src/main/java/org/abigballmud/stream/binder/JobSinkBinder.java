package org.abigballmud.stream.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/12 11:50
 * @since 1.0
 */
public interface JobSinkBinder {

    String INPUT = JobProcessorBinder.OUTPUT;

    /**
     * input channel
     *
     * @return input channel
     */
    @Input(INPUT)
    SubscribableChannel input();
}
