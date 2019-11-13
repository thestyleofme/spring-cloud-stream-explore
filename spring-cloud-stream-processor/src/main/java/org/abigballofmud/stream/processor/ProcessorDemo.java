package org.abigballofmud.stream.processor;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;
import org.abigballmud.stream.binder.JobProcessorBinder;
import org.abigballmud.stream.entity.Job;
import org.abigballmud.stream.entity.JobEvent;
import org.abigballmud.stream.exception.CommonException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
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
    @StreamListener(value = JobProcessorBinder.INPUT)
    @SendTo(JobProcessorBinder.OUTPUT)
    public JobEvent handle(JobEvent data, @Header("jobType") String jobType) {
        log.debug("Processor Received: {}, jobType: {}", data, jobType);
        Job job = Job.builder().name(data.getName()).build();
        // 模拟消息处理出错
        if (shouldFail.get()) {
            shouldFail.set(false);
            throw new CommonException("Simulated network error");
        } else {
            // We fail every other request as a test
            shouldFail.set(true);
        }
        // 插表
        log.info("=====================");
        log.info("Write job table,job: {}", job);
        JobEvent event = JobEvent.builder().name(data.getName()).build();
        log.info("=====================");
        return event;
    }

    /**
     * 消息消费失败的降级处理逻辑
     *
     * @param message Message
     */
    @ServiceActivator(inputChannel = JobProcessorBinder.INPUT)
    public void error(Message<?> message) {
        log.info("Message [{}] consumer failed, call fallback!", message);
    }

}
