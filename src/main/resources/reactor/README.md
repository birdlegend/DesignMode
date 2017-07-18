reactor模式
===
* author: zsb
* date: 20170717

----

### 概述
[反应器(Reactor)模式](http://blog.csdn.net/linxcool/article/details/7771952)

[Reactor模式详解-上善若水](http://www.blogjava.net/DLevin/archive/2015/09/02/427045.html)

[Scalable IO in Java](http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf)

[细说Reactor模式](http://blog.csdn.net/u010168160/article/details/53019039)

`reactor框架图见/resources/reacotr/reacotr.png`

Java NIO非堵塞技术实际是采取反应器模式，或者说是观察者(observer)模式为我们监察I/O端口，
如果有内容进来，会自动通知我们，这样，我们就不必开启多个线程死等，
从外界看，实现了流畅的I/O读写，不堵塞了。

同步和异步区别：有无通知（是否轮询）
堵塞和非堵塞区别：操作结果是否等待（是否马上有返回值），只是设计方式的不同

NIO 有一个主要的类Selector，这个类似一个观察者，只要我们把需要探知的socketchannel告诉Selector，
我们接着做别的事情，当有事件发生时，他会通知我们，传回一组SelectionKey，我们读取这些Key，
就会获得我们刚刚注册过的socketchannel，然后，我们从这个Channel中读取数据，接着我们可以处理这些数据。
反应器模式与观察者模式在某些方面极为相似：当一个主体发生改变时，所有依属体都得到通知。
不过，观察者模式与单个事件源关联，而反应器模式则与多个事件源关联 。

### 一般模型

我们想象以下情形：长途客车在路途上，有人上车有人下车，但是乘客总是希望能够在客车上得到休息。

传统的做法是：每隔一段时间（或每一个站），司机或售票员对每一个乘客询问是否下车。

反应器模式做法是：汽车是乘客访问的主体（Reactor），乘客上车后，到售票员（acceptor）处登记，
之后乘客便可以休息睡觉去了，当到达乘客所要到达的目的地后，售票员将其唤醒即可。

