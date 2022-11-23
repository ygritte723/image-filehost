package cn.zxl.filecloudplus.entity;

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
    public class FileType implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String fileType;

    private String fileTypeDetail;


}
