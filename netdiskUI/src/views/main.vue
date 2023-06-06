<template>
    <div :style="{ padding: '20px', background: '#fff',height:'560px' , textAlign: 'center',position:'relative' }">
        <a-list :grid="{ gutter: 16, column: 8 }" :data-source="listData">
            <template #renderItem="{ item }">
                <a-list-item>
                    <a-dropdown :trigger="['contextmenu']">
                        <a-card hoverable style="width: 120px">
                            <template #cover>
                                <img @dblclick="open(item)" :style="{padding: '5px'}" :width="110" :higth="110"
                                    :src="item.image"
                                    fallback="data:image/png;base64,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" />
                            </template>
                            <template #actions>
                                <a-typography-text strong v-if="item.display" @dblclick="double_click(item)">
                                    {{item.folderName}}
                                </a-typography-text>
                                <a-input :ref="(el)=>in_ref(el,item.index)" @keyup.enter="$event.target.blur()"
                                    @blur="Undisplay(item)" size="small" :bordered="false"
                                    v-model:value="item.folderName" v-if="!item.display" />
                            </template>
                        </a-card>
                        <template #overlay>
                            <a-menu>
                                <a-menu-item key="1" @click="open(item)">
                                    <folder-open-filled />
                                    打开</a-menu-item>
                                <a-menu-item key="2">
                                    <eye-filled />
                                    查看</a-menu-item>
                                <a-menu-item key="3" @click="double_click(item)">
                                    <edit-filled />
                                    重命名</a-menu-item>
                                <a-menu-item key="4" @click="newCreate(item)">
                                    <folder-add-filled />
                                    新建文件夹</a-menu-item>
                                <a-menu-item />
                                <a-menu-item key="5" @click="deleteFolder(item)">
                                    <delete-filled />
                                    删除</a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>
                </a-list-item>
            </template>
        </a-list>
    </div>
</template>
<script setup lang="ts">
    import {
        FolderOpenFilled,
        EditFilled,
        DeleteFilled,
        ToolFilled,
        EyeFilled,
        FolderAddFilled,
        FolderOpenTwoTone,
        PictureTwoTone,
        VideoCameraTwoTone,
        UpCircleOutlined,
        FolderOpenOutlined,
    } from '@ant-design/icons-vue'
    import {
        defineComponent,
        ref,
        reactive,
        watch,
        nextTick,
        onMounted,
        computed
    } from 'vue'
    import request from '../until/request'
    import {
        useStore
    } from 'vuex';
    import {
        message
    } from 'ant-design-vue'

    // 卡片信息
    interface CardData {
        display: boolean,
            fId: string,
            folderName: string,
            image: string,
            masterFolderId: string,
            createTime: string,
            updateTime: string,
            volume: string,
            isDelete: int,
    }
    // 文本框显示判定
    const display = ref(false)
    // 文件夹列表
    const listData = reactive < CardData[20] > ([])
    // 存储组件参数，用于获取焦点
    const refin = ref({})
    // 获取共享变量的参数
    const store = useStore();
    // 计算数组长度
    const arrayLength = computed(() => store.state.CardData.length)

    // 监听数组长度变化
    watch(arrayLength, (newQuestion, oldQuestion) => {
        // 视图更新
        addData()
        let isor = false
        for (let i = 0; i < store.state.CardData.length; i++) {
            // 判断这次的长度变化的原因是新增文件夹，还是面包屑导航出现的文件夹数量变化
            if (!store.state.CardData[i].folderName || store.state.CardData[i].folderName.trim().length === 0) {
                isor = true
            }
        }
        if (newQuestion > oldQuestion && isor) {
            nextTick(() => {
                let key = ''
                for (let i = 0; i < store.state.CardData.length; i++) {
                    if (!store.state.CardData[i].folderName || store.state.CardData[i].folderName.trim()
                        .length === 0) {
                        key = i
                    }
                }
                // 文本框获取焦点
                refin[key].focus()
            })
        }
    })

    // 监听共享变量中设置的父文件夹id
    watch(() => store.state.masterId, (newQuestion, oldQuestion) => {
        let form = {
            fId: store.state.masterId
        }
        if (!store.state.masterId || store.state.masterId.trim().length === 0) {
            getData()
        } else {
            getData(form)
        }

    })

    // 生命函数：组件挂载完成后调用
    onMounted(() => {
        getData()
    })

    // 打开文件夹
    function open(params) {
        // console.log(params);
        getData(params)
    }

    // 新建文件夹
    function newCreate(params) {
        let isor = true
        for (let i = 0; i < listData.length; i++) {
            // 判断是否存在新建的文件夹（即判断是否存在空命名的文件夹）
            if (!listData[i].folderName || listData[i].folderName.trim().length === 0) {
                isor = false
            }
        }
        if (isor) {
            const form = {
                image: "https://huyi-1312710090.cos.ap-guangzhou.myqcloud.com/image%2F%E6%96%87%E4%BB%B6%E5%A4%B9.jpeg",
                masterFolderId: store.state.masterId,
            }
            request.post("/create_folder", form).then(res => {
                res.data.display = false
                store.commit('addData', res.data)
            })
        }

    }

    // 删除文件夹
    function deleteFolder(res) {
        request.post("/delete_folder/" + res.fId).then(r => {
            if (r.code === 0) {
                message.success('删除成功！')
                getData()
            }
        })
    }

    // 列表更新
    function addData(params) {
        // 已有数据清空
        listData.splice(0, listData.length)
        // 遍历共享变量中的文件夹列表数据
        for (let i = 0; i < store.state.CardData.length; i++) {
            listData.push(store.state.CardData[i])
            if (listData[i].display === undefined || listData[i].display === null) {
                listData[i].display = true
            }
            // 用于绑定组件参数下标
            listData[i].index = i
        }
    }

    // ref绑定组件参数的函数
    function in_ref(el, key) {
        refin[key] = el
    }

    //双击时
    async function double_click(params) {
        params.display = false
        await nextTick(() => {
            refin[params.index].focus()
        })
    }

    // 回车 or 失去焦点
    function Undisplay(params) {
        let isor = true
        if (!params.folderName || params.folderName.trim().length === 0) {
            isor = false
            message.error('名称不能为空！');
            refin[params.index].focus()
        } else {
            params.display = true
            for (let i = 0; i < listData.length; i++) {
                if (params.folderName === listData[i].folderName && params.fId != listData[i].fId) {
                    isor = false
                    message.error('名称不能相同！')
                    params.display = false
                    refin[params.index].focus()
                }
            }
        }
        if (isor) {
            const form = {
                fid: params.fId,
                folderName: params.folderName,
                image: params.image,
                masterFolderId: params.masterFolderId,
                createTime: params.createTime,
                updateTime: params.updateTime,
                volume: params.volume,
                isDelete: params.isDelete,
            }
            request.put("/update_folder", form).then(res => {
                if (res.code === 0) {
                    for (let i = 0; i < store.state.CardData.length; i++) {
                        if (store.state.CardData[i].fId === res.data.fId) {
                            store.state.CardData[i].updateTime = res.data.updateTime
                        }
                    }
                    console.log(store.state.CardData);
                } else {
                    message.error(res.message)
                    getData()
                }
            })

        }
    }

    // 连接后端获取列表
    function getData(res) {
        // 若为根文件夹
        if (res === undefined) {
            request.get("/get_folder_list", {
                params: {
                    fId: ''
                }
            }).then(r => {
                store.commit('coverData', r.data)
                store.state.masterId = r.data[0].masterFolderId
                addData()
            })
        } else {
            request.get("/get_folder_list", {
                params: {
                    fId: res.fId
                }
            }).then(r => {
                store.commit('coverData', r.data)
                console.log(r.data);
                if (!r.data || r.data.length === 0) {
                    store.state.masterId = res.fId
                } else {
                    store.state.masterId = r.data[0].masterFolderId
                }
                addData()
            })
        }
    }
</script>
<style>

</style>