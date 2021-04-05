#### Binder

Binder初始化的工作就是通过JNI建立起Native Binder 与 Java Binder之间互相通信的桥梁



#### MessageQueue

native层还有一个Looper 和java层的是两个东西,没任何关系

enqueueMessage 

 将message按照时间顺序排序,并加入消息队列

根据情况调用nativeWake函数,以触发nativePollOnce函数,结束等待.

Handler为什么不用binder来做唤醒,

因为binder的一般用于进程间通信

binder涉及一次内存拷贝,handler中的msg不需要拷贝,只是需要通知另外一个线程有数据了

binder每次通信都射击到binder线程的创建和内存分配等,浪费CPU资源

屏障消息 通过postSyncBarrier 插入一个屏障 然后去寻找异步消息 然后执行  直到屏障移除才可以执行普通消息 removeSyncBarrier 这个方法可以移除屏障