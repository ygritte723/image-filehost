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
    public class UserAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String userEmail;

    private String username;

    private String avatarPath;

    private String password;

    private Integer userRole;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
