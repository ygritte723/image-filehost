package cn.zxl.filecloudplus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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
    public class ImageType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "image_type",type = IdType.INPUT)
    private String imageType;


}
