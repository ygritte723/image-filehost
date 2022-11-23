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
    public class ImageHost implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private String imageType;

    private String imageName;

    private Integer downloadCount;

    private String path;

    private String thumbnailPath;

    private Double fileSize;

    private LocalDateTime createDate;

    private String updateDate;

    private String uploadUser;

    private Integer deleted;


}
