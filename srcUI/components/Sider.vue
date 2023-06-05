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
    <a-menu theme="dark" mode="inline">
        <a-sub-menu key="sub2">
            <template #icon></template>
            <template #title>
                <folder-open-two-tone />
                文件夹
            </template>
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
            <a-sub-menu key="sub3" title="Submenu">
                <a-menu-item key="7">Option 7</a-menu-item>
                <a-menu-item key="8">Option 8</a-menu-item>
            </a-sub-menu>
        </a-sub-menu>
    </a-menu>
</template>
<script setup lang="ts">
    import {
        FolderOpenTwoTone,
        PictureTwoTone,
        VideoCameraTwoTone,
    } from '@ant-design/icons-vue'
    import {
        ref,
        reactive
    } from 'vue'
    const selectedKeys = ref < string[1] > (["1"])
</script>
<style>

</style>