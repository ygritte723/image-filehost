package cn.zxl.filecloudplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userIp;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    private String targetClassify;

    private String targetId;


}
