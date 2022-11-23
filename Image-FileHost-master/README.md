![](introduction.png)
![](https://img.shields.io/badge/Springboot-2.3.4-green)
![](https://img.shields.io/badge/Mybatis%20Plus-3.4.0-green)

# 项目概述

- 本项目基于`Springboot`、`Mybatis Plus`和`Thymeleaf`
- 本项目使用maven骨架

# 更新日志
- 21.01.05 缩略图改为Webp格式；使用JS上传组件删除图片和文件（支持一次性上传多张图片）；**逐渐前后端分离**
- 20.12.23 修改数据库结构，使用Google开源算法压缩图片生成缩图图并修复下载过程中文件头文件bug
- 20.12.06 更改图床图片ID默认数据库存取为`雪花ID`，URL获取图片加密为Base64
- 20.11.15 初始化项目

# 项目说明


## 完成进度
目前项目粗略完成，暂时能想到还未完成：
- ~~文件分类模块~~
- ~~图片压缩处理~~
- ~~邮箱验证注册~~

已经完成：
- 图片/文件上传
- 图片直链获取
- 图片/文件删除（逻辑删除）

## 项目结构
采用经典MVC架构
controller层:
- FileHostController:文件处理(上传、下载等)
- FileTypeController:文件分类(查询分类、增加分类等)
- ImageHostController:图床管理(直链获取、上传等)
- ImageTypeController:图片分类
- UserAdminController:用户模块
- UserIpController:IP记录处理

