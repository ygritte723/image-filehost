package cn.zxl.filecloudplus.dao;

import cn.zxl.filecloudplus.entity.ImageHost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Mapper
public interface ImageHostMapper extends BaseMapper<ImageHost> {
    List<ImageHost> selectAll();
    List<ImageHost> selectAllByType(String image_type);
    List<ImageHost> selectImage(int index,int limit);
    List<ImageHost> selectImageByType(int index,int limit,String image_type);

}
