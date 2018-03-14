package com.zsb.flyweightPattern.ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * http://blog.csdn.net/seu_calvin/article/details/52858135
 * Created by zsb on 2018/3/14.
 */
public class TicketClient {
    public static void main(String[] args) {
        //使用时
        TicketFactory.getTicket("南京", "杭州").showPrice("Gaotie");
        TicketFactory.getTicket("南京", "杭州").showPrice("Dongche");
    }
}

/*
*@SEU_Calvin
*@2016/12/31
*/
//抽象享元类
interface Ticket {
    //显示票价，参数为列车类型
    void showPrice(String type);
}

//具体享元类
class ConcreteTicket implements Ticket {
    String from;
    String to;

    public ConcreteTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void showPrice(String type) {
        if (type.equals("Gaotie")) {
            System.out.println("从" + from + "到" + to + "的高铁票价为200元");
        } else {
            System.out.println("从" + from + "到" + to + "的动车票价为120元");
        }
    }
}

//享元工厂类
class TicketFactory {
    static Map<String, Ticket> map = new ConcurrentHashMap<String, Ticket>();

    public static Ticket getTicket(String from, String to) {
        String key = from + to;
        if (map.containsKey(key)) {
            System.out.println("使用缓存查询" + key);
            return map.get(key);
        } else {
            System.out.println("创建对象查询" + key);
            Ticket ticket = new ConcreteTicket(from, to);
            map.put(key, ticket);
            return ticket;
        }
    }
}
