package org.abigballmud.stream.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/12 11:50
 * @since 1.0
 */
public interface JobProcessorBinder {

    String OUTPUT = "job-processor-output";
    String INPUT = JobSourceBinder.OUTPUT;

    /**
     * output channel
     *
     * @return output channel
     */
    @Output(OUTPUT)
    MessageChannel output();

    /**
     * input channel
     *
     * @return input channel
     */
    @Input(INPUT)
    SubscribableChannel input();
}
