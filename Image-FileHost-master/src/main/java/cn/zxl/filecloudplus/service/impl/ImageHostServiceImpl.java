package cn.zxl.filecloudplus.service.impl;

import cn.zxl.filecloudplus.entity.ImageHost;
import cn.zxl.filecloudplus.dao.ImageHostMapper;
import cn.zxl.filecloudplus.service.ImageHostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Service
public class ImageHostServiceImpl extends ServiceImpl<ImageHostMapper, ImageHost> implements ImageHostService {
    @Autowired
    @Resource
    ImageHostMapper mapper;
    // 一页几张图
    private final int LIMIT = 8;
    @Override
    public int getPages() {
        int count = mapper.selectAll().size();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }

    @Override
    public int getPages(String image_type) {
        int count = mapper.selectAllByType(image_type).size();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }

    @Override
    public List<ImageHost> FindImages(int page) {
        int index = (page - 1) * LIMIT;
        return mapper.selectImage(index,LIMIT);
    }

    @Override
    public List<ImageHost> FindImages(int page, String image_type) {
        int index = (page - 1) * LIMIT;
        return mapper.selectImageByType(index,LIMIT,image_type);
    }
}
