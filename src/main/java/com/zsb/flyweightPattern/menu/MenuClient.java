package com.zsb.flyweightPattern.menu;

import java.util.*;

/**
 * 单纯享元模式
 * 菜单实例
 * http://blog.csdn.net/ai92/article/details/224598
 * Created by zsb on 2018/3/14.
 */


/**
 * 客户端角色
 */
public class MenuClient {
    private static FlyweightFactory factory;

    public static void main(String[] args)

    {

        List list1 = new ArrayList();

        factory = FlyweightFactory.getInstance();

        Menu menu = factory.factory("尖椒土豆丝");

        menu.setPersonMenu("a", list1);

        menu = factory.factory("红烧肉");

        menu.setPersonMenu("a", list1);

        menu = factory.factory("地三鲜");

        menu.setPersonMenu("a", list1);

        menu = factory.factory("地三鲜");

        menu.setPersonMenu("a", list1);

        menu = factory.factory("红焖鲤鱼");

        menu.setPersonMenu("a", list1);

        menu = factory.factory("红烧肉");

        menu.setPersonMenu("b", list1);

        menu = factory.factory("红焖鲤鱼");

        menu.setPersonMenu("b", list1);

        menu = factory.factory("地三鲜");

        menu.setPersonMenu("b", list1);

        System.out.println(factory.getNumber());


        List list2 = menu.findPersonMenu("b", list1);

        Iterator it = list2.iterator();

        while (it.hasNext())

        {
            System.out.println(" " + it.next());

        }

    }
}

interface Menu

{

    //规定了实现类必须实现设置内外关系的方法

    public void setPersonMenu(String person, List list);

//规定了实现类必须实现查找外蕴状态对应的内蕴状态的方法

    public List findPersonMenu(String person, List list);

}

/**
 * 具体享元角色
 */
class PersonMenu implements Menu

{

    private String dish;

    //在构造方法中给内蕴状态附值

    public PersonMenu(String dish) {

        this.dish = dish;

    }

    @Override
    public synchronized void setPersonMenu(String person, List list)

    {

        list.add(person);

        list.add(dish);

    }

    @Override
    public List findPersonMenu(String person, List list)

    {

        List dishList = new ArrayList();

        Iterator it = list.iterator();

        while (it.hasNext())

        {

            if (person.equals((String) it.next()))

                dishList.add(it.next());

        }

        return dishList;

    }

}

/**
 * 复合享元模式
 */
class PersonMenuMuch implements Menu {
    private Map MenuList = new HashMap();

    public PersonMenuMuch() {
    }

    //增加一个新的单纯享元对象
    public void add(String key, Menu menu) {
        MenuList.put(key, menu);
    }

    //两个无为的方法
    @Override
    public synchronized void setPersonMenu(String person, List list) {
    }

    @Override
    public List findPersonMenu(String person, List list) {
        List nothing = null;
        return nothing;
    }
}

/**
 * 享元工厂角色
 */
class FlyweightFactory

{

    private Map menuList = new HashMap();

    private static FlyweightFactory factory = new FlyweightFactory();

    //这里还使用了单例模式，来使工厂对象只产生一个工厂实例

    private FlyweightFactory() {
    }

    public static FlyweightFactory getInstance()

    {

        return factory;

    }

//这就是享元模式同工厂模式的不同所在！！

    public synchronized Menu factory(String dish)

    {

//判断如果内蕴状态已经存在就不再重新生成，而是使用原来的，否则就重新生成

        if (menuList.containsKey(dish))

        {

            return (Menu) menuList.get(dish);

        } else {

            Menu menu = new PersonMenu(dish);

            menuList.put(dish, menu);

            return menu;

        }

    }

    public Menu factory(String[] dish) {
        PersonMenuMuch menu = new PersonMenuMuch();
        String key = null;
        for (int i = 0; i < dish.length; i++) {
            key = dish[i];
            menu.add(key, this.factory(key));//调用了单纯享元角色的工厂方法
        }
        return menu;
    }

//来验证下是不是真的少产生了对象

    public int getNumber()

    {

        return menuList.size();

    }

}