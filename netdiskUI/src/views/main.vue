<template>
    <div class="my-container" :style="{ textAlign:'center' }">
        <a-list :grid="{ gutter: 40, column: 8 }" :data-source="listData">
            <template #renderItem="{ item }">
                <a-list-item>
                    <a-dropdown v-if="item.fileType == undefined" :trigger="['contextmenu']">
                        <a-card hoverable style="width: 120px">
                            <template #cover>
                                <img @dblclick="open(item)" :style="{padding: '5px'}" :width="110" :higth="110"
                                    :src="item.image"
                                    fallback="data:image/png;base64,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" />
                            </template>
                            <template #actions>
                                <a-typography-text :style="{width: '100px' }" :ellipsis="true" strong
                                    v-if="item.display" @dblclick="double_click(item)">
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
                                <a-menu-item key="2" @click="double_click(item)">
                                    <edit-filled />
                                    重命名</a-menu-item>
                                <a-menu-item key="3" @click="newCreate(item)">
                                    <folder-add-filled />
                                    新建文件夹</a-menu-item>
                                <a-menu-item />
                                <a-menu-item key="4" @click="deleteFolder(item)">
                                    <delete-filled />
                                    删除</a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>
                    <a-dropdown v-else :trigger="['contextmenu']">
                        <a-card hoverable style="width: 120px">
                            <template #cover>
                                <img :style="{padding: '5px'}" class="fixed-height-img" :src="item.fileImage"
                                    fallback="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMIAAADDCAYAAADQvc6UAAABRWlDQ1BJQ0MgUHJvZmlsZQAAKJFjYGASSSwoyGFhYGDIzSspCnJ3UoiIjFJgf8LAwSDCIMogwMCcmFxc4BgQ4ANUwgCjUcG3awyMIPqyLsis7PPOq3QdDFcvjV3jOD1boQVTPQrgSkktTgbSf4A4LbmgqISBgTEFyFYuLykAsTuAbJEioKOA7DkgdjqEvQHEToKwj4DVhAQ5A9k3gGyB5IxEoBmML4BsnSQk8XQkNtReEOBxcfXxUQg1Mjc0dyHgXNJBSWpFCYh2zi+oLMpMzyhRcASGUqqCZ16yno6CkYGRAQMDKMwhqj/fAIcloxgHQqxAjIHBEugw5sUIsSQpBobtQPdLciLEVJYzMPBHMDBsayhILEqEO4DxG0txmrERhM29nYGBddr//5/DGRjYNRkY/l7////39v///y4Dmn+LgeHANwDrkl1AuO+pmgAAADhlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAAqACAAQAAAABAAAAwqADAAQAAAABAAAAwwAAAAD9b/HnAAAHlklEQVR4Ae3dP3PTWBSGcbGzM6GCKqlIBRV0dHRJFarQ0eUT8LH4BnRU0NHR0UEFVdIlFRV7TzRksomPY8uykTk/zewQfKw/9znv4yvJynLv4uLiV2dBoDiBf4qP3/ARuCRABEFAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghggQAQZQKAnYEaQBAQaASKIAQJEkAEEegJmBElAoBEgghgg0Aj8i0JO4OzsrPv69Wv+hi2qPHr0qNvf39+iI97soRIh4f3z58/u7du3SXX7Xt7Z2enevHmzfQe+oSN2apSAPj09TSrb+XKI/f379+08+A0cNRE2ANkupk+ACNPvkSPcAAEibACyXUyfABGm3yNHuAECRNgAZLuYPgEirKlHu7u7XdyytGwHAd8jjNyng4OD7vnz51dbPT8/7z58+NB9+/bt6jU/TI+AGWHEnrx48eJ/EsSmHzx40L18+fLyzxF3ZVMjEyDCiEDjMYZZS5wiPXnyZFbJaxMhQIQRGzHvWR7XCyOCXsOmiDAi1HmPMMQjDpbpEiDCiL358eNHurW/5SnWdIBbXiDCiA38/Pnzrce2YyZ4//59F3ePLNMl4PbpiL2J0L979+7yDtHDhw8vtzzvdGnEXdvUigSIsCLAWavHp/+qM0BcXMd/q25n1vF57TYBp0a3mUzilePj4+7k5KSLb6gt6ydAhPUzXnoPR0dHl79WGTNCfBnn1uvSCJdegQhLI1vvCk+fPu2ePXt2tZOYEV6/fn31dz+shwAR1sP1cqvLntbEN9MxA9xcYjsxS1jWR4AIa2Ibzx0tc44fYX/16lV6NDFLXH+YL32jwiACRBiEbf5KcXoTIsQSpzXx4N28Ja4BQoK7rgXiydbHjx/P25TaQAJEGAguWy0+2Q8PD6/Ki4R8EVl+bzBOnZY95fq9rj9zAkTI2SxdidBHqG9+skdw43borCXO/ZcJdraPWdv22uIEiLA4q7nvvCug8WTqzQveOH26fodo7g6uFe/a17W3+nFBAkRYENRdb1vkkz1CH9cPsVy/jrhr27PqMYvENYNlHAIesRiBYwRy0V+8iXP8+/fvX11Mr7L7ECueb/r48eMqm7FuI2BGWDEG8cm+7G3NEOfmdcTQw4h9/55lhm7DekRYKQPZF2ArbXTAyu4kDYB2YxUzwg0gi/41ztHnfQG26HbGel/crVrm7tNY+/1btkOEAZ2M05r4FB7r9GbAIdxaZYrHdOsgJ/wCEQY0J74TmOKnbxxT9n3FgGGWWsVdowHtjt9Nnvf7yQM2aZU/TIAIAxrw6dOnAWtZZcoEnBpNuTuObWMEiLAx1HY0ZQJEmHJ3HNvGCBBhY6jtaMoEiJB0Z29vL6ls58vxPcO8/zfrdo5qvKO+d3Fx8Wu8zf1dW4p/cPzLly/dtv9Ts/EbcvGAHhHyfBIhZ6NSiIBTo0LNNtScABFyNiqFCBChULMNNSdAhJyNSiECRCjUbEPNCRAhZ6NSiAARCjXbUHMCRMjZqBQiQIRCzTbUnAARcjYqhQgQoVCzDTUnQIScjUohAkQo1GxDzQkQIWejUogAEQo121BzAkTI2agUIkCEQs021JwAEXI2KoUIEKFQsw01J0CEnI1KIQJEKNRsQ80JECFno1KIABEKNdtQcwJEyNmoFCJAhELNNtScABFyNiqFCBChULMNNSdAhJyNSiECRCjUbEPNCRAhZ6NSiAARCjXbUHMCRMjZqBQiQIRCzTbUnAARcjYqhQgQoVCzDTUnQIScjUohAkQo1GxDzQkQIWejUogAEQo121BzAkTI2agUIkCEQs021JwAEXI2KoUIEKFQsw01J0CEnI1KIQJEKNRsQ80JECFno1KIABEKNdtQcwJEyNmoFCJAhELNNtScABFyNiqFCBChULMNNSdAhJyNSiECRCjUbEPNCRAhZ6NSiAARCjXbUHMCRMjZqBQiQIRCzTbUnAARcjYqhQgQoVCzDTUnQIScjUohAkQo1GxDzQkQIWejUogAEQo121BzAkTI2agUIkCEQs021JwAEXI2KoUIEKFQsw01J0CEnI1KIQJEKNRsQ80JECFno1KIABEKNdtQcwJEyNmoFCJAhELNNtScABFyNiqFCBChULMNNSdAhJyNSiEC/wGgKKC4YMA4TAAAAABJRU5ErkJggg==" />
                            </template>
                            <template #actions>
                                <a-typography-text :style="{width: '100px' }" :ellipsis="true" strong
                                    v-if="item.display" @dblclick="double_click(item)">
                                    {{item.fileName}}
                                </a-typography-text>
                                <a-input :ref="(el)=>in_ref(el,item.index)" @keyup.enter="$event.target.blur()"
                                    @blur="UnFileDisplay(item)" size="small" :bordered="false"
                                    v-model:value="item.fileName" v-if="!item.display" />
                            </template>
                        </a-card>
                        <template #overlay>
                            <a-menu>
                                <a-menu-item key="1">
                                    <folder-open-filled />
                                    下载</a-menu-item>
                                <a-menu-item key="2" @click="Preview(item)">
                                    <eye-filled />
                                    预览</a-menu-item>
                                <a-menu-item key="3" @click="double_click(item)">
                                    <edit-filled />
                                    重命名</a-menu-item>
                                <a-menu-item key="4" @click="deleteFile(item)">
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
    import {
        useLock
    } from '../until/useLock';
    import token from '../until/token';
    import COS from 'cos-js-sdk-v5';


    // // 卡片信息
    // interface CardData {
    //     display: boolean,
    //         fId: string,
    //         folderName: string,
    //         image: string,
    //         masterFolderId: string,
    //         createTime: string,
    //         updateTime: string,
    //         volume: string,
    //         isDelete: number,
    // }
    // 文本框显示判定
    const display = ref(false)
    // 文件夹列表
    // const listData = reactive < CardData[20] > ([])
    const listData = reactive([])
    // 存储组件参数，用于获取焦点
    const refin = ref({})
    // 获取共享变量的参数
    const store = useStore();
    // 计算数组长度
    const arrayLength = computed(() => store.state.CardData.length)
    // 存储桶信息
    const CosData = token.getCosData()

    // 定义cos中的秘钥
    const cos = new COS({
        SecretId: CosData.SecretId,
        SecretKey: CosData.SecretKey
    })

    const {
        acquireLock,
        releaseLock
    } = useLock()

    // 监听数组长度变化
    watch(arrayLength, (newQuestion, oldQuestion) => {
        // 视图更新
        addData()
        let isor = false
        for (let i = 0; i < store.state.CardData.length; i++) {
            // 判断这次的长度变化的原因是新增文件夹，还是面包屑导航出现的文件夹数量变化,亦或是新增文件
            if ((!store.state.CardData[i].folderName || store.state.CardData[i].folderName.trim().length ===
                    0) &&
                store.state.CardData[i].fileType == undefined) {
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

    // 视频预览
    function Preview(params) {
        store.state.videoUrl = params.fileUrl
    }

    // 生命函数：组件挂载完成后调用
    onMounted(() => {
        // getData()
        store.state.masterId = null
    })

    // 打开文件夹
    function open(params) {
        // console.log(params);
        // getData(params)
        store.state.masterId = params.fId
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
                // let form = {
                //     fId: store.state.masterId
                // }
                // if (store.state.masterId != null)
                //     getData(form)
                // else
                //     getData()
                let index = listData.indexOf(res)
                listData.splice(index, 1)
            }
        })
    }

    // 删除文件
    function deleteFile(params) {
        cos.deleteObject({
            Bucket: CosData.Bucket,
            /* 填写自己的 bucket，必须字段 */
            Region: CosData.Region,
            /* 存储桶所在地域，必须字段 */
            Key: params.fileName,
            /* 存储在桶里的对象键（例如1.jpg，a/b/test.txt），必须字段 */
        }, function (err, data) {
            if (data.statusCode == 200 || data.statusCode == 204) {
                request.post("/delete_file/" + params.fileId).then(r => {
                    if (r.code === 0) {
                        message.success('删除成功！')
                        // let form = {
                        //     fId: store.state.masterId
                        // }
                        // if (store.state.masterId != null)
                        //     getData(form)
                        // else
                        //     getData()
                        let index = listData.indexOf(params)
                        listData.splice(index, 1)
                    }
                })
            }
        });
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
                } else {
                    message.error(res.message)
                    getData()
                }
            })

        }
    }

    // 连接后端获取列表
    function getData(res) {
        if (!acquireLock()) {
            return; // 如果无法获取锁，则退出方法
        }
        try {
            // 若为根文件夹
            if (res === undefined) {
                request.get("/get_folder_list", {
                    params: {
                        fId: ''
                    }
                }).then(r => {
                    store.commit('coverData', r.data)
                    store.state.masterId = r.data[0].masterFolderId
                    getFileData()
                })
            } else {
                request.get("/get_folder_list", {
                    params: {
                        fId: res.fId
                    }
                }).then(r => {
                    store.commit('coverData', r.data)
                    if (!r.data || r.data.length === 0) {
                        store.state.masterId = res.fId
                    } else {
                        store.state.masterId = r.data[0].masterFolderId
                    }
                    getFileData()
                })
            }
        } finally {
            releaseLock(); // 解锁
        }
    }

    function getFileData(params) {
        if (store.state.masterId === null) {
            request.get("get_file_list", {
                params: {
                    masterId: ''
                }
            }).then(res => {
                store.commit('fileListAdd', res.data)
            })
        } else {
            request.get("get_file_list", {
                params: {
                    masterId: store.state.masterId
                }
            }).then(res => {
                store.commit('fileListAdd', res.data)
            })
        }

    }
</script>
<style>
    .fixed-height-img {
        height: 120px;
        /* 设置固定高度 */
        width: 120px;
        object-fit: scale-down;
    }

    .my-container {
        padding: 20px;
        background: #fff;
        height: 620px;
        position: relative;
        overflow: auto;
    }

    /* 修改滚动条的宽度和颜色 */
    .my-container::-webkit-scrollbar {
        width: 4px;
    }

    .my-container::-webkit-scrollbar-track {
        background-color: #ffffff;
    }

    .my-container::-webkit-scrollbar-thumb {
        background-color: #727272;
        border-radius: 4px;
        /* 添加圆角效果 */
    }

    /* 当 thumb 处于悬停状态时的样式 */
    .my-container::-webkit-scrollbar-thumb:hover {
        background-color: rgb(108, 151, 157);
    }
</style>