package com.bugly.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author no_f
 * @create 2020-06-18 10:38
 */
@Data
@Accessors(chain = true)
public class UpdateAlarmConfigDto implements Serializable {

    private static final long serialVersionUID = 1391340588728829310L;

    @NotNull
    private String id;

    @NotNull
    private String nickname;

    private String webhookUrl;


}
