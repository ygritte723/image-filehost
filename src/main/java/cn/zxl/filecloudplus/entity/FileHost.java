package cn.zxl.filecloudplus.entity;

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
    public class FileHost implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String fileType;

    private String fileTypeDetail;

    private String fileName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer downloadCount;

    private String path;

    private Double fileSize;

    private String uploadUser;

    private Integer deleted;


}
