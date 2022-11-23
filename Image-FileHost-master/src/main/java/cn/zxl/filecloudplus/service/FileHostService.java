package cn.zxl.filecloudplus.service;

import cn.zxl.filecloudplus.entity.FileHost;
import cn.zxl.filecloudplus.entity.ImageHost;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
public interface FileHostService extends IService<FileHost> {
    int getPages(String file_type);
    int getPages(String file_type,String file_type_detail);
    List<FileHost> FindFiles(int page, String file_type);
    List<FileHost> FindFiles(int page,String file_type,String file_type_detail);

}
