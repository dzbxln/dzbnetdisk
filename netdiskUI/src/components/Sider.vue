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
    <div style="color:#108ee9;">上传进度</div>
    <a-progress :show-info="false" :stroke-color="{'0%': '#108ee9','100%': '#87d068',}" :percent="percent" />
    <div style="color:#108ee9;">
        <video id="videoF" :src="store.state.videoUrl" controls preload="auto" height="112.5" width="200"
            style="object-fit:contain" controlslist="nofullscreen">
        </video>
        视频加载进度
        <a-progress :show-info="false" :stroke-color="{'0%': '#108ee9','100%': '#87d068',}" :percent="loadProgress" />
    </div>
</template>
<script setup lang="ts">
    import {
        InboxOutlined,
        FolderOpenTwoTone,
        PictureTwoTone,
        VideoCameraTwoTone,
    } from '@ant-design/icons-vue';
    import {
        ref,
        reactive,
        onMounted,
        watch,
    } from 'vue';
    import {
        useStore
    } from 'vuex';
    import {
        message,
        Upload
    } from 'ant-design-vue';
    import COS from 'cos-js-sdk-v5';
    import token from '../until/token';
    import request from '../until/request'
    const selectedKeys = ref < string[1] > (["1"])
    // 上传进度
    const percent = ref(0)
    // 视频加载进度
    const loadProgress = ref(0)
    // 共享变量
    const store = useStore();
    // COS配置文件
    const CosData = token.getCosData()

    // 定义cos中的秘钥
    const cos = new COS({
        SecretId: CosData.SecretId,
        SecretKey: CosData.SecretKey
    })

    // onMounted(() => {
    //     console.log(video);
    // })

    // 获取视频加载进度
    watch(() => store.state.videoUrl, (newQuestion, oldQuestion) => {
        if (store.state.videoUrl != "") {
            const video = document.getElementById('videoF');
            video.addEventListener('loadedmetadata', (event) => {
                const duration = video.duration;
                console.log(duration);
                video.addEventListener('progress', (event) => {
                    const i = video.buffered.end(0);
                    console.log(i);
                    const loading = i / duration
                    loadProgress.value = loading * 100
                    if (loading > 0.1) {
                        video.play()
                        video.addEventListener('ended', (event) => {
                            store.state.videoUrl = ''
                            loadProgress.value = 0
                        })
                    }
                })
            })
        }else{
            loadProgress.value = 0
        }
    })

    // // 回调函数。
    // function loadedmetadata() {
    //     const video = document.getElementById('videoF');
    //     // 视频总长度
    //     const duration = video.duration;
    //     // 正在下载数据
    //     video.addEventListener('progress', progress(duration))
    // }
    // // 回调函数二
    // function progress(duration) {
    //     const video = document.getElementById('videoF');
    //     const i = video.buffered.end(0);
    //     // 判断加载进度百分比
    //     const loading = i / duration
    //     loadProgress.value = loading * 100
    //     video.play()
    //     video.addEventListener('ended', (event) => {
    //         store.state.videoUrl = ''
    //         loadProgress.value = 0
    //     })
    // }
    // // 关闭监听
    // function closeListener(params) {
    //     const video = document.getElementById('videoF');
    //     video.removeEventListener('loadedmetadata', loadedmetadata)
    //     video.removeEventListener('progress', progress)
    //     video.addEventListener('ended', (event) => {
    //         store.state.videoUrl = ''
    //         loadProgress.value = 0
    //     })
    // }

    // 监听共享数据内的file，用于获取Layout选中的文件
    watch(() => store.state.file, (newQuestion, oldQuestion) => {
        if (newQuestion != undefined) {
            upload(store.state.file)
            store.state.file = undefined
        }
    })

    // 异步上传，关闭文件列表
    function before(params) {
        upload(params)
        return Upload.LIST_IGNORE
    }

    // 上传文件
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
                if (params.type.indexOf("image") != -1) {
                    addImage(data)
                } else if (params.type.indexOf("video") != -1) {
                    // 获取本地上传文件的临时url
                    store.state.videoUrl = URL.createObjectURL(params)
                    const video = document.createElement("video") //新创建一个video标签实例
                    video.src = URL.createObjectURL(params) // url地址
                    let canvas = document.createElement('canvas') // 建立canvas标签实例
                    const ctx = canvas.getContext('2d'); // 绘制2d
                    video.crossOrigin = 'anonymous' // 解决跨域问题，也就是提示污染资源无法转换视频
                    video.currentTime = 1 // 第一帧
                    video.oncanplay = () => {

                        canvas.width = video.clientWidth ? video.clientWidth : 960; // 获取视频宽度 or 默认宽度
                        canvas.height = video.clientHeight ? video.clientHeight : 540; //获取视频高度 or 默认高度
                        // 利用canvas对象方法绘图
                        ctx.drawImage(video, 0, 0, canvas.width, canvas.height)
                        // 转换成base64形式
                        let base64URL = canvas.toDataURL("image/png"); // 截取后的视频封面

                        // 获取上传文件的名称，筛掉后缀
                        var imgName = params.name.split("."),
                            imgN = imgName[0]
                        // 将字符串以逗号分割，取图形编码部分
                        var arr = base64URL.split(","),
                            bstr = atob(arr[1]),
                            n = bstr.length,
                            u8arr = new Uint8Array(n);
                        while (n--) {
                            u8arr[n] = bstr.charCodeAt(n);
                        }
                        // 将绘制好的base64编码转为file图片流
                        const file = new File([u8arr], imgN + "视频封面.png", {
                            type: "image/png"
                        })
                        // 传入方法
                        addCover(file, params.name, params.size / 1048576, 'http://' + data.Location)
                        // 释放无用的标签资源
                        video.remove();
                        canvas.remove();
                    }
                }
            }
        })
    }

    // 进行封面上传（文件，视频名，视频大小，视频链接）
    function addCover(params, videoName, videoVolume, videoUrl) {
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
                const coverUrl = 'http://' + data.Location
                // 封面链接，视频名，视频大小，视频链接
                addVideo(coverUrl, videoName, videoVolume, videoUrl)
            }
        })
    }

    // 上传图片调用
    function addImage(params) {
        console.log("图片！");
    }

    // 上传视频调用
    function addVideo(coverUrl, videoName, videoVolume, videoUrl) {
        const form = {
            fileName: videoName,
            fileType: 'video',
            fileImage: coverUrl,
            fileUrl: videoUrl,
            folderId: store.state.masterId,
            volume: videoVolume.toFixed(2),
        }
        // 导入数据库
        request.post("/create_file", form).then(res => {
            res.data.display = true
            // console.log(res.data);
            store.commit('fileAddData', res.data)
        })
    }
</script>
<style>
    /* // 全屏按钮 */
    video::-webkit-media-controls-fullscreen-button {
        display: none;
    }

    /* //播放按钮 */
    /* video::-webkit-media-controls-play-button {
  display: none;
} */
    /* //进度条 */
    /* video::-webkit-media-controls-timeline {
  display: none;
} */
    /* //观看的当前时间 */
    video::-webkit-media-controls-current-time-display {
        display: none;
    }

    /* //剩余时间 */
    video::-webkit-media-controls-time-remaining-display {
        display: none;
    }

    /* //音量按钮 */
    /* video::-webkit-media-controls-mute-button {
  display: none;
} */
    /* // 画中画 */
    video::-webkit-media-controls-toggle-closed-captions-button {
        display: none;
    }

    /* //音量的控制条 */
    /* video::-webkit-media-controls-volume-slider {
  display: none;
} */
    /* //所有控件 */
    /* video::-webkit-media-controls-enclosure {
  display: none;
} */
</style>