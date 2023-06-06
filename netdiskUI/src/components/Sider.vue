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
        <a-upload-dragger>
            <p class="ant-upload-drag-icon">
                <inbox-outlined></inbox-outlined>
            </p>
            <p class="ant-upload-text">上传文件</p>
            <p class="ant-upload-hint">
                也可以选择拖拽上传
            </p>
        </a-upload-dragger>
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
    import COS from 'cos-js-sdk-v5';
    import token from '../assets/token'
    const selectedKeys = ref < string[1] > (["1"])
    const CosData = token.getCosData()
    const cos = new COS({
        SecretId: CosData.SecretId,
        SecretKey: CosData.SecretKey
    })
</script>
<style>

</style>