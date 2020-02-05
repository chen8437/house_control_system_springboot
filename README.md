# house_control_system_springboot
房屋管理系统的后台

# 前言
由于时间关系和系统的使用需求 , 本项目只是大体实现了功能, 并没有完整的按照实际企业业务需要完成功能的实现.所以整体项目只适用于交流和学习或者毕设这些用途.

# 启动步骤
* 找到sql文件 创建数据库 创建表 (`/src/resources/sql/house_control_system.sql`)
* 启动redis 端口开放为6379 (默认为本地)
* 后端代码导入到 idea
* properties文件配置mybatis数据源 redis默认密码
* 修改`resources/logback-spring.xml`文件的日志输出路径
* 运行`CloudApplication`
* 成功
## 建议
建议第一次运行项目注解掉`quartz/GetDataJob` 里面的定时任务

# 页面预览
[房屋中介管理系统](http://housepage.autumnsun.top "点击跳转") 
`http://housepage.autumnsun.top`
`应该还能用一段时间 不知道什么时候会挂掉`

[前端连接](http://housepage.autumnsun.top "https://github.com/qq841557053/house_control_system_vue") 
`https://github.com/qq841557053/house_control_system_vue`

# 项目采用的技术
* 项目整体采用前后端分离, 前端整体使用Vue.js框架, elementui组件, 使用模板 vue-element-admin,
* 后端使用springboot为框架, 集成springmvc , spring-data-mybatis, spring-data-redis , spring-data-quartz logbac日志输出 等组件
* 数据库使用 mysql8.0,
* 缓存中间件使用redis,

# 未解决的问题
* 未解决的问题
* 高并发 
* 支付流程 
* 支付操作流程 
* 订单具体实现流程
* token真实校验
* 按钮级别的权限校验
* 等等

# 功能模块

* 登录功能
* 登出功能

* 首页展示
    * 个人信息展示
    * 最火房源
    * 最近成交金额

* 用户管理
    * 用户新增
    * 用户查看
    * 用户修改信息
    * 用户删除
* 房源查看
    * 求租房源
    * 在租房源
* 发布订单
    * 我是房东
    * 我是房客
* 我的
    * 我买的
    * 我卖的
    * 售后单
* 售后服务

# 接口规定

## 状态码
| 数值 | 状态 | 备注 |
| ---- | ---- | ------------- |
| 1 | 成功| 无|
| 0 | 失败 |网络问题 或 服务器宕机 或 出现异常|
| -1 | 登陆过期 | redis中的token失效|
|-2| 无权限|目前权限没有涉及到接口级所以没用到|

## 请求规则
前端默认把token加到header里面 后端制作intercept 进行过滤
所有的前后交互数据格式皆为json

## 接口域名
开发环境BAS_EURL = http://localhost:1314
生产环境BAS_EURL = http://house.autumnsun.top

# 用户角色规定:
|中文名称	|权限	|说明|
| ----- | ------| ------|
|管理员|	admin|	可以为多个|
|普通用户|	user|	|
|房东	|seller	||
|超级管理员	|yechrom	|有且只存在一个账号|


# 单号生成规则
统一订单前缀(5位) + 业务名称(4位) + 13位时间戳(13位) + 8位随机数(8位) + -(2位)

例如
* 出租: order-sell-157966780323412345678
* 求租: order-buyy-157966780323412345678
* 售后: order-ques-157966780323412345678

# 路由权限规定
下表表示每个页面对应的角色

|模块	|页面/路由	|角色|
|------|------|------|
|首页展示	|首页展示|	user/seller/admin/yechrom|
|用户管理	|用户新增|	admin/yechrom|
||	用户查看	|admin/yechrom|
|房源查看	|在租房源	|user/seller/yechrom|
| |求租房源	|user/seller/yechrom|
|发布订单	我是房东	|seller/yechrom|
| |我是顾客	|user/yechrom|
|售后服务	|提交售后单 |	user/yechrom|
|我的	|我买的|	user/yechrom|
| |我卖的	|seller/yechrom|
| |问题单|	admin/yechrom|

# 订单状态
## 出租房源订单状态
|状态码	|状态	|备注|
|-----|-----|----|
|1	|发布|	刚发布的订单状态|
|2	|完成|	|
|0	|取消|	取消了单|
 
## 出租房源订单状态
|状态码	|状态	|备注|
|-----|-----|----|
|1	|发布|	刚发布的订单状态|
|2	|完成	| |
|0|	取消|	取消了单|

##出租房源订单状态
|状态码|	状态|	备注|
|-----|-----|----|
|1	|发布	|刚发布的订单状态|
|2	|处理中	| | 
|3	|完成	| |
|0	|撤销	|取消了单|

# 删除规定
由于业务要求 , 不会给数据库角色分发删除权限 , 故 不允许对数据进行物理删除 , 只需对表中的is_delete 进行赋值即可
所以每次查询的时候需要在查询语句后面加上判断 , where is_delete = 0

# 首页生成数据redis key命名规则
house-control-system-data-dashboard
