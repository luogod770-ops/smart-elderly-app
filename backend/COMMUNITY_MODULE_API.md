# 社区交流模块API文档

## 模块概述

社区交流模块包含版块管理、帖子发布、评论互动等核心功能,为用户提供社区交流平台。

## API列表

### 1. 版块管理接口

#### 1.1 获取版块列表
- **接口**: `GET /board/list`
- **描述**: 获取所有启用的版块列表
- **请求头**:
  - `userId` (可选): 用户ID,用于判断是否已加入版块
- **请求参数**:
  - `status` (可选): 版块状态,默认1(启用)
- **响应数据**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "name": "邻里互助",
      "description": "邻里之间互相帮助",
      "icon": "/icons/board_help.png",
      "postCount": 10,
      "memberCount": 50,
      "isJoined": true,
      "createTime": "2026-03-12 10:00:00"
    }
  ]
}
```

#### 1.2 获取我的版块列表
- **接口**: `GET /board/my`
- **描述**: 获取当前用户已加入的版块列表
- **请求头**:
  - `userId` (必填): 用户ID
- **响应数据**: 同上

#### 1.3 获取版块详情
- **接口**: `GET /board/detail/{boardId}`
- **描述**: 获取指定版块的详细信息
- **路径参数**:
  - `boardId`: 版块ID
- **响应数据**: 单个版块对象

#### 1.4 加入版块
- **接口**: `POST /board/join`
- **描述**: 用户加入指定版块
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "boardId": 1
}
```
- **响应数据**:
```json
{
  "code": 200,
  "message": "success",
  "data": true
}
```

#### 1.5 退出版块
- **接口**: `POST /board/leave`
- **描述**: 用户退出指定版块
- **请求头**:
  - `userId` (必填): 用户ID
- **请求参数**:
  - `boardId`: 版块ID
- **响应数据**: 同上

---

### 2. 帖子管理接口

#### 2.1 发布帖子
- **接口**: `POST /post/create`
- **描述**: 创建并发布新帖子
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "boardId": 1,
  "title": "帖子标题",
  "content": "帖子内容",
  "images": ["/images/img1.jpg", "/images/img2.jpg"],
  "video": "/videos/video.mp4",
  "location": "位置信息"
}
```
- **响应数据**:
```json
{
  "code": 200,
  "message": "success",
  "data": 123  // 返回帖子ID
}
```

#### 2.2 更新帖子
- **接口**: `POST /post/update`
- **描述**: 更新帖子信息(仅作者可操作)
- **请求头**:
  - `userId` (必填): 用户ID
- **请求参数**:
  - `postId`: 帖子ID
- **请求体**: 同发布帖子
- **响应数据**: 操作结果

#### 2.3 删除帖子
- **接口**: `DELETE /post/delete/{postId}`
- **描述**: 删除帖子(仅作者可操作)
- **请求头**:
  - `userId` (必填): 用户ID
- **路径参数**:
  - `postId`: 帖子ID
- **响应数据**: 操作结果

#### 2.4 获取帖子列表
- **接口**: `GET /post/list`
- **描述**: 获取帖子列表,支持多种筛选和分页
- **请求头**:
  - `userId` (可选): 用户ID,用于判断点赞状态
- **请求参数**:
  - `boardId` (可选): 版块ID
  - `userId_filter` (可选): 用户ID(筛选指定用户的帖子)
  - `isTop` (可选): 是否置顶 0-否 1-是
  - `isEssence` (可选): 是否精华 0-否 1-是
  - `page` (可选): 页码,默认1
  - `size` (可选): 每页数量,默认10
- **响应数据**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "boardId": 1,
        "boardName": "邻里互助",
        "userId": 1,
        "userNickname": "张大爷",
        "userAvatar": "/avatars/user1.jpg",
        "title": "帖子标题",
        "content": "帖子内容",
        "images": ["/images/img1.jpg"],
        "video": null,
        "location": "位置",
        "likeCount": 10,
        "commentCount": 5,
        "shareCount": 2,
        "isTop": 0,
        "isEssence": 0,
        "isLiked": true,
        "createTime": "2026-03-12 10:00:00"
      }
    ],
    "total": 100,
    "page": 1,
    "size": 10
  }
}
```

#### 2.5 获取帖子详情
- **接口**: `GET /post/detail/{postId}`
- **描述**: 获取帖子详细信息
- **请求头**:
  - `userId` (可选): 用户ID,用于判断点赞状态
- **路径参数**:
  - `postId`: 帖子ID
- **响应数据**: 单个帖子对象

#### 2.6 点赞/取消点赞帖子
- **接口**: `POST /post/like`
- **描述**: 对帖子进行点赞或取消点赞
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "postId": 1
}
```
- **响应数据**: 操作结果

#### 2.7 转发帖子
- **接口**: `POST /post/share`
- **描述**: 转发帖子到当前用户
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "postId": 1,
  "content": "转发内容(可选)"
}
```
- **响应数据**: 返回新创建的帖子ID

#### 2.8 举报帖子
- **接口**: `POST /post/report`
- **描述**: 举报违规帖子
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "postId": 1,
  "reason": "举报原因"
}
```
- **响应数据**: 操作结果

#### 2.9 置顶/取消置顶
- **接口**: `POST /post/toggleTop`
- **描述**: 切换帖子置顶状态(管理员操作)
- **请求参数**:
  - `postId`: 帖子ID
- **响应数据**: 操作结果

#### 2.10 设置/取消精华
- **接口**: `POST /post/toggleEssence`
- **描述**: 切换帖子精华状态(管理员操作)
- **请求参数**:
  - `postId`: 帖子ID
- **响应数据**: 操作结果

---

### 3. 评论管理接口

#### 3.1 创建评论
- **接口**: `POST /comment/create`
- **描述**: 创建评论(支持回复评论)
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "postId": 1,
  "parentId": 0,  // 0表示顶级评论,否则为父评论ID
  "replyUserId": 2,  // 回复的用户ID(可选)
  "content": "评论内容",
  "images": ["/images/img1.jpg"]
}
```
- **响应数据**: 返回评论ID

#### 3.2 删除评论
- **接口**: `DELETE /comment/delete/{commentId}`
- **描述**: 删除评论(仅作者可操作)
- **请求头**:
  - `userId` (必填): 用户ID
- **路径参数**:
  - `commentId`: 评论ID
- **响应数据**: 操作结果

#### 3.3 获取评论列表
- **接口**: `GET /comment/list`
- **描述**: 获取评论列表
- **请求头**:
  - `userId` (可选): 用户ID,用于判断点赞状态
- **请求参数**:
  - `postId` (必填): 帖子ID
  - `parentId` (可选): 父评论ID,不传则获取顶级评论
  - `page` (可选): 页码,默认1
  - `size` (可选): 每页数量,默认20
- **响应数据**:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "id": 1,
        "postId": 1,
        "userId": 1,
        "userNickname": "张大爷",
        "userAvatar": "/avatars/user1.jpg",
        "parentId": 0,
        "replyUserId": null,
        "replyNickname": null,
        "content": "评论内容",
        "images": ["/images/img1.jpg"],
        "likeCount": 5,
        "isLiked": true,
        "replyCount": 3,
        "createTime": "2026-03-12 10:00:00"
      }
    ],
    "total": 50,
    "page": 1,
    "size": 20
  }
}
```

#### 3.4 点赞/取消点赞评论
- **接口**: `POST /comment/like`
- **描述**: 对评论进行点赞或取消点赞
- **请求头**:
  - `userId` (必填): 用户ID
- **请求体**:
```json
{
  "commentId": 1
}
```
- **响应数据**: 操作结果

#### 3.5 获取评论回复列表
- **接口**: `GET /comment/replies`
- **描述**: 获取指定评论的回复列表
- **请求参数**:
  - `parentId`: 父评论ID
  - `page` (可选): 页码,默认1
  - `size` (可选): 每页数量,默认10
- **响应数据**: 同评论列表

---

## 数据库表结构

### 版块表(board)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| name | VARCHAR(50) | 版块名称 |
| description | VARCHAR(200) | 版块描述 |
| icon | VARCHAR(500) | 版块图标 |
| sort | INT | 排序 |
| status | TINYINT | 状态: 0-禁用 1-启用 |
| post_count | INT | 帖子数 |
| member_count | INT | 成员数 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| deleted | TINYINT | 删除标记 |

### 版块成员表(board_member)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| board_id | BIGINT | 版块ID |
| user_id | BIGINT | 用户ID |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| deleted | TINYINT | 删除标记 |

### 帖子表(post)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| board_id | BIGINT | 版块ID |
| user_id | BIGINT | 用户ID |
| user_nickname | VARCHAR(50) | 用户昵称 |
| user_avatar | VARCHAR(500) | 用户头像 |
| title | VARCHAR(100) | 帖子标题 |
| content | TEXT | 帖子内容 |
| images | TEXT | 图片列表(JSON) |
| video | VARCHAR(500) | 视频 |
| location | VARCHAR(200) | 位置 |
| like_count | INT | 点赞数 |
| comment_count | INT | 评论数 |
| share_count | INT | 转发数 |
| is_top | TINYINT | 是否置顶: 0-否 1-是 |
| is_essence | TINYINT | 是否精华: 0-否 1-是 |
| status | TINYINT | 状态: 0-草稿 1-已发布 2-已删除 |
| audit_status | TINYINT | 审核状态: 0-待审核 1-已通过 2-已拒绝 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| deleted | TINYINT | 删除标记 |

### 评论表(comment)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| post_id | BIGINT | 帖子ID |
| user_id | BIGINT | 用户ID |
| user_nickname | VARCHAR(50) | 用户昵称 |
| user_avatar | VARCHAR(500) | 用户头像 |
| parent_id | BIGINT | 父评论ID (0表示顶级评论) |
| reply_user_id | BIGINT | 回复的用户ID |
| reply_nickname | VARCHAR(50) | 回复的用户昵称 |
| content | TEXT | 评论内容 |
| images | TEXT | 图片(JSON) |
| like_count | INT | 点赞数 |
| status | TINYINT | 状态: 0-已删除 1-正常 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| deleted | TINYINT | 删除标记 |

### 帖子点赞表(post_like)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| post_id | BIGINT | 帖子ID |
| user_id | BIGINT | 用户ID |
| create_time | DATETIME | 创建时间 |
| deleted | TINYINT | 删除标记 |

### 评论点赞表(comment_like)
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 主键ID |
| comment_id | BIGINT | 评论ID |
| user_id | BIGINT | 用户ID |
| create_time | DATETIME | 创建时间 |
| deleted | TINYINT | 删除标记 |

---

## 注意事项

1. **用户认证**: 所有需要登录的接口都需要在请求头中携带 `userId`
2. **权限控制**:
   - 帖子的更新/删除只能由作者操作
   - 评论的删除只能由作者操作
   - 置顶和精华功能需要管理员权限
3. **图片上传**: 图片和视频需要先通过文件上传接口上传,获取URL后再传给相关接口
4. **JSON格式**: 图片列表(images)字段使用JSON数组格式存储
5. **软删除**: 所有删除操作都是软删除,通过deleted字段标记
6. **分页**: 列表接口都支持分页,默认每页10条或20条
7. **时间格式**: 所有时间字段返回格式为 `yyyy-MM-dd HH:mm:ss`

---

## 示例数据

项目初始化时会自动插入以下示例数据:

### 版块示例
- 邻里互助
- 活动聚会
- 闲置置换
- 物业服务
- 健康养生
- 美食分享

### 帖子示例
- 旧电视机赠送
- 社区广场舞活动
- 闲置自行车转让
- 寻找走失宠物狗
- 养生小贴士分享
- 家常菜制作分享

### 评论示例
- 每个帖子包含2-3条示例评论
- 包含回复评论的示例
