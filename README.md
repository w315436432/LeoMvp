# LeoMvp
Android组件化框架

lib_common为框架主要的库，提供给各模块所需功能。app为空壳，根据模块创建module依赖lib_common，然后进行模块开发

基础MVP + Dagger2 + Arouter实现项目的组件化

网络方面使用Retrofit + RxJava2

UI方面使用ButterKnife、BaseRecyclerViewAdapterHelper、easyswipemenulayout（此控件直接copy代码进common了，不知为什么无法下载，表示很无奈）
、sweetalert(没研究具体用法，直接源码弄到项目里改了改监听)，后续还会加入一些常用的东西

数据库GreenDao

EventBus通信

API接口使用 http://api.comin.top/

状态：
     lib_common基本完成，为了避免多模块间资源混乱，相应模块下的gradle中加入了资源前缀限制resourcePrefix "common_"
接下来准备做模块Demo。by 2019/5/6

module_home加入，在LoginAct中加了个测试接口。by 2019/5/8
        
使用注意：  
     1、module下build.gradle中isModule需自己在gradle.properties中定义，为了便于进行模块单独测试，如单独运行module，需在相应module中配置Manifest，自行百度；             
     2、module如果使用ButterKnife、Arouter，需要在build.gradle下配置apply plugin: 'com.jakewharton.butterknife'、apply plugin: 'com.alibaba.arouter'。并添加arouter-compiler、butterknife-compiler依赖；
     3、apply plugin: 'cn.leo.plugin.magic'是singleclick防重复点击库所需；
     4、由于使用Dagger2提供了一些所需的实例，所以每个模块下一定要添加 dagger2-compiler；
     5、建议每个module使用resourcePrefix限制资源前缀，防止资源文件过多造成混乱；
     
     


     
