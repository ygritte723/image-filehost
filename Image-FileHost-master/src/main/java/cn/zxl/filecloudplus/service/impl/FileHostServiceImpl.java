package cn.zxl.filecloudplus.service.impl;

import cn.zxl.filecloudplus.entity.FileHost;
import cn.zxl.filecloudplus.dao.FileHostMapper;
import cn.zxl.filecloudplus.service.FileHostService;
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
public class FileHostServiceImpl extends ServiceImpl<FileHostMapper, FileHost> implements FileHostService {
    @Autowired
    @Resource
    FileHostMapper mapper;

    // 一页展示几个文件
    private final int LIMIT = 8;

    @Override
    public int getPages(String file_type) {
        int count = mapper.selectAllByType(file_type).size();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }

    @Override
    public int getPages(String file_type, String file_type_detail) {
        int count = mapper.selectAllByDetail(file_type_detail).size();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }

    @Override
    public List<FileHost> FindFiles(int page, String file_type) {
        int index = (page - 1) * LIMIT;
        return mapper.selectFileByType(index,LIMIT,file_type);
    }

    @Override
    public List<FileHost> FindFiles(int page, String file_type, String file_type_detail) {
        int index = (page - 1) * LIMIT;
        return mapper.selectFileByTypeDetail(index,LIMIT,file_type_detail);
    }
}
