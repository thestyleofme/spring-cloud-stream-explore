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
 * @author isacc 2019/11/12 13:48
 * @since 1.0
 */
@Slf4j
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class JobEvent {

    private String name;
    private String type;
}
