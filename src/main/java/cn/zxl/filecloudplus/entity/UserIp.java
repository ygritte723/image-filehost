package cn.zxl.filecloudplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 3190105744
 * @since 2021-12-29
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UserIp implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String userIp;

    private LocalDateTime createDate;

    private String targetClassify;

    private Long targetId;


}
