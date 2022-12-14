package cn.zxl.filecloudplus.entity;

import com.baomidou.mybatisplus.annotation.*;

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
public class ImageHost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    private String imageType;

    private String imageName;

    private Integer downloadCount;

    private String path;

    private String thumbnailPath;

    private double fileSize;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    private String uploadUser;

    @TableLogic
    private Integer deleted;


}
