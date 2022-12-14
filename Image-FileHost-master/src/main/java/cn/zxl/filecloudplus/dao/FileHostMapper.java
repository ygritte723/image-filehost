package cn.zxl.filecloudplus.dao;

import cn.zxl.filecloudplus.entity.FileHost;
import cn.zxl.filecloudplus.entity.ImageHost;
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
public interface FileHostMapper extends BaseMapper<FileHost> {
    List<FileHost> selectAll();
    List<FileHost> selectAllByType(String file_type);
    List<FileHost> selectAllByDetail(String file_type_detail);
    List<FileHost> selectAllByTypeDetail(String file_type,String file_type_detail);
    List<FileHost> selectFile(int index,int limit);
    List<FileHost> selectFileByType(int index,int limit,String file_type);
    List<FileHost> selectFileByTypeDetail(int index,int limit,String file_type_detail);


}
