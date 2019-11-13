package org.abigballofmud.stream.source;

import org.abigballmud.stream.binder.JobSourceBinder;
import org.abigballmud.stream.entity.JobEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/08 0:37
 * @since 1.0
 */
@EnableBinding(JobSourceBinder.class)
@RestController
public class SourceDemo {

    private final JobSourceBinder jobSourceBinder;

    public SourceDemo(JobSourceBinder jobSourceBinder) {
        this.jobSourceBinder = jobSourceBinder;
    }

    @PostMapping
    public String sendMessage(@RequestBody JobEvent incoming) {
        JobEvent jobEvent = JobEvent.builder().name(incoming.getName()).build();
        // jobSourceBinder.output()
        //         .send(MessageBuilder.withPayload(jobEvent).setHeader("jobType", "datax").build());
        jobSourceBinder.output()
                .send(MessageBuilder.withPayload(jobEvent).setHeader("jobType", "sqoop").build());
        return "job sent";
    }


}
