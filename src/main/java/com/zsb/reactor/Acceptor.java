package com.zsb.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Created by zsb on 17/7/17.
 */
public class Acceptor {
    private Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }

    public void run() {
        try {
            SocketChannel socketChannel = reactor.serverSocketChannel.accept();
            if (socketChannel != null)//调用Handler来处理channel
                new SocketReadHandler(reactor.selector, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
