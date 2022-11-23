package cn.zxl.filecloudplus.dao;

import cn.zxl.filecloudplus.entity.FileType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
public interface FileTypeMapper extends BaseMapper<FileType> {
    List <FileType> selectAllFileType();

}
