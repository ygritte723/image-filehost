package cn.zxl.filecloudplus.service;

import cn.zxl.filecloudplus.entity.FileType;
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
public interface FileTypeService extends IService<FileType> {
    List <String> getDetailByType(String type);
    List <String> getType();
}
