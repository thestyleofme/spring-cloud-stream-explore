package org.abigballmud.stream.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * description
 * </p>
 *
 * @author isacc 2019/11/08 0:06
 * @since 1.0
 */
@Slf4j
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Job {

    private String name;

}
