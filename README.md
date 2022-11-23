![](introduction.png)
![](https://img.shields.io/badge/Springboot-2.3.4-green)
![](https://img.shields.io/badge/Mybatis%20Plus-3.4.0-green)

# Project abstract

- Based on `Springboot`、`Mybatis Plus` and `Thymeleaf`
- `maven`

# Update log
- 21.01.05 change abstract image into Webp form; use JS tools to delete files and documents（multiple files upload is supported); **gradually depart backend from frontend**
- 20.12.23 modify database structure，use Google source code compress images
- 20.12.06 change image id to `Snowflake ID`，URL encryption: Base64
- 20.11.15 Initialization

# Project demonstration


## Complement progress
Not completed yet:
- ~~file classification~~
- ~~image compression~~
- ~~email validation registration~~

Completed:
- image/file upload
- image direct link access 
- image/file delete

## Project structrue
Classic MVC structure
controller:
- FileHostController: file process(upload/download/etc)
- FileTypeController: file classification(search/add classification)
- ImageHostController: imagebed manipulation(direct link access/upload/etc)
- ImageTypeController: image classification
- UserAdminController: user module
- UserIpController: IP record and process

