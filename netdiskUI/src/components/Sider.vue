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
        <video id="videoF" :src="store.state.videoUrl" controls loop preload="auto" height="112.5" width="200"
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
    const selectedKeys = ref < string[1] > (["1"])
    const percent = ref(0)
    const loadProgress = ref(0)
    const store = useStore();
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
                video.addEventListener('progress', (event) => {
                    const i = video.buffered.end(0);
                    const loading = i / duration
                    loadProgress.value = loading * 100
                    if (loading > 0.3) {
                        video.play()
                    }
                })
            });
        }
    })
    // video.addEventListener('progress', function(e){
    //     console.log("准备！");
    // })

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
                    console.log(params);
                    // store.state.videoUrl = 'http://' + data.Location
                    const video = document.getElementById('videoF');
                    video.currentTime = 13
                    store.state.videoUrl = URL.createObjectURL(params)
                    // 创建画板
                    let canvas = document.createElement("canvas");
                    // 设置画板宽高 比例
                    canvas.width = video.width;
                    canvas.height = video.height;
                    // 获取画板上下文执行绘图 直接把video 丢进去 就可以了
                    canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
                    const base64URL = canvas.toDataURL("image/png")
                    var imgName = params.name.split("."),
                        imgN = imgName[0]
                    var arr = base64URL.split(","),
                        bstr = atob(arr[1]),
                        n = bstr.length,
                        u8arr = new Uint8Array(n);
                    while (n--) {
                        u8arr[n] = bstr.charCodeAt(n);
                    }
                    const file = new File([u8arr],imgN + "视频封面", { type: "image/png" })
                    addCover(file)
                    // let img = document.createElement("img");
                    // img.src = canvas.toDataURL("image/png");
                    // console.log(img)
                }
                // 手动处理 fileList
                // this.form.image = 'http://' + data.Location
                // this.savedish()
                // this.fileList[0].status = 'success'
            }
        })
    }

    // 进行封面上传
    function addCover(params) {
        
    }

    // 上传图片调用
    function addImage(params) {
        console.log("图片！");
    }

    // 上传视频调用
    function addVideo(params, data) {

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