package org.abigballmud.stream.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/12 11:50
 * @since 1.0
 */
public interface JobSourceBinder {

    String OUTPUT = "job-supplier-output";

    /**
     * output channel
     *
     * @return output channel
     */
    @Output(OUTPUT)
    MessageChannel output();

}
