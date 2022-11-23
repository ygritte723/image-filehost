package cn.zxl.filecloudplus.service.impl;

import cn.zxl.filecloudplus.entity.FileType;
import cn.zxl.filecloudplus.dao.FileTypeMapper;
import cn.zxl.filecloudplus.service.FileTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class FileTypeServiceImpl extends ServiceImpl<FileTypeMapper, FileType> implements FileTypeService {
    @Autowired
    @Resource
    FileTypeMapper mapper;

    @Override
    public List<String> getDetailByType(String type) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("file_type",type);
        List <String> list=new ArrayList<>();
        List <FileType> fileTypes= mapper.selectList(wrapper);
        for (FileType fileType:fileTypes) {
            list.add(fileType.getFileTypeDetail());
        }

        return list;
    }

    @Override
    public List<String> getType() {
        List<FileType> fileTypes = mapper.selectAllFileType();
        List<String> list = new ArrayList<>();
        for (FileType filetype: fileTypes) {
            list.add(filetype.getFileType());
        }
        return list;
    }
}
