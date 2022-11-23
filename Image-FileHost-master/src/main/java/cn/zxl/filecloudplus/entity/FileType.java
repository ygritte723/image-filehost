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
public class FileType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String fileType;

    private String fileTypeDetail;


}
