## 目录

* 1\. [用户名重复性校验](#1\.-用户名重复性校验)
* 2\. [邮箱重复性校验](#2\.-邮箱重复性校验)
* 3\. [获取注册用验证码](#3\.-获取注册用验证码)
* 4\. [用户注册](#4\. 用户注册)
* 5\. [用户登录](#5\.-用户登录)
* 6\. [用户更改头像](#6\.-用户更改头像)
* 7\. [申请重置密码](#7\.-申请重置密码)
* 8\. [重置密码](#8\. 重置密码)
* 9\. [查看用户信息](#9\.-查看用户信息)
* 10\. [用户更新个人信息](#10\.-用户更新个人信息)
* 11\. [专辑信息自动匹配](#11\.-专辑信息自动匹配)
* 12\. [自动填充专辑详细](#12\. 自动填充专辑详细)
* 13\. [校验专辑标题](#13\.-校验专辑标题)
* 14\. [用户创建维护专辑](#14\.-用户创建维护专辑)
* 15\. [修改维护专辑](#15\.-修改维护专辑)
* 16\. [上传维护专辑的封面](#16\. 上传维护专辑的封面)
* 17\. [查看专辑详细信息](#17\.-查看专辑详细信息)
* 18\. [查看推荐专辑](#18\.-查看推荐专辑)
* 19\. [查看指定用户所有专辑](#19\.-查看指定用户所有专辑)
* 20\. [删除专辑](#20\. 删除专辑)
* 21\. [按照标签搜索专辑](#21\.-按照标签搜索专辑)
* 22\. [按照标题模糊搜索专辑](#22\.-按照标题模糊搜索专辑)
* 23\. [提交评论](#23\.-提交评论)
* 24\. [拉取专辑下所有评论](#24\. 拉取专辑下所有评论)
* 25\. [提交修改请求](#25\.-提交修改请求)
* 26\. [查看专辑下所有等待修改队列](#26\.-查看专辑下所有等待修改队列)
* 27\. [维护者应用修改](#27\.-维护者应用修改)
* 28\. [维护者拒绝修改](#28\. 维护者拒绝修改)
* 29\. [打赏](#29\.-打赏)
* 30\. [获取专辑打赏列表](#30\.-获取专辑打赏列表)
* 31\. [是否有新消息](#31\.-是否有新消息)
* 32\. [获取消息列表](#32\. 获取消息列表)
* 33\. [专辑审核](#33\.-专辑审核)
* 34\. [拉取专辑列表](#34\.-拉取专辑列表)
* 35\. [拉取用户列表](#35\.-拉取用户列表)

---


### 1\. 用户名重复性校验

###### 接口功能
> 验证用户名是否可以使用

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/check/usernames]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> 

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | username  | String | 需要校验的用户名 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String   | 返回结果文字说明 |
> | data     | Boolean  | 返回是否校验通过 |

###### 接口示例
> 地址：[https://api.otakuy.com/check/usernames?username=生蚝QAQ]()  
> 允许注册(200)
``` javascript
{
    "message": "用户名有效",
    "data": true
}
```
> 用户名重复(400)
``` javascript
{
    "message": "用户名校验不通过(已存在相同用户名)",
    "data": false
}
```

### 2\. 邮箱重复性校验

###### 接口功能
> 验证邮箱是否可以使用

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/check/emails]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> 

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | email  | String | 需要校验的邮箱 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String   | 返回结果文字说明 |
> | data     | Boolean  | 返回是否校验通过 |

###### 接口示例
> 地址：[https://api.otakuy.com/check/emails?email=oysterqaq@acg.edu.kg]()  
> 允许注册(200)
``` javascript
{
    "message": "邮箱有效",
    "data": true
}
```
> 邮箱重复(400)
``` javascript
{
    "message": "邮箱校验不通过(已存在相同邮箱)",
    "data": false
}
```

### 3\. 注册用验证码获取

###### 接口功能
> 获取注册用验证码

###### 接口说明
> 验证码获取后三分钟内有效,使用后自动失效

###### URL
> [https://api.otakuy.com/verificationCode]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> 

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段    | 字段类型 | 说明                       |
> | :---------- | :------- | :------------------------- |
> | message     | String | 返回结果文字说明           |
> | data.id          | String   | 校验字段之一               |
> | data.imageBase64 | String   | base64编码的验证码图片数据 |
> | data.createtime | Date   | 验证码生成时间 |

###### 接口示例
> 地址：[https://api.otakuy.com/verificationCode]()  
> 获取成功(200)
``` javascript
{
    "message": "ok",
    "data": {
        "id": "5c661ab678be861980ca2c27",
        "imageBase64": "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",
        "createtime": "2019-02-15T01:49:42.909+0000"
    }
}
```

### 4\. 用户注册

###### 接口功能
> 新用户注册

###### 接口说明
> 校验:验证码有效性&用户名有效性&邮箱有效性

###### URL
> [https://api.otakuy.com/register]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | verificationCodeId | String | 验证码Id |
> | verificationCode | String | 验证码 |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
  "username": "生蚝QAQ",
  "password": "2333",
  "email": "349135289789457457852@qq.com",
  "avatar": "https://img.otakuy.com/default.png",
  "intro": "233333"
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String | 返回结果文字说明 |
> | data     | User  | 用户详细信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/register]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | verificationCodeId | 5c51bb8378be86577caf4e76 |
> | verificationCode | Vc44 |
> 请求体
``` javascript
{
  "username": "生蚝QAQ",
  "password": "2333",
  "email": "349135289789457457852@qq.com",
  "avatar": "https://img.otakuy.com/default.png",
  "intro": "233333"
}
```
> 注册完成(200)
``` javascript
{
    "message": "注册完成",
    "data": {
        "id": "5c662c8878be861980ca2c29",
        "username": "生蚝QAQ",
        "avatar": "https://avatar.otakuy.com/default.png",
        "email": "oysterqaq@acg.edu.kg",
        "intro": "233333",
        "star": 0,
        "enabled": true,
        "role": [
            "ROLE_USER"
        ]
    }
}
```
> 验证码校验出错(400)
``` javascript
{
    "message": "邮箱校验不通过(已存在相同邮箱)",
}
```
> 邮箱或用户名重复(400)
``` javascript
{
    "message": "用户名或邮箱已被注册",
}
```

### 5\. 用户登录

###### 接口功能
> 用户登录

###### 接口说明
> 登录成功返回token(24小时有效)

###### URL
> [https://api.otakuy.com/login]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> 

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
	"username":"生蚝QAQ",
	"password":"2333"
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String | 返回结果文字说明 |
> | data     | String | token |

###### 接口示例
> 地址：[https://api.otakuy.com/register]() 
> 请求体
``` javascript
{
	"username":"生蚝QAQ",
	"password":"2333"
}
```
> 登录成功(200)
``` javascript
{
    "message": "登录成功",
    "data": "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A"
}
```
> 用户名或密码错误(400)
``` javascript
{
     "message": "登录失败:用户名或密码错误"
}
```
### 6\. 用户更改头像

###### 接口功能
> 用户上传头像

###### 接口说明
> 每个用户头像链接固定(user_id.png),仅支持上传jpg或者png文件

###### URL
> [https://api.otakuy.com/users/avatars]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> form-data
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | file | image | 头像文件 |

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | String | 头像链接 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
//图片文件
```
> 上传成功(200)
``` javascript
{
    "message": "上传头像成功",
    "data": "https://img.otakuy.com/5c662c8878be861980ca2c29.png"
}
```
> 文件格式错误(400)
``` javascript
{
    "message": "图片格式不支持,上传失败"
}
```
### 7\. 申请重置密码

###### 接口功能
> 忘记密码申请重置

###### 接口说明
> 申请重置后会发送带校验参数的重置页面链接到用户邮箱,进入页面后重置密码(携带校验参数)

###### URL
> [https://api.otakuy.com/forgetPassword]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> 

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | email  | String | 账号邮箱 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String   | 返回结果文字说明 |

###### 接口示例
> 地址：[https://api.otakuy.com/forgetPassword?email=392822872@qq.com]() 

> 发送重置邮件成功(200)
``` javascript
{
    "message": "发送验证邮件成功"
}
```
> 邮箱不存在(400)
``` javascript
{
    "message": "邮箱不存在"
}
```
### 8\. 重置密码

###### 接口功能
> 密码重置

###### 接口说明
> 携带校验参数重置密码

###### URL
> [https://api.otakuy.com/forgetPassword]()

###### 支持格式
> JSON

###### 请求方式
> PUT

###### 请求头
> 

###### 请求参数
> | 参数 | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | email | String | 用户邮箱 |
> | verificationCode | String | 验证码 |
> | verificationCodeId | String | 验证码Id |
> | password | String | 新密码 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String   | 返回结果文字说明 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/password?email=392822872@qq.com&verificationCode=GQqT&verificationCodeId=5c664be678be8642fc2ad975&password=123]() 

> 重置成功(200)
``` javascript
{
    "message": "修改密码成功"
}
```
> 校验信息错误(400)
``` javascript
{
    "message": "重置密码失败:链接错误或失效"
}
```
### 9\. 查看用户信息

###### 接口功能
> 查看用户资料

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/users/{user_id}]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | User | 用户信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/5c662c8878be861980ca2c29]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "成功拉取生蚝QAQ的个人信息",
    "data": {
        "id": "5c662c8878be861980ca2c29",
        "username": "生蚝QAQ",
        "avatar": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
        "email": "oysterqaq@acg.edu.kg",
        "intro": "233333",
        "star": 0,
        "enabled": true,
        "role": [
            "ROLE_USER"
        ]
    }
}
```
> 用户不存在(400)
``` javascript
{
    "message": "用户不存在"
}
```
### 10\. 用户更新个人信息

###### 接口功能
> 用户更新部分个人信息

###### 接口说明
> 目前仅支持头像与自我介绍

###### URL
> [https://api.otakuy.com/users/avatars]()

###### 支持格式
> JSON

###### 请求方式
> PUT

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
    "username": "*",
    "password": "*",
    "avatar": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
    "email": "*@*.com",
    "intro": "233333"
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | User | 更新后个人信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/5c662c8878be861980ca2c29]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
{
    "username": "*",
    "password": "*",
    "avatar": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
    "email": "*@*.com",
    "intro": "233333"
}
```
> 更新成功(200)
``` javascript
{
    "message": "更新完成",
    "data": {
        "id": "5c662c8878be861980ca2c29",
        "username": "生蚝QAQ",
        "avatar": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
        "email": "oysterqaq@acg.edu.kg",
        "intro": "233333",
        "star": 0,
        "enabled": true,
        "role": [
            "ROLE_USER"
        ]
    }
}
```

### 11\. 专辑信息自动匹配

###### 接口功能
> 实现根据专辑名筛选专辑建议

###### 接口说明
> 依赖豆瓣api

###### URL
> [https://api.otakuy.com/douban]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | title | String | 专辑标题 |

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data.title | String | 专辑标题 |
> | data.douban_id | String | 专辑位于豆瓣音乐的id |
> | data.cover | String | 专辑封面 |

###### 接口示例
> 地址：[https://api.otakuy.com/douban?title=Fantôme]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |

> 拉取成功(200)
``` javascript
{
    "message": "以下是搜索建议",
    "data": [
        {
            "title": "Fantôme",
            "douban_id": "26832501",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s29000484.jpg"
        },
        {
            "title": "Fantôme",
            "douban_id": "26878444",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s29054534.jpg"
        },
        {
            "title": "Fantôme",
            "douban_id": "26936294",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s29221652.jpg"
        },
        {
            "title": "Velvet on the Air",
            "douban_id": "4930568",
            "cover": "https://img1.doubanio.com/view/subject/s/public/s4430879.jpg"
        },
        {
            "title": "Bare Mythology",
            "douban_id": "5342448",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s4517525.jpg"
        },
        {
            "title": "It All Makes Sense",
            "douban_id": "26638328",
            "cover": "https://img1.doubanio.com/view/subject/s/public/s28313398.jpg"
        },
        {
            "title": "Fantôme (Korea Edition)",
            "douban_id": "26883186",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s29068555.jpg"
        },
        {
            "title": "Fantomes",
            "douban_id": "2131297",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s2554962.jpg"
        },
        {
            "title": "Fantome Noir",
            "douban_id": "26589015",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s28272941.jpg"
        },
        {
            "title": "Fantôme avec chauffeur(Ghost With Driver)",
            "douban_id": "26258682",
            "cover": "https://img1.doubanio.com/view/subject/s/public/s27802728.jpg"
        },
        {
            "title": "Fantomes\\/Monstres & Sorcieres",
            "douban_id": "2353798",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s2818491.jpg"
        },
        {
            "title": "Belphégor Le Fantôme du Louvre",
            "douban_id": "5279993",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s2967522.jpg"
        },
        {
            "title": "La Place Du Fantôme",
            "douban_id": "10589084",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s9030515.jpg"
        },
        {
            "title": "L'Integrale Sixties",
            "douban_id": "2080682",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s2405893.jpg"
        },
        {
            "title": "Tour de Manège 2 : Le Train Fantôme",
            "douban_id": "26343138",
            "cover": "https://img3.doubanio.com/view/subject/s/public/s28025233.jpg"
        }
    ]
}
```
### 12\. 自动填充专辑详细

###### 接口功能
> 跟随专辑信息自动匹配获取专辑详细信息

###### 接口说明
> 依赖豆瓣音乐api

###### URL
> [https://api.otakuy.com/douban/{douban_id}]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | douban_id | String | 专辑位于豆瓣音乐的id |


###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Album | 专辑详细信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/douban/26832501]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "拉取成功",
    "data": {
        "title": "Fantôme",
        "tracks": [
            {
                "title": "1:道",
                "preview": ""
            },
            {
                "title": "2:俺の彼女",
                "preview": ""
            },
            {
                "title": "3:花束を君に",
                "preview": ""
            },
            {
                "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
                "preview": ""
            },
            {
                "title": "4:二時間だけのバカンス",
                "preview": ""
            },
            {
                "title": "5:人魚",
                "preview": ""
            },
            {
                "title": "6:ともだち",
                "preview": ""
            },
            {
                "title": "7:真夏の通り雨",
                "preview": ""
            },
            {
                "title": "日本テレビ「NEWSZERO」テーマ曲",
                "preview": ""
            },
            {
                "title": "8:荒野の狼",
                "preview": ""
            },
            {
                "title": "9:忘却",
                "preview": ""
            },
            {
                "title": "10:人生最高の日",
                "preview": ""
            },
            {
                "title": "11:桜流し",
                "preview": ""
            },
            {
                "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
                "preview": ""
            }
        ],
        "artists": [
            {
                "name": "宇多田ヒカル"
            }
        ],
        "pubdate": "2016-09-28",
        "publisher": "Universal Music =music=",
        "genres": "流行",
        "version": "专辑",
        "tags": [
            {
                "name": "日本"
            },
            {
                "name": "女声"
            },
            {
                "name": "J-pop"
            },
            {
                "name": "专辑"
            },
            {
                "name": "流行"
            },
            {
                "name": "R&B"
            },
            {
                "name": "日语"
            }
        ],
        "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
        "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
        "douban_url": "https://music.douban.com/subject/26832501/",
        "rating": 8.8027,
        "rating_count": 4604
    }
}
```
### 13\. 校验专辑标题

###### 接口功能
> 校验专辑标题有效性

###### 接口说明
> 将校验专辑标题是否在活跃状态下的专辑中唯一

###### URL
> [https://api.otakuy.com/check/albums]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | title  | String | 需要校验的专辑标题 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Boolean | 是否有效 |

###### 接口示例
> 地址：[https://api.otakuy.com/check/albums?title=Fantôme]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 专辑标题可用(200)
``` javascript
{
    "message": "专辑标题有效",
    "data": true
}
```
> 专辑标题不可用(400)
``` javascript
{
    "message": "专辑标题校验不通过:已存在相同专辑名",
    "data": false
}
```

### 14\. 用户创建维护专辑

###### 接口功能
> 用户创建新的专辑并等待审核

###### 接口说明
> 将校验专辑标题是否在活跃状态下的专辑中唯一

###### URL
> [https://api.otakuy.com/users/albums]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
    "title": "Fantôme",
    "tracks": [
        {
            "title": "1:道",
            "preview": ""
        },
        {
            "title": "2:俺の彼女",
            "preview": ""
        },
        {
            "title": "3:花束を君に",
            "preview": ""
        },
        {
            "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
            "preview": ""
        },
        {
            "title": "4:二時間だけのバカンス",
            "preview": ""
        },
        {
            "title": "5:人魚",
            "preview": ""
        },
        {
            "title": "6:ともだち",
            "preview": ""
        },
        {
            "title": "7:真夏の通り雨",
            "preview": ""
        },
        {
            "title": "日本テレビ「NEWSZERO」テーマ曲",
            "preview": ""
        },
        {
            "title": "8:荒野の狼",
            "preview": ""
        },
        {
            "title": "9:忘却",
            "preview": ""
        },
        {
            "title": "10:人生最高の日",
            "preview": ""
        },
        {
            "title": "11:桜流し",
            "preview": ""
        },
        {
            "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
            "preview": ""
        }
    ],
    "artists": [
        {
            "name": "宇多田ヒカル"
        }
    ],
    "pubdate": "2016-09-28",
    "publisher": "Universal Music =music=",
    "genres": "流行",
    "version": "专辑",
    "tags": [
        {
            "name": "日本"
        },
        {
            "name": "女声"
        },
        {
            "name": "J-pop"
        },
        {
            "name": "专辑"
        },
        {
            "name": "流行"
        },
        {
            "name": "R&B"
        },
        {
            "name": "日语"
        }
    ],
    "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
    "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
    "douban_url": "https://music.douban.com/subject/26832501/",
    "rating": 8.8027,
    "rating_count": 4604
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Album | 专辑详细信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
{
    "title": "Fantôme",
    "tracks": [
        {
            "title": "1:道",
            "preview": ""
        },
        {
            "title": "2:俺の彼女",
            "preview": ""
        },
        {
            "title": "3:花束を君に",
            "preview": ""
        },
        {
            "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
            "preview": ""
        },
        {
            "title": "4:二時間だけのバカンス",
            "preview": ""
        },
        {
            "title": "5:人魚",
            "preview": ""
        },
        {
            "title": "6:ともだち",
            "preview": ""
        },
        {
            "title": "7:真夏の通り雨",
            "preview": ""
        },
        {
            "title": "日本テレビ「NEWSZERO」テーマ曲",
            "preview": ""
        },
        {
            "title": "8:荒野の狼",
            "preview": ""
        },
        {
            "title": "9:忘却",
            "preview": ""
        },
        {
            "title": "10:人生最高の日",
            "preview": ""
        },
        {
            "title": "11:桜流し",
            "preview": ""
        },
        {
            "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
            "preview": ""
        }
    ],
    "artists": [
        {
            "name": "宇多田ヒカル"
        }
    ],
    "pubdate": "2016-09-28",
    "publisher": "Universal Music =music=",
    "genres": "流行",
    "version": "专辑",
    "tags": [
        {
            "name": "日本"
        },
        {
            "name": "女声"
        },
        {
            "name": "J-pop"
        },
        {
            "name": "专辑"
        },
        {
            "name": "流行"
        },
        {
            "name": "R&B"
        },
        {
            "name": "日语"
        }
    ],
    "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
    "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
    "douban_url": "https://music.douban.com/subject/26832501/",
    "rating": 8.8027,
    "rating_count": 4604
}
```
> 上传成功(200)
``` javascript
{
    "message": "创建成功",
    "data": {
        "id": "5c6658af78be863730550145",
        "title": "Fantôme",
        "tracks": [
            {
                "title": "1:道",
                "preview": ""
            },
            {
                "title": "2:俺の彼女",
                "preview": ""
            },
            {
                "title": "3:花束を君に",
                "preview": ""
            },
            {
                "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
                "preview": ""
            },
            {
                "title": "4:二時間だけのバカンス",
                "preview": ""
            },
            {
                "title": "5:人魚",
                "preview": ""
            },
            {
                "title": "6:ともだち",
                "preview": ""
            },
            {
                "title": "7:真夏の通り雨",
                "preview": ""
            },
            {
                "title": "日本テレビ「NEWSZERO」テーマ曲",
                "preview": ""
            },
            {
                "title": "8:荒野の狼",
                "preview": ""
            },
            {
                "title": "9:忘却",
                "preview": ""
            },
            {
                "title": "10:人生最高の日",
                "preview": ""
            },
            {
                "title": "11:桜流し",
                "preview": ""
            },
            {
                "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
                "preview": ""
            }
        ],
        "artists": [
            {
                "name": "宇多田ヒカル"
            }
        ],
        "pubdate": "2016-09-28",
        "publisher": "Universal Music =music=",
        "genres": "流行",
        "version": "专辑",
        "tags": [
            {
                "name": "日本"
            },
            {
                "name": "女声"
            },
            {
                "name": "J-pop"
            },
            {
                "name": "专辑"
            },
            {
                "name": "流行"
            },
            {
                "name": "R&B"
            },
            {
                "name": "日语"
            }
        ],
        "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
        "cover": "https://cover.otakuy.com/default.png",
        "douban_url": "https://music.douban.com/subject/26832501/",
        "rating": 0,
        "rating_count": 0,
        "owner": "5c662c8878be861980ca2c29",
        "status": "block",
        "isRecommend": false
    }
}
```
> 专辑名重复(400)
``` javascript
{
    "message": "已存在同标题活跃专辑"
}
```
### 15\. 修改维护专辑

###### 接口功能
> 用户更新专辑信息

###### 接口说明
> 每个用户头像链接固定(user_id.png),仅支持上传jpg或者png文件

###### URL
> [https://api.otakuy.com/albums/{album_id}]()

###### 支持格式
> JSON

###### 请求方式
> PUT

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
    "title": "Fantôme123",
    "tracks": [
        {
            "title": "1:道",
            "preview": ""
        },
        {
            "title": "2:俺の彼女",
            "preview": ""
        },
        {
            "title": "3:花束を君に",
            "preview": ""
        },
        {
            "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
            "preview": ""
        },
        {
            "title": "4:二時間だけのバカンス",
            "preview": ""
        },
        {
            "title": "5:人魚",
            "preview": ""
        },
        {
            "title": "6:ともだち",
            "preview": ""
        },
        {
            "title": "7:真夏の通り雨",
            "preview": ""
        },
        {
            "title": "日本テレビ「NEWSZERO」テーマ曲",
            "preview": ""
        },
        {
            "title": "8:荒野の狼",
            "preview": ""
        },
        {
            "title": "9:忘却",
            "preview": ""
        },
        {
            "title": "10:人生最高の日",
            "preview": ""
        },
        {
            "title": "11:桜流し",
            "preview": ""
        },
        {
            "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
            "preview": ""
        }
    ],
    "artists": [
        {
            "name": "宇多田ヒカル"
        }
    ],
    "pubdate": "2016-09-28",
    "publisher": "Universal Music =music=",
    "genres": "流行",
    "version": "专辑",
    "tags": [
        {
            "name": "日本"
        },
        {
            "name": "女声"
        },
        {
            "name": "J-pop"
        },
        {
            "name": "专辑"
        },
        {
            "name": "流行"
        },
        {
            "name": "R&B"
        },
        {
            "name": "日语"
        }
    ],
    "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
    "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
    "douban_url": "https://music.douban.com/subject/26832501/",
    "rating": 8.8027,
    "rating_count": 4604
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Album | 专辑详细信息     |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
{
    "title": "Fantôme123",
    "tracks": [
        {
            "title": "1:道",
            "preview": ""
        },
        {
            "title": "2:俺の彼女",
            "preview": ""
        },
        {
            "title": "3:花束を君に",
            "preview": ""
        },
        {
            "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
            "preview": ""
        },
        {
            "title": "4:二時間だけのバカンス",
            "preview": ""
        },
        {
            "title": "5:人魚",
            "preview": ""
        },
        {
            "title": "6:ともだち",
            "preview": ""
        },
        {
            "title": "7:真夏の通り雨",
            "preview": ""
        },
        {
            "title": "日本テレビ「NEWSZERO」テーマ曲",
            "preview": ""
        },
        {
            "title": "8:荒野の狼",
            "preview": ""
        },
        {
            "title": "9:忘却",
            "preview": ""
        },
        {
            "title": "10:人生最高の日",
            "preview": ""
        },
        {
            "title": "11:桜流し",
            "preview": ""
        },
        {
            "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
            "preview": ""
        }
    ],
    "artists": [
        {
            "name": "宇多田ヒカル"
        }
    ],
    "pubdate": "2016-09-28",
    "publisher": "Universal Music =music=",
    "genres": "流行",
    "version": "专辑",
    "tags": [
        {
            "name": "日本"
        },
        {
            "name": "女声"
        },
        {
            "name": "J-pop"
        },
        {
            "name": "专辑"
        },
        {
            "name": "流行"
        },
        {
            "name": "R&B"
        },
        {
            "name": "日语"
        }
    ],
    "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
    "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
    "douban_url": "https://music.douban.com/subject/26832501/",
    "rating": 8.8027,
    "rating_count": 4604
}
```
> 更新成功(200)
``` javascript
{
    "message": "更新成功",
    "data": {
        "id": "5c6658af78be863730550145",
        "title": "Fantôme123",
        "tracks": [
            {
                "title": "1:道",
                "preview": ""
            },
            {
                "title": "2:俺の彼女",
                "preview": ""
            },
            {
                "title": "3:花束を君に",
                "preview": ""
            },
            {
                "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
                "preview": ""
            },
            {
                "title": "4:二時間だけのバカンス",
                "preview": ""
            },
            {
                "title": "5:人魚",
                "preview": ""
            },
            {
                "title": "6:ともだち",
                "preview": ""
            },
            {
                "title": "7:真夏の通り雨",
                "preview": ""
            },
            {
                "title": "日本テレビ「NEWSZERO」テーマ曲",
                "preview": ""
            },
            {
                "title": "8:荒野の狼",
                "preview": ""
            },
            {
                "title": "9:忘却",
                "preview": ""
            },
            {
                "title": "10:人生最高の日",
                "preview": ""
            },
            {
                "title": "11:桜流し",
                "preview": ""
            },
            {
                "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
                "preview": ""
            }
        ],
        "artists": [
            {
                "name": "宇多田ヒカル"
            }
        ],
        "pubdate": "2016-09-28",
        "publisher": "Universal Music =music=",
        "genres": "流行",
        "version": "专辑",
        "tags": [
            {
                "name": "日本"
            },
            {
                "name": "女声"
            },
            {
                "name": "J-pop"
            },
            {
                "name": "专辑"
            },
            {
                "name": "流行"
            },
            {
                "name": "R&B"
            },
            {
                "name": "日语"
            }
        ],
        "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
        "cover": "https://img3.doubanio.com/view/subject/m/public/s29000484.jpg",
        "douban_url": "https://music.douban.com/subject/26832501/",
        "rating": 0,
        "rating_count": 0,
        "owner": "5c662c8878be861980ca2c29",
        "status": "active",
        "isRecommend": false
    }
}
```
> 非本人专辑(401)
``` javascript
{
    "message": "权限不足"
}
```
### 16\. 上传维护专辑的封面

###### 接口功能
> 用户上传或更新专辑封面

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/albums/{album_id}/covers]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> form-data
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | file | image | 头像文件 |

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | String | 专辑封面链接 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
//图片文件
```
> 上传成功(200)
``` javascript
{
    "message": "上传专辑封面成功",
    "data": "https://img.otakuy.com/5c6658af78be863730550145.png"
}
```
> 非本人专辑(401)
``` javascript
{
    "message": "权限不足"
}
```
### 17\. 查看专辑详细信息

###### 接口功能
> 查看专辑详细信息

###### 接口说明
> 根据用户star数与专辑下载资源做权限对比,通过(或属于本人维护专辑)则显示下载资源不通过则不显示

###### URL
> [https://api.otakuy.com/users/avatars]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Album | 专辑详细信息 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)(根据权限判断是否显示下载资源)
``` javascript
{
    "message": "拉取专辑详细成功",
    "data": {
        "id": "5c6658af78be863730550145",
        "title": "Fantôme",
        "tracks": [
            {
                "title": "1:道",
                "preview": ""
            },
            {
                "title": "2:俺の彼女",
                "preview": ""
            },
            {
                "title": "3:花束を君に",
                "preview": ""
            },
            {
                "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
                "preview": ""
            },
            {
                "title": "4:二時間だけのバカンス",
                "preview": ""
            },
            {
                "title": "5:人魚",
                "preview": ""
            },
            {
                "title": "6:ともだち",
                "preview": ""
            },
            {
                "title": "7:真夏の通り雨",
                "preview": ""
            },
            {
                "title": "日本テレビ「NEWSZERO」テーマ曲",
                "preview": ""
            },
            {
                "title": "8:荒野の狼",
                "preview": ""
            },
            {
                "title": "9:忘却",
                "preview": ""
            },
            {
                "title": "10:人生最高の日",
                "preview": ""
            },
            {
                "title": "11:桜流し",
                "preview": ""
            },
            {
                "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
                "preview": ""
            }
        ],
        "artists": [
            {
                "name": "宇多田ヒカル"
            }
        ],
        "pubdate": "2016-09-28",
        "publisher": "Universal Music =music=",
        "genres": "流行",
        "version": "专辑",
        "tags": [
            {
                "name": "日本"
            },
            {
                "name": "女声"
            },
            {
                "name": "J-pop"
            },
            {
                "name": "专辑"
            },
            {
                "name": "流行"
            },
            {
                "name": "R&B"
            },
            {
                "name": "日语"
            }
        ],
        "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
        "cover": "https://cover.otakuy.com/default.png",
        "douban_url": "https://music.douban.com/subject/26832501/",
        "rating": 0,
        "rating_count": 0,
        "owner": "5c662c8878be861980ca2c29",
        "downloadRes": {
            "permission": 20,
            "url": "https://pan.baidu.com",
            "password": "1234",
            "unzipKey": null
        },
        "status": "active",
        "isRecommend": false
    }
}
```
> 专辑id错误(400)
``` javascript
{
    "message": "专辑不存在"
}
```
### 18\. 查看推荐专辑

###### 接口功能
> 查看推荐专辑

###### 接口说明
> 首页用,可以由管理员设置推荐专辑

###### URL
> [https://api.otakuy.com/albums/recommendAlbum]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data.id  | String | 专辑id |
> | data.title | String | 专辑标题 |
> | data.intro | String | 专辑简介 |
> | data.cover | String | 专辑封面 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/recommendAlbum]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "共有2张置顶专辑",
    "data": [
        {
            "id": "5c5faefa78be864eb0f22802",
            "title": "123456",
            "intro": "十年多之后的今天，『One more time, One more chance』配合新海诚的新电影重新发售特别版。熟悉JPOP的朋友应该知道，这首曲子曾是山崎まさよし主演的电影「月とキャベツ」的主题曲。也是他的代表曲目之一。\n凝结了新海监督想最描绘的东西的作品「秒速5厘米」。\n把现在这里有的“日常”的风景做为舞台，尝试用动画片表现出每天谁都能感觉到现实感。\n正因为如此，新海监督自己很强地认识到做为主题歌的乐曲的重要性。\n新海监督所想象的主题歌——「谁都知道，对现代人们来说有普遍性的乐曲」。\n从过去到现在的许多的流行的歌曲，找寻了适合这个条件的乐曲\n最终走到了山崎まさよし的「One more time, One more chance」面前。\n在新海作品那情感溢出的风景里，配以这伤感却美丽的旋律。影片结束时响起的歌声有种力量让人感动……\n单曲中还收录了天门所编曲的一曲\"雪の駅\"，还有初次音源化的\"弾き語りVer\"。",
            "cover": "https://img3.doubanio.com/view/subject/m/public/s2340104.jpg"
        },
        {
            "id": "5c62a03678be860d703581b0",
            "title": "aa",
            "intro": "内容（「CDジャーナル」データベースより）大絶賛を浴びた前作「風の谷のナウシカ」から2年,宮崎駿の原作・脚本・監督による話題のアニメ作品「天空の城ラピュタ」がついに完成。久石譲の音楽は前作同様だが今回はぐっと明るく楽しい曲が多くハツラツとした少年少女の夢と冒険のドラマを謳う。",
            "cover": "https://cover.otakuy.com/default.png"
        }
    ]
}
```

### 19\. 查看指定用户所有专辑

###### 接口功能
> 拉取指定用户的所有专辑(所有状态)

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/uers/{owner}/albums]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | page  | Integer | 页数 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | List<Album> | 专辑列表 |

###### 接口示例
> 地址：[https://api.otakuy.com/uers/5c662c8878be861980ca2c29/albums?page=0]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |

> 拉取成功(200)
``` javascript
{
    "message": "共有1张维护专辑",
    "data": [
        {
            "id": "5c6658af78be863730550145",
            "title": "Fantôme",
            "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
            "cover": "https://cover.otakuy.com/default.png"
        }
    ]
}
```

### 20\. 删除专辑

###### 接口功能
> 用户删除专辑

###### 接口说明
> 验证权限

###### URL
> [https://api.otakuy.com/albums/{album_id}]()

###### 支持格式
> JSON

###### 请求方式
> DELETE

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | String | 头像链接 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |

> 删除成功(200)
``` javascript
{
    "message": "删除成功"
}
```
> 非本人专辑(401)
``` javascript
{
    "message": "权限不足"
}
```
### 21\. 按照标签搜索专辑

###### 接口功能
> 按照标签进行模糊搜索

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/search/byTag]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | tag | String | 标签 |
> | page | Integer | 页数 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data.id  | String | 专辑id |
> | data.title | String | 专辑标题 |
> | data.intro | String | 专辑简介 |
> | data.cover | String | 专辑封面 |

###### 接口示例
> 地址：[https://api.otakuy.com/search/byTag?tag=日本&page=0]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |

> 拉取成功(200)
``` javascript
{
    "message": "共有3张专辑",
    "data": [
        {
            "id": "5c6658af78be863730550145",
            "title": "Fantôme",
            "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
            "cover": "https://cover.otakuy.com/default.png"
        },
        {
            "id": "5c5faefa78be864eb0f22802",
            "title": "123456",
            "intro": "十年多之后的今天，『One more time, One more chance』配合新海诚的新电影重新发售特别版。熟悉JPOP的朋友应该知道，这首曲子曾是山崎まさよし主演的电影「月とキャベツ」的主题曲。也是他的代表曲目之一。\n凝结了新海监督想最描绘的东西的作品「秒速5厘米」。\n把现在这里有的“日常”的风景做为舞台，尝试用动画片表现出每天谁都能感觉到现实感。\n正因为如此，新海监督自己很强地认识到做为主题歌的乐曲的重要性。\n新海监督所想象的主题歌——「谁都知道，对现代人们来说有普遍性的乐曲」。\n从过去到现在的许多的流行的歌曲，找寻了适合这个条件的乐曲\n最终走到了山崎まさよし的「One more time, One more chance」面前。\n在新海作品那情感溢出的风景里，配以这伤感却美丽的旋律。影片结束时响起的歌声有种力量让人感动……\n单曲中还收录了天门所编曲的一曲\"雪の駅\"，还有初次音源化的\"弾き語りVer\"。",
            "cover": "https://img3.doubanio.com/view/subject/m/public/s2340104.jpg"
        },
        {
            "id": "5c5f98cc78be860cd830369f",
            "title": "One more time,One more chance 「秒速5センチメートル」Special Edition",
            "intro": "十年多之后的今天，『One more time, One more chance』配合新海诚的新电影重新发售特别版。熟悉JPOP的朋友应该知道，这首曲子曾是山崎まさよし主演的电影「月とキャベツ」的主题曲。也是他的代表曲目之一。\n凝结了新海监督想最描绘的东西的作品「秒速5厘米」。\n把现在这里有的“日常”的风景做为舞台，尝试用动画片表现出每天谁都能感觉到现实感。\n正因为如此，新海监督自己很强地认识到做为主题歌的乐曲的重要性。\n新海监督所想象的主题歌——「谁都知道，对现代人们来说有普遍性的乐曲」。\n从过去到现在的许多的流行的歌曲，找寻了适合这个条件的乐曲\n最终走到了山崎まさよし的「One more time, One more chance」面前。\n在新海作品那情感溢出的风景里，配以这伤感却美丽的旋律。影片结束时响起的歌声有种力量让人感动……\n单曲中还收录了天门所编曲的一曲\"雪の駅\"，还有初次音源化的\"弾き語りVer\"。",
            "cover": "https://img3.doubanio.com/view/subject/m/public/s2340104.jpg"
        }
    ]
}
```

### 22\. 按照标题模糊搜索专辑

###### 接口功能
>  按照标题模糊搜索专辑

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/search/byTitle]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | title | String | 需要校验的邮箱 |
> | page | Integer | 页数 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data.id  | String | 专辑id |
> | data.title | String | 专辑标题 |
> | data.intro | String | 专辑简介 |
> | data.cover | String | 专辑封面 |

###### 接口示例
> 地址：[https://api.otakuy.com/search/byTitle?page=0&title=1234]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |

> 上传成功(200)
``` javascript
{
    "message": "共有1",
    "data": [
        {
            "id": "5c5faefa78be864eb0f22802",
            "title": "123456",
            "intro": "十年多之后的今天，『One more time, One more chance』配合新海诚的新电影重新发售特别版。熟悉JPOP的朋友应该知道，这首曲子曾是山崎まさよし主演的电影「月とキャベツ」的主题曲。也是他的代表曲目之一。\n凝结了新海监督想最描绘的东西的作品「秒速5厘米」。\n把现在这里有的“日常”的风景做为舞台，尝试用动画片表现出每天谁都能感觉到现实感。\n正因为如此，新海监督自己很强地认识到做为主题歌的乐曲的重要性。\n新海监督所想象的主题歌——「谁都知道，对现代人们来说有普遍性的乐曲」。\n从过去到现在的许多的流行的歌曲，找寻了适合这个条件的乐曲\n最终走到了山崎まさよし的「One more time, One more chance」面前。\n在新海作品那情感溢出的风景里，配以这伤感却美丽的旋律。影片结束时响起的歌声有种力量让人感动……\n单曲中还收录了天门所编曲的一曲\"雪の駅\"，还有初次音源化的\"弾き語りVer\"。",
            "cover": "https://img3.doubanio.com/view/subject/m/public/s2340104.jpg"
        }
    ]
}
```

### 23. 提交评论

###### 接口功能
> 提交评论

###### 接口说明
> 最初始pid为专辑id

###### URL
> [https://api.otakuy.com/albums/{album_id}/comments]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
    "album": "5c6658af78be863730550145",
    "pid": "5c6658af78be863730550145",
    "content": "2333"
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Comment | 评论详情 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145/comments]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
{
    "album": "5c6658af78be863730550145",
    "pid": "5c6658af78be863730550145",
    "content": "2333"
}
```
> 评论成功(200)
``` javascript
{
    "message": "评论提交成功",
    "data": {
        "album": "5c6658af78be863730550145",
        "pid": "5c6658af78be863730550145",
        "id": "5c66a91778be8621500ec1d1",
        "user_id": "5c662c8878be861980ca2c29",
        "user_cover": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
        "user_username": "生蚝QAQ",
        "content": "2333",
        "createTime": "2019-02-15T11:57:11.489+0000"
    }
}
```

### 24\. 拉取专辑下所有评论

###### 接口功能
> 拉取专辑下所有评论

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/albums/{album_id}/comments]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | page  | Integer | 页数 |

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | List<Comment> | 评论列表 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145/comments?page=0]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "该专辑共有1条评论",
    "data": [
        {
            "album": "5c6658af78be863730550145",
            "pid": "5c6658af78be863730550145",
            "id": "5c66a91778be8621500ec1d1",
            "user_id": "5c662c8878be861980ca2c29",
            "user_cover": "https://img.otakuy.com/5c662c8878be861980ca2c29.png",
            "user_username": "生蚝QAQ",
            "content": "2333",
            "createTime": "2019-02-15T11:57:11.489+0000"
        }
    ]
}
```
### 25\. 提交修改请求

###### 接口功能
> 用户提交对应(活跃状态)专辑对应模块的修改请求(请求队列小于3)

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/albums/{album_id}/revisions]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> raw
``` javascript
{
  "username": "生蚝QAQ",
  "password": "2333",
  "email": "349135289789457457852@qq.com",
  "avatar": "https://img.otakuy.com/default.png",
  "intro": "233333"
}
```

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 请求体
``` javascript
//图片文件
```
> 提交成功(200)
``` javascript
{
    "message": "提交修改成功,等待维护者审核"
}
```
> 给自己的专辑提交修改(400)
``` javascript
{
    "message": "不能对自己的专辑提交修改请求"
}
```
> 更改等待队列已满(400)
``` javascript
{
    "message": "等待修改队列已满"
}
```
> 专辑不存在(400)
``` javascript
{
    "message": "专辑不存在或未审核通过"
}
```

### 26\. 查看专辑下所有等待修改队列

###### 接口功能
> 查看专辑下所有等待修改队列

###### 接口说明
> 针对待审核修改

###### URL
> [https://api.otakuy.com/albums/{album_id}/revisions]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | List<Revision> | 修改列表 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145/revisions]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "共3条等待审核的修改提交",
    "data": [
        {
            "id": "5c66b4e178be8621500ec1d3",
            "album": "5c6658af78be863730550145",
            "committer": "5c4fc2ab1bdab24f22edfc70",
            "modificationPoint": "title",
            "content": "233333",
            "status": "block"
        },
        {
            "id": "5c66b4fe78be8621500ec1d5",
            "album": "5c6658af78be863730550145",
            "committer": "5c4fc2ab1bdab24f22edfc70",
            "modificationPoint": "title",
            "content": "233333",
            "status": "block"
        },
        {
            "id": "5c66b50178be8621500ec1d7",
            "album": "5c6658af78be863730550145",
            "committer": "5c4fc2ab1bdab24f22edfc70",
            "modificationPoint": "title",
            "content": "233333",
            "status": "block"
        }
    ]
}
```
### 27. 维护者应用修改

###### 接口功能
> 维护者应用修改

###### 接口说明
> 给提交者加分并发送消息给提交者

###### URL
> [https://api.otakuy.com/albums/{album_id}/revisions/{revision_id}]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Album | 应用修改后的专辑 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 应用成功(200)
``` javascript
{
    "message": "应用修改成功",
    "data": {
        "id": "5c6658af78be863730550145",
        "title": "233333",
        "tracks": [
            {
                "title": "1:道",
                "preview": ""
            },
            {
                "title": "2:俺の彼女",
                "preview": ""
            },
            {
                "title": "3:花束を君に",
                "preview": ""
            },
            {
                "title": "NHK連続テレビ小説「とと姉ちゃん」主題歌",
                "preview": ""
            },
            {
                "title": "4:二時間だけのバカンス",
                "preview": ""
            },
            {
                "title": "5:人魚",
                "preview": ""
            },
            {
                "title": "6:ともだち",
                "preview": ""
            },
            {
                "title": "7:真夏の通り雨",
                "preview": ""
            },
            {
                "title": "日本テレビ「NEWSZERO」テーマ曲",
                "preview": ""
            },
            {
                "title": "8:荒野の狼",
                "preview": ""
            },
            {
                "title": "9:忘却",
                "preview": ""
            },
            {
                "title": "10:人生最高の日",
                "preview": ""
            },
            {
                "title": "11:桜流し",
                "preview": ""
            },
            {
                "title": "「ヱヴァンゲリヲン新劇場版：Q」テーマソング",
                "preview": ""
            }
        ],
        "artists": [
            {
                "name": "宇多田ヒカル"
            }
        ],
        "pubdate": "2016-09-28",
        "publisher": "Universal Music =music=",
        "genres": "流行",
        "version": "专辑",
        "tags": [
            {
                "name": "日本"
            },
            {
                "name": "女声"
            },
            {
                "name": "J-pop"
            },
            {
                "name": "专辑"
            },
            {
                "name": "流行"
            },
            {
                "name": "R&B"
            },
            {
                "name": "日语"
            }
        ],
        "intro": "宇多田ヒカル 8年ぶりとなるオリジナル・フルアルバムが遂に完成！\n「誰もいない世界へ　私を連れて行って」\n2008年にリリースされたアルバム「HEART STATION」のラストソングはこの歌詞で幕を閉じました。\nあれから8年。一時活動休止期間を経た宇多田ヒカルは、自身6枚目となるオリジナル・フルアルバムを携えていよいよ本格的に活動再開を果たします。\n本年4月にデジタルシングルとして同時配信し、各配信ランキングの記録を更新、国内外107冠を獲得した「花束を君に」（NHK連続テレビ小説「とと姉ちゃん」主題歌）、「真夏の通り雨」（日本テレビ「NEWS ZERO」テーマ曲）、そして当時「人間活動」中の2012年11月に突然配信リリースされたのも記憶に新しい「桜流し」（「ヱヴァンゲリヲン新劇場版：Q」テーマソング）、この3曲はついに初CD化として収録され、以外はすべて新たに書き下ろされた作品です。\nよろこびとかなしみをひとつにしたあの声は、彼女にしか書けない言葉と旋律で物語を紡ぎます。\nポップミュージックの生命力、その瑞々しさは色褪せず、その豊かさは汲めども尽きない、ということ。\nこのアルバムにはそれが満ち溢れています。\nまさに待望の、としか言いようのない、新たなマスターピースの完成です。",
        "cover": "https://cover.otakuy.com/default.png",
        "douban_url": "https://music.douban.com/subject/26832501/",
        "rating": 0,
        "rating_count": 0,
        "owner": "5c662c8878be861980ca2c29",
        "downloadRes": {
            "permission": 20,
            "url": "https://pan.baidu.com",
            "password": "1234",
            "unzipKey": null
        },
        "status": "active",
        "isRecommend": false
    }
}
```
> 非本人专辑(401)
``` javascript
{
    "message": "权限不足"
}
```

### 28. 维护者拒绝修改

###### 接口功能
> 维护者拒绝修改

###### 接口说明
> 将修改置为并发送消息给提交者

###### URL
> [https://api.otakuy.com/albums/{album_id}/revisions/{revision_id}]()

###### 支持格式
> JSON

###### 请求方式
> DELETE

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |

###### 接口示例
> 地址：[https://api.otakuy.com/albums/5c6658af78be863730550145/revisions/5c66b4fe78be8621500ec1d5]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 应用成功(200)
``` javascript
{
    "message": "拒绝修改成功"
}
```
> 非本人专辑(401)
``` javascript
{
    "message": "权限不足"
}
```

### 29. 打赏

###### 接口功能
> 用户打赏

###### 接口说明
> 将校验是否有足够star,消耗用户star,给被打赏者加分并发送消息

###### URL
> [https://api.otakuy.com/albums/{album_id}/star]()

###### 支持格式
> JSON

###### 请求方式
> POST

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 应用成功(200)
``` javascript
{
    "message": "打赏成功"
}
```
> 自己给自己star或拥有star数量不足以打赏(400)
``` javascript
{
    "message": "拥有star数不足or自己给自己打赏是不行的哦"
}
```
> 被打赏用户不存在(400)
``` javascript
{
    "message": "被打赏用户不存在"
}
```
### 30. 获取专辑打赏列表

###### 接口功能
> 获取专辑打赏列表

###### 接口说明
> 

###### URL
> [https://api.otakuy.com/albums/{album_id}/star]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Star | Star列表 |

###### 接口示例
> 地址：[https://api.otakuy.com/users/avatars]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取成功(200)
``` javascript
{
    "message": "拉取成功",
    "data": [
        {
            "id": "5c63cf5278be862cd494df9f",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63cfcc78be863eb4d59ac8",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63d19978be8615442d8862",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63d19a78be8615442d8863",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63d1e378be8615442d8864",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b7be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63d33378be860d20bc6452",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        },
        {
            "id": "5c63d7a378be8641e063dbbd",
            "starFrom": "5c4fc2ab1bdab24f22edfc70",
            "starTo": "5c613a6b78be8623e4f16499",
            "starAt": "5c62a03678be860d703581b0",
            "num": 10
        }
    ]
}
```
> 专辑不存在(400)
``` javascript
{
    "message": "专辑不存在"
}
```

### 31. 是否有新消息

###### 接口功能
> 查看是否有未读消息

###### 接口说明
> 用于首页未读消息提醒

###### URL
> [https://api.otakuy.com/notifications/read]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> 

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | Boolean | 是否有未读消息 |

###### 接口示例
> 地址：[https://api.otakuy.com/notifications/read]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 有未读消息(200)
``` javascript
{
    "message": "有未读消息",
    "data": true
}
```
> 无未读消息(200)
``` javascript
{
    "message": "无未读消息",
    "data": false
}
```

### 32. 获取消息列表

###### 接口功能
> 获取消息列表

###### 接口说明
> 可以选择获取已读或未读,获取未读列表后未读消息将转为已读

###### URL
> [https://api.otakuy.com/notifications]()

###### 支持格式
> JSON

###### 请求方式
> GET

###### 请求头
> | 键  | 类型 | 说明             |
> | :-------  | :----- | ---------------- |
> | Authorization | String | token |

###### 请求参数
> | 参数   | 类型   | 说明             |
> | :-------  | :----- | ---------------- |
> | isRead | Boolean | 已读或未读 |

###### 请求体
> 
> 

###### 返回字段
> | 返回字段 | 字段类型 | 说明             |
> | :------- | :------- | :--------------- |
> | message  | String  | 返回结果文字说明 |
> | data     | List<Notification> | 消息列表 |

###### 接口示例
> 地址：[https://api.otakuy.com/notifications?isRead=false]() 
> 请求头
>
> | 键  | 值 |
> | :-------  | :----- |
> | Authorization | Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpZCI6IjVjNjYyYzg4NzhiZTg2MTk4MGNhMmMyOSIsInN1YiI6IueUn-ianVFBUSIsImlhdCI6MTU1MDIwMDM5OSwiZXhwIjoxNTUwMjI5MTk5fQ.WEId75Hl-DxeVoK6_9KZAeZ3r5oLSuqe8DNNgPmkd13JukcE7dat97oRq2yjOobS29hx0BFR19ifWbxu_byY4A |
> 拉取一度列表(200)
``` javascript
{
    "message": "拉取成功",
    "data": [
        {
            "id": "5c66b4e178be8621500ec1d4",
            "owner": "5c662c8878be861980ca2c29",
            "albumId": "5c6658af78be863730550145",
            "isRead": false,
            "creatTime": "2019-02-15T12:47:29.948+0000",
            "content": "您维护的专辑收到一条修改请求",
            "url": "url"
        },
        {
            "id": "5c66b4fe78be8621500ec1d6",
            "owner": "5c662c8878be861980ca2c29",
            "albumId": "5c6658af78be863730550145",
            "isRead": false,
            "creatTime": "2019-02-15T12:47:58.665+0000",
            "content": "您维护的专辑收到一条修改请求",
            "url": "url"
        },
        {
            "id": "5c66b50178be8621500ec1d8",
            "owner": "5c662c8878be861980ca2c29",
            "albumId": "5c6658af78be863730550145",
            "isRead": false,
            "creatTime": "2019-02-15T12:48:01.725+0000",
            "content": "您维护的专辑收到一条修改请求",
            "url": "url"
        },
        {
            "id": "5c66c76978be8639e8f4e9f9",
            "owner": "5c662c8878be861980ca2c29",
            "albumId": "5c6658af78be863730550145",
            "isRead": false,
            "creatTime": "2019-02-15T14:06:33.043+0000",
            "content": "您维护的专辑收到打赏",
            "url": "url"
        }
    ]
}
```
