# 教育管理系统

这是一个基于Spring Boot框架开发的教育管理系统，用于管理学校的课程、班级、学生、教师等信息。

## 项目结构

- `src/main/java/com/cnlbc/controller`
  - `ClassroomController.java` - 教室管理控制器
- `src/main/java/com/cnlbc/service`
  - `ClassroomService.java` - 教室管理服务接口
  - `ClassroomServiceImpl.java` - 教室管理服务实现
- `src/main/java/com/cnlbc/repository`
  - `ClassroomRepository.java` - 教室数据访问接口
- `src/main/java/com/cnlbc/model`
  - `Classroom.java` - 教室实体类
  - `ClassroomUsage.java` - 教室使用记录实体类

## 项目概述
学生管理系统是一个用于管理教师和班级信息的Web应用程序。该系统旨在简化教师和班级信息的管理流程,提高工作效率。系统支持添加、编辑、删除和查看教师及班级信息,并提供了分页和搜索功能,方便管理员快速查找所需信息。

## 主要功能
- **教师管理**:添加、编辑、删除和查看教师信息。
- **班级管理**:添加、编辑、删除和查看班级信息。
- **分页功能**:支持分页显示教师和班级列表,每页显示10条记录。 
- **搜索功能**:根据教师姓名和ID搜索教师信息,根据班级名称和ID搜索班级信息。

## 技术栈
- **前端**：HTML, CSS, JavaScript, jQuery, Toastr (版本号：1.4.2)
- **后端**：Java, JavaServer Pages (JSP), Maven (版本号：3.8.4)
- **构建工具**：Maven (版本号：3.8.4)

## 系统架构
学生管理系统采用前后端分离的架构设计。前端负责用户界面的展示和用户交互，后端负责数据处理和业务逻辑。前后端通过RESTful API进行通信。

### 前端架构
- 使用HTML、CSS和JavaScript构建用户界面。
- 使用jQuery简化DOM操作和事件处理。
- 使用Toastr库提供用户友好的提示信息。

### 后端架构
- 使用Java编写业务逻辑和数据访问层。
- 使用JavaServer Pages (JSP) 构建动态网页。
- 使用Maven进行项目管理和依赖管理。

## 部署指南
### 后端部署
1. 确保已安装Java开发环境（JDK）和Maven (版本号：3.8.4)。
2. 进入后端项目目录，运行 `mvn clean install` 构建项目。
3. 将生成的WAR文件部署到Tomcat服务器 (版本号：9.0.71)。
4. 启动Tomcat服务器，访问系统。

## 使用示例
1. 打开浏览器,访问系统URL(http://localhost:8080/StudentManageSystem_war)。
2. 登录系统,进入教师管理页面。
3. 点击"添加教师"按钮,填写教师信息并提交。
4. 使用搜索功能,根据教师姓名或ID搜索教师信息。
5. 点击"编辑"按钮,修改教师信息并保存。
6. 点击"删除"按钮,删除教师信息。
7. 进入班级管理页面。
8. 点击"添加班级"按钮,填写班级信息并提交。
9. 使用搜索功能,根据班级名称或ID搜索班级信息。
10. 点击"编辑"按钮,修改班级信息并保存。
11. 点击"删除"按钮,删除班级信息。

## 常见问题 
### Q1: 如何添加教师?
A1: 点击"添加教师"按钮,填写教师信息并提交。

### Q2: 如何添加班级?
A2: 点击"添加班级"按钮,填写班级信息并提交。

## 数据库设计

### 1. 班级表 (CLASSES)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| CLASSID | VARCHAR(10) | 班级ID | - |
| CLASSNAME | VARCHAR(20) | 班级名称 | - |
| DEPARTMENTID | INTEGER | 所属院系ID | 外键 |
| TEACHERID | INTEGER | 班主任ID | - |

### 2. 课程表 (COURSES)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| COURSEID | INTEGER | 课程ID | 主键 |
| COURSENAME | VARCHAR(30) | 课程名称 | 非空 |
| CREDITS | INTEGER | 学分 | - |

### 3. 成绩表 (GRADES)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| GRADEID | INTEGER | 成绩ID | - |
| STUDENTID | INTEGER | 学生ID | 外键 |
| COURSEID | INTEGER | 课程ID | 外键 |
| SEMESTER | VARCHAR(20) | 学期 | - |
| GRADE | INTEGER | 分数 | - |

### 4. 专业表 (MAJORS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| MAJORID | INTEGER | 专业ID | 主键 |
| MAJORNAME | VARCHAR(30) | 专业名称 | - |
| DEPARTMENTID | INTEGER | 所属院系ID | 外键 |

### 5. 院系表 (DEPARTMENTS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| DEPARTMENTID | INTEGER | 院系ID | 主键 |
| DEPARTMENTNAME | VARCHAR(20) | 院系名称 | 非空 |
| ADDRESS | VARCHAR(40) | 地址 | - |

### 6. 学生专业关联表 (STUDENTMAJORS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| STUDENTMAJORSID | INTEGER | 关联ID | - |
| STUDENTID | INTEGER | 学生ID | 外键 |
| MAJORID | INTEGER | 专业ID | 外键 |

### 7. 教师表 (TEACHERS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| TEACHERID | INTEGER | 教师ID | 主键 |
| NAME | VARCHAR(20) | 姓名 | 非空 |
| GENDER | VARCHAR(6) | 性别 | - |
| TITLE | VARCHAR(10) | 职称 | - |
| FIELDOFSTUDY | VARCHAR(30) | 研究领域 | - |
| CONTACTNUMBER | VARCHAR(20) | 联系电话 | - |
| DEPARTMENTID | INTEGER | 所属院系ID | 外键 |

### 8. 用户表 (USERS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| USERTID | INTEGER | 用户ID | 主键 |
| USERNAME | VARCHAR(20) | 用户名 | 非空 |
| PASSWORD | VARCHAR(20) | 密码 | - |

### 9. 学生表 (STUDENTS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| STUDENTID | INTEGER | 学生ID | 主键 |
| NAME | VARCHAR(20) | 姓名 | 非空 |
| GENDER | VARCHAR(6) | 性别 | - |
| DATEOFBIRTH | DATE | 出生日期 | - |
| CONTACTNUMBER | VARCHAR(20) | 联系电话 | - |
| EMAIL | VARCHAR(20) | 电子邮箱 | - |
| ENROLLMENTDATE | DATE | 入学日期 | - |

### 10. 教室表 (CLASSROOMS)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| ROOMID | INTEGER | 教室ID | 主键 |
| ROOMNAME | VARCHAR(20) | 教室名称 | 非空 |
| BUILDING | VARCHAR(20) | 所在教学楼 | - |
| FLOOR | INTEGER | 所在楼层 | - |
| CAPACITY | INTEGER | 容纳人数 | - |
| TYPE | VARCHAR(20) | 教室类型 | - |
| STATUS | VARCHAR(10) | 使用状态 | - |
| EQUIPMENT | VARCHAR(100) | 设备信息 | - |
| DEPARTMENTID | INTEGER | 所属院系ID | 外键 |

### 11. 教室使用记录表 (CLASSROOM_USAGE)
| 字段 | 类型 | 说明 | 约束 |
|------|------|------|------|
| USAGEID | INTEGER | 使用记录ID | 主键 |
| ROOMID | INTEGER | 教室ID | 外键 |
| COURSEID | INTEGER | 课程ID | 外键 |
| TEACHERID | INTEGER | 教师ID | 外键 |
| STARTTIME | TIMESTAMP | 开始时间 | - |
| ENDTIME | TIMESTAMP | 结束时间 | - |
| WEEKDAY | INTEGER | 星期几 | - |
| SEMESTER | VARCHAR(20) | 学期 | - |

## 表关系说明

1. 一个院系(DEPARTMENTS)可以有多个班级(CLASSES)
2. 一个院系(DEPARTMENTS)可以有多个专业(MAJORS)
3. 一个院系(DEPARTMENTS)可以有多个教师(TEACHERS)
4. 一个学生(STUDENTS)可以选多个专业(MAJORS)，通过STUDENTMAJORS关联
5. 一个学生(STUDENTS)可以有多个成绩(GRADES)
6. 一个课程(COURSES)可以有多个成绩记录(GRADES)
7. 一个院系(DEPARTMENTS)可以管理多个教室(CLASSROOMS)
8. 一个教室(CLASSROOMS)可以有多个使用记录(CLASSROOM_USAGE)
9. 一个教师(TEACHERS)可以有多个教室使用记录(CLASSROOM_USAGE)

## 功能模块

7. 教室管理
   - 教室信息维护
     * 添加新教室
     * 修改教室信息
     * 删除教室
     * 查询教室信息
   - 教室使用管理
     * 教室排课
     * 教室使用查询
     * 空闲教室查询
   - 设备管理
     * 教室设备登记
     * 设备维护记录
   - 统计分析
     * 教室使用率统计
     * 教室容量分析