<template>
    <a-menu v-model:selectedKeys="selectedKeys" :style="{height:'150px' ,padding: '0 0 50px 0'}" :theme="light"
        mode="inline">
        <a-menu-item key="1">
            <folder-open-two-tone />
            <span class="nav-text">主页面</span>
        </a-menu-item>
        <a-menu-item key="2">
            <video-camera-two-tone />
            <span class="nav-text">视频</span>
        </a-menu-item>
        <a-menu-item key="3">
            <picture-two-tone />
            <span class="nav-text">图片</span>
        </a-menu-item>
    </a-menu>
    <div style="height: 150px;">
        <a-upload-dragger :beforeUpload="before">
            <p class="ant-upload-drag-icon">
                <inbox-outlined></inbox-outlined>
            </p>
            <p class="ant-upload-text">上传文件</p>
            <p class="ant-upload-hint">
                也可以选择拖拽上传
            </p>
        </a-upload-dragger>
    </div>
    <a-progress :show-info="false" :stroke-color="{'0%': '#108ee9','100%': '#87d068',}" :percent="percent" />
    <div>
        <video :src="videoUrl" controls autoplay height="112.5" width="200" style="object-fit:contain">
        </video>
    </div>
</template>
<script setup lang="ts">
    import {
        InboxOutlined,
        FolderOpenTwoTone,
        PictureTwoTone,
        VideoCameraTwoTone,
    } from '@ant-design/icons-vue'
    import {
        ref,
        reactive,
        onMounted
    } from 'vue'
    import {
        message,
        Upload
    } from 'ant-design-vue'
    import COS from 'cos-js-sdk-v5';
    import token from '../until/token'
    const selectedKeys = ref < string[1] > (["1"])
    const percent = ref(0)
    const videoUrl = ref("")

    const CosData = token.getCosData()
    // 定义cos中的秘钥
    const cos = new COS({
        SecretId: CosData.SecretId,
        SecretKey: CosData.SecretKey
    })

    function before(params) {
        upload(params)
        return Upload.LIST_IGNORE
    }

    function upload(params) {
        cos.putObject({
            Bucket: CosData.Bucket,
            /* 填入您自己的存储桶，必须字段 */
            Region: CosData.Region,
            /* 存储桶所在地域，例如ap-beijing，必须字段 */
            Key: params.name,
            /* 存储在桶里的对象键（例如1.jpg，a/b/test.txt），必须字段 */
            StorageClass: CosData.StorageClass,
            Body: params, // 上传文件对象
            onProgress: (progressData) => {
                percent.value = progressData.percent * 100
            }
        }, (err, data) => {
            if (!err) {
                percent.value = 0
                message.success('上传成功！');
                console.log(params.type);
                if (params.type.indexOf("image") != -1) {
                    addImage(data)
                } else if (params.type.indexOf("video") != -1) {
                    videoUrl.value = 'http://' + data.Location
                    console.log(videoUrl);
                }
                // 手动处理 fileList
                // this.form.image = 'http://' + data.Location
                // this.savedish()
                // this.fileList[0].status = 'success'
            }
        })
    }

    // 上传图片调用
    function addImage(params) {
        console.log("图片！");
    }
</script>
<style>
</style>