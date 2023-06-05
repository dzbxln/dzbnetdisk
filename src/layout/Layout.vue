<template>
    <a-layout has-sider>
        <a-layout-sider :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0 }">
            <Sider></Sider>
        </a-layout-sider>
        <a-layout :style="{ marginLeft: '200px' }">
            <a-layout-header :style="{ background: '#fff', padding: 0 }">
                <a-menu mode="horizontal" v-model:selectedKeys="model">
                    <a-menu-item key="1">
                        <template #icon>
                            <up-circle-outlined />
                        </template>
                        上传文件
                    </a-menu-item>
                    <a-menu-item key="2" @click="newCreate">
                        <template #icon>
                            <folder-open-outlined />
                        </template>
                        新建文件夹
                    </a-menu-item>
                </a-menu>
            </a-layout-header>
            <a-layout-content :style="{ margin: '5px 16px 0', overflow: 'initial' }">
                <div>
                    <a-breadcrumb>
                        <a-breadcrumb-item><a @click="updateMasterId('根目录')">根目录</a></a-breadcrumb-item>
                        <a-breadcrumb-item v-for="item in breadcrumb">
                            <a @click="updateMasterId(item)">{{item.folderName}}</a>
                        </a-breadcrumb-item>
                    </a-breadcrumb>
                </div>
                <router-view />
            </a-layout-content>
            <a-layout-footer :style="{ textAlign: 'center' }">
                DengZebin netdisk @2023 by play
            </a-layout-footer>
        </a-layout>
    </a-layout>
</template>
<script setup lang="ts">
    import Sider from '@/components/Sider.vue'
    import {
        ref,
        reactive,
        watch
    } from 'vue'
    import {
        useStore
    } from 'vuex';
    import {
        UpCircleOutlined,
        FolderOpenOutlined,
    } from '@ant-design/icons-vue'
    import request from '../until/request'
    const model = ref < string[10] > (["0"])
    const store = useStore();
    const breadcrumb = ref([])

    // 用于实现取消导航栏选中
    watch(model, (newQuestion, oldQuestion) => {
        model.value[0] = "0"
    })

    watch(() => store.state.masterId, (newQuestion, oldQuestion) => {
        getData(newQuestion)
    })

    function newCreate(params) {
        const i = store.state.CardData.length
        const s = {
            src: "https://huyi-1312710090.cos.ap-guangzhou.myqcloud.com/image%2F%E6%96%87%E4%BB%B6%E5%A4%B9.jpeg",
            display: false,
            text: "",
            key: "",
            index: i
        }
        store.commit('addData', s)
    }

    // 修改父文件夹id
    function updateMasterId(params) {
        if (params === "根目录") {
            store.state.masterId = ""
        } else {
            store.state.masterId = params.folderId
        }
    }

    // 获取数据
    function getData(params) {
        if (params != undefined) {
            request.get("/get_breadcrumb", {
                params: {
                    masterId: params
                }
            }).then(res => {
                breadcrumb.value = res.data
            })
        }
    }
</script>
<style>
    #components-layout-demo-fixed-sider .logo {
        height: 32px;
        background: rgba(255, 255, 255, 0.2);
        margin: 16px;
    }

    .site-layout .site-layout-background {
        background: #fff;
    }

    [data-theme='dark'] .site-layout .site-layout-background {
        background: #141414;
    }
</style>