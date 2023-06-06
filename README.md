# 简易网盘项目

#### 配置
！进入netdiskUI->src->assets
新建ts文件：token.ts
复制以下代码：

```
interface Data {
    Bucket:String,
    Region:String,
    StorageClass:String,
    SecretId:String,
    SecretKey:String,
}

const CosData:Data = {
    Bucket: '', /* 填入自己的存储桶，必须字段*/
    Region: '', /* 存储桶所在地域，例如ap-beijing，必须字段 */
    StorageClass: 'STANDARD', /* 默认 */
    SecretId: '', // 腾讯云秘钥ID
    SecretKey: '' // 腾讯云秘钥Key
}

export default{
    getCosData:function(){
        return CosData;
    }
}
```

#### 启动前端
`cd netdiskUI`

`npm install`

`npm run serve`

#### 启动后端

路径：dzbnetdisk->src->main->java->NetdiskApplication启动类