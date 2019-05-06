# LeoMvp
Android组件化框架

基础MVP + Dagger2 + Arouter实现项目的组件化
网络方面使用Retrofit + RxJava2
UI方面使用ButterKnife、BaseRecyclerViewAdapterHelper、easyswipemenulayout（此控件直接copy代码进common了，不知为什么无法下载，表示很无奈）
、sweetalert(没研究具体用法，直接源码弄到项目里改了改监听)，后续还会加入一些常用的东西
数据库GreenDao
EventBus通信

目前状态：
        lib_common基本完成，为了避免多模块间资源混乱，相应模块下的gradle中加入了资源前缀限制resourcePrefix "common_"
        接下来准备做模块Demo。by 2019/5/6
     
