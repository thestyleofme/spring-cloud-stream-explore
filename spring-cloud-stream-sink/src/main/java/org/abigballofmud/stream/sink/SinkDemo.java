package org.abigballofmud.stream.sink;

import lombok.extern.slf4j.Slf4j;
import org.abigballmud.stream.binder.JobSinkBinder;
import org.abigballmud.stream.entity.JobEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/08 0:05
 * @since 1.0
 */
@EnableBinding(JobSinkBinder.class)
@Slf4j
public class SinkDemo {

    @StreamListener(JobSinkBinder.INPUT)
    public void handle(JobEvent data) {
        log.debug("Sink Received: {}", data);
    }

}
