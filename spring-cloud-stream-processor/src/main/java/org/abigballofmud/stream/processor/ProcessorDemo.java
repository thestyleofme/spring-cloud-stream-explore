package org.abigballofmud.stream.processor;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;
import org.abigballmud.stream.entity.Job;
import org.abigballmud.stream.entity.JobEvent;
import org.abigballmud.stream.binder.JobProcessorBinder;
import org.abigballmud.stream.exception.CommonException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/08 0:28
 * @since 1.0
 */
@Slf4j
@EnableBinding(JobProcessorBinder.class)
// @EnableTransactionManagement
public class ProcessorDemo {

    private AtomicBoolean shouldFail = new AtomicBoolean(false);

    // @Transactional(rollbackFor = Exception.class)
    @StreamListener(JobProcessorBinder.INPUT)
    @SendTo(JobProcessorBinder.OUTPUT)
    public JobEvent handle(JobEvent data) {
        log.debug("Processor Received: {}", data);
        Job job = Job.builder().name(data.getName()).build();
        if (shouldFail.get()) {
            shouldFail.set(false);
            throw new CommonException("Simulated network error");
        } else {
            // We fail every other request as a test
            shouldFail.set(true);
        }
        log.info("Saving Job={}", job);
        JobEvent event = JobEvent.builder().name(data.getName()).build();
        // 插表
        event.setType("JobSaved");
        log.info("Sent event={}", event);
        return event;
    }

}
