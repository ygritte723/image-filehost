package cn.zxl.filecloudplus.service;

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
public interface ImageHostService extends IService<ImageHost> {
    int getPages();
    int getPages(String image_type);
    List<ImageHost> FindImages(int page);
    List<ImageHost> FindImages(int page,String image_type);
}
